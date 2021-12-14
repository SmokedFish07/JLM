package com.jlm.controller;

import com.jlm.common.util.FileUploadUtil;
import com.jlm.common.util.ImgCut;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserSpecialController {

    @RequestMapping("/userLoginVerify")
    public Map userLoginVerify(HttpServletRequest request){
        Map map=new HashMap();
        HttpSession session =request.getSession();
        if (session.getAttribute("uid")!=null){
            map.put("isok",true);
            if (session.getAttribute("uimg")!=null){
                String uImg=session.getAttribute("uimg").toString();
                map.put("uImg",uImg);
            }
        }else{
            map.put("isok",false);
        }
        return map;
    }

    @RequestMapping(value = "/uploadHeadImage",method = RequestMethod.POST)
    public String uploadHeadImage(
            HttpServletRequest request,
            @RequestParam(value = "x") String x,
            @RequestParam(value = "y") String y,
            @RequestParam(value = "h") String h,
            @RequestParam(value = "w") String w,
            @RequestParam(value = "imgFile") MultipartFile imageFile
    ) throws Exception{
        System.out.println("==========Start=============");
        System.out.println("需要截取的坐标：X:>>" + x + ">>Y:>>" + y + ">>H:>>" + h + ">>W:>>" + w);
        String realPath = "E:/software3-3-1/jlmfile/";
        System.out.println(realPath);
        String resourcePath = "uploadImg/";
        String imgName="";
        if(imageFile!=null){
            if(FileUploadUtil.allowUpload(imageFile.getContentType())){
                String fileName = FileUploadUtil.rename(imageFile.getOriginalFilename());
                int end = fileName.lastIndexOf(".");
                String saveName = fileName.substring(0,end);
                File dir = new File(realPath + resourcePath);
                if(!dir.exists()){
                    dir.mkdirs();
                }
                System.out.println("---文件保存目录--" + dir);
                File file = new File(dir,saveName+"_src.jpg");
                imageFile.transferTo(file);
                String srcImagePath = realPath + resourcePath + saveName;
                int imageX = Integer.valueOf(x);
                int imageY = Integer.valueOf(y);
                int imageH = Integer.valueOf(h);
                int imageW = Integer.valueOf(w);
                //这里开始截取操作
                System.out.println("==========imageCutStart=============");
                ImgCut.imgCut(srcImagePath,imageX,imageY,imageW,imageH);
                System.out.println("==========imageCutEnd=============");
                imgName=saveName+"_cut.jpg";
                request.getSession().setAttribute("imgCut",imgName);//成功之后显示用
                file.delete();
            }
        }
        return "图片已经上传，待更新到数据库中";
    }
}

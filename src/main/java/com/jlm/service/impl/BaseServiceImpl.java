package com.jlm.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;

public class BaseServiceImpl<M extends BaseMapper<T>,T>
        extends ServiceImpl<M,T>
        implements IService<T> {

    //定义一个文件上传的方法，并放回图片名
    public String upload(MultipartFile detailImg, String target) throws IOException {
        //上传文件路径
        String path="E:\\software3-3-1\\jlmfile\\"+target;
        if(detailImg ==null){
            return null;
        }
        if(!detailImg.getOriginalFilename().equals("")){
            //获取输出流
            OutputStream os=new FileOutputStream(path+"\\"+detailImg.getOriginalFilename());
            //获取输入流 CommonsMultipartFile 中可以直接得到文件的流
            BufferedInputStream is=new BufferedInputStream(detailImg.getInputStream());
            byte[] buff=new byte[1024];
            int temp;
            //一个一个字节的读取并写入
            while ((temp = is.read(buff))!=(-1)){
                os.write(buff,0,temp);
            }
            os.flush();
            os.close();
            is.close();
            System.out.println("MultipartFile文件名:"+detailImg.getOriginalFilename().toString());
        }
        File file=new File(path+"\\"+detailImg.getOriginalFilename());
//        获取当前时间
        java.text.SimpleDateFormat date = new java.text.SimpleDateFormat("yyyyMMddHHmmssSS");
        String currentTimeMillis = date.format(new Date(System.currentTimeMillis()));


        String fname=file.getName();
        String t1_fname=fname.substring(0,fname.lastIndexOf("."));
        String t2_fname = fname.substring(fname.lastIndexOf("."));

        System.out.println("转file后文件名:"+fname);
        System.out.println("t1:"+t1_fname);
        System.out.println("t2:"+t2_fname);
//        创建新名字
        fname=currentTimeMillis + t2_fname;
//        覆盖掉源文件
        File newfile=new File(path+"\\"+fname);
        file.renameTo(newfile);
        return fname;
    }

    public void deleteOld(String fname,String target){
        //文件路径
        String path="E:\\software3-3-1\\jlmfile\\"+target;
        File file=new File(path+"\\"+fname);
        file.delete();
    }
}

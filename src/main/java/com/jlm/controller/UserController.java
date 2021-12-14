package com.jlm.controller;


import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.RandomUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jlm.common.util.EmailUtil;
import com.jlm.entity.User;
import com.jlm.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ZXY
 * @since 2021-10-16
 */
@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    private EmailUtil emailUtil;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/loginUser")
    public Map loginUser(User user,HttpSession session){
        Map map=new HashMap();
        if (userService.loginUser(user)==null){
            map.put("isok",false);
            map.put("message","没有这个用户");
        }else {
            User loginUser=userService.loginUser(user);
            String k="BannedID_"+loginUser.getUid();
            Integer type=0;
            if (loginUser.getBanned()==1){
                if (loginUser.getBanned()==1&&stringRedisTemplate.opsForValue().get(k)==null){
                    userService.unbannedById(loginUser.getUid());
                }else {
                    type=Integer.valueOf(stringRedisTemplate.opsForValue().get(k));
                }
            }
            if (loginUser!=null){
                if (type==1){
                    map.put("isok",false);
                    map.put("message","你号被封了");
                }else {
                    map.put("isok",true);
                    session.setAttribute("uid",loginUser.getUid());
                    session.setAttribute("uname",loginUser.getUname());
                    session.setAttribute("uimg",loginUser.getUimg());
                }
            }else{//失败
                map.put("isok",false);
                map.put("message","用户名或密码错误");
            }
        }
        return map;
    }

    @RequestMapping("/insertUser")
    public Map insertUser(User user){
        Map map=new HashMap();
        boolean isok= userService.insertUser(user);
        if (!isok){
            //失败
            map.put("isok",isok);
            map.put("message","注册失败");
            return map;
        }else {
            map.put("isok",isok);
            return map;
        }

    };

    @RequestMapping("/admin/queryUser")
    public Map queryUser(@RequestParam(required = true,defaultValue = "1") Integer pn, String key){
        Map map=new HashMap();
        PageHelper.startPage(pn,3);
        List<User> ulist=userService.queryUser(key);
        PageInfo<User> pageInfo=new PageInfo<>(ulist);
        map.put("records",ulist);
        map.put("pageInfo",pageInfo);
        return map;
    }
    @RequestMapping("/admin/deleteUser")
    public boolean deleteUser(Integer id){
        return userService.deleteUser(id);
    }

    @RequestMapping("/admin/bannedById")
    public boolean bannedById(Integer id,Long time){
        return userService.bannedById(id,time);
    }

    @RequestMapping("/admin/unbannedById")
    public boolean unbannedById(Integer id){
        return userService.unbannedById(id);
    }

    @RequestMapping("/updateUser")
    public Map updateUser(User user){
        Map map=new HashMap();
        boolean isok=userService.updateUser(user);
        if(!isok){//操作失败
            map.put("message","修改失败");
        }
        map.put("isok",isok);
        return map;
    }

    @RequestMapping("/updateUserImg")
    public boolean updateUserImg(HttpServletRequest request){
        HttpSession session=request.getSession();
        Object uImgO=session.getAttribute("imgCut");
        Object uidO=session.getAttribute("uid");
        Assert.isFalse(uidO==null,"还没有登录哦");
        int userId=Integer.valueOf(uidO.toString());
        //如有有旧图片先删除旧的
        if (session.getAttribute("uimg")!=null){
            String olduserImg=session.getAttribute("uimg").toString();
            File file=new File("E:/software3-3-1/jlmfile/uploadImg/"+olduserImg);
            file.delete();
        }
        if (uImgO!=null){
            String uImg=uImgO.toString();
            userService.updateUserImg(userId,uImg);
            session.setAttribute("uimg",uImg);
            return true;
        }else {
            return false;
        }
    }

    @RequestMapping("/updateUpwdByCode")
    public boolean updateUpwdByCode(@Param("newpwd")String newpwd,String code,HttpServletRequest request){
        HttpSession session=request.getSession();
        boolean result=false;
        if (session.getAttribute("uid")!=null){
            if (session.getAttribute("code")!=null){
                String sessionCode= session.getAttribute("code").toString();
                if (sessionCode.equals(code)){
                    int userId=Integer.valueOf(session.getAttribute("uid").toString());
                    result= userService.updateUpwdByCode(userId,newpwd);
                }
            }
        }
        return result;
    }

    @RequestMapping("/sendEmailCode")
    public void sendEmailCode(HttpServletRequest request){
        HttpSession session=request.getSession();
        session.setMaxInactiveInterval(60);
        if (session.getAttribute("uid")!=null){
            int userId=Integer.valueOf(session.getAttribute("uid").toString());
            String code= RandomUtil.randomNumbers(6);
            User user= userService.selectUserById(userId);
            emailUtil.sendAuthCode(user.getUemail(),"验证码:有效时间60s",code);
            session.setAttribute("code",code);
        }
    }

    @RequestMapping("/queryUserInf")
    public Map queryUserInf(HttpServletRequest request){
        Map map=new HashMap();
        HttpSession session=request.getSession();
        if (session.getAttribute("uid")!=null){
            int userId=Integer.valueOf(session.getAttribute("uid").toString());
            User user= userService.selectUserById(userId);
            map.put("isok",true);
            map.put("user",user);
        }else {
            map.put("isok",false);
        }
        return map;
    }

    @RequestMapping("/userLogout")
    public void userLogout(HttpSession session){
        session.invalidate();
    }

}


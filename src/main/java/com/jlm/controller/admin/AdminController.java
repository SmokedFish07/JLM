package com.jlm.controller.admin;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jlm.entity.Admin;
import com.jlm.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ZXY
 * @since 2021-10-19
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @RequestMapping("/loginAdmin")
    public Map loginAdmin(Admin admin,HttpSession session){
        Map map=new HashMap();
        Admin loginAdmin=adminService.loginAdmin(admin);
//        System.out.println("登陆管理员数据"+loginAdmin);
        if(loginAdmin!=null){
            map.put("isok",true);
            session.setAttribute("aid",loginAdmin.getAid());
        }else{//失败
            map.put("isok",false);
            map.put("message","管理员信息错误");
        }
        return map;
    }

    @RequestMapping("/addAdmin")
    public Map addAdmin(Admin admin){
        Map map=new HashMap();
        boolean isok=adminService.addAdmin(admin);
        if (!isok){
            //失败
            map.put("isok",isok);
            map.put("message","添加失败");
            return map;
        }else {
            map.put("isok",isok);
            return map;
        }
    }

    @RequestMapping("/queryAdmin")
    public Map queryAdmin(@RequestParam(required = true,defaultValue = "1") Integer pn,String key){
        Map map=new HashMap();
        //分页查询
        PageHelper.startPage(pn,2);
        List<Admin> alist=adminService.queryAdmin(key);
        //分页后构建分页对象
        PageInfo<Admin> pageInfo=new PageInfo<>(alist);
        map.put("records",alist);
        map.put("pageInfo",pageInfo);
        return map;
    }


    @RequestMapping("/deleteAdmin")
    public boolean deleteAdmin(Integer id){
        return adminService.deleteAdmin(id);
    }

    @RequestMapping("/updateAdmin")
    public Map updateAdmin(Admin admin){
        Map map=new HashMap();
        System.out.println("controller"+admin);
        boolean isok=adminService.updateAdmin(admin);
        if(!isok){//操作失败
            map.put("message","修改失败");
        }
        map.put("isok",isok);
        return map;
    }
}


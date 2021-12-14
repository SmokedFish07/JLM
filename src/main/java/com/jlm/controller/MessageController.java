package com.jlm.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jlm.entity.Message;
import com.jlm.entity.Product;
import com.jlm.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ZXY
 * @since 2021-11-12
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @RequestMapping("/add")
    public boolean add(Integer pid, String message, HttpServletRequest request){
        HttpSession session=request.getSession();
        if (session.getAttribute("uid")!=null){
            int uid=Integer.valueOf(session.getAttribute("uid").toString());
            Message obj=new Message();
            obj.setMuid(uid);
            obj.setMcontent(message);
            obj.setMpid(pid);
            obj.setMdate(LocalDateTime.now());
            return messageService.addMsg(obj);
        }else{
            return false;
        }
    }

    @RequestMapping("/queryByPid")
    public Map queryByPid(Integer pid,@RequestParam(required = true,defaultValue = "1") Integer pn){
        Map map=new HashMap();
        PageHelper.startPage(pn,3);
        List<Message> mlist= messageService.selectByPid(pid);

        PageInfo<Message> pageInfo=new PageInfo<>(mlist);
        map.put("records",mlist);
        map.put("pageInfo",pageInfo);
        return map;
    }

}


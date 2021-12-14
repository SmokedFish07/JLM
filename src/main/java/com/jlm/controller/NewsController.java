package com.jlm.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jlm.entity.Admin;
import com.jlm.entity.News;
import com.jlm.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ZXY
 * @since 2021-11-07
 */
@RestController
public class NewsController {
    @Autowired
    private NewsService newsService;

    @RequestMapping("/admin/addNews")
    public boolean addNews(News news,HttpServletRequest request){
        HttpSession session=request.getSession();
        boolean result=false;
        if (session.getAttribute("aid")!=null){
            int aid=Integer.valueOf(session.getAttribute("aid").toString());
            news.setNaid(aid);
            news.setNdate(LocalDate.now());
            result= newsService.addNews(news);
        }
        return result;
    }

    @RequestMapping("/admin/queryNews")
    public Map queryNews(@RequestParam(required = true,defaultValue = "1") Integer pn,String key){
        Map map=new HashMap();
        //分页查询
        PageHelper.startPage(pn,3);
        List<News> nlist=newsService.queryNews(key);
        //分页后构建分页对象
        PageInfo<News> pageInfo=new PageInfo<>(nlist);
        map.put("records",nlist);
        map.put("pageInfo",pageInfo);
        return map;
    }

    @RequestMapping("/admin/deleteNews")
    public boolean deleteNews(Integer id){
        return newsService.deleteNews(id);
    }

    @RequestMapping("/admin/updateNews")
    public Map updateNews(News news){
        Map map=new HashMap();
        boolean isok=newsService.updateNews(news);
        if(!isok){//操作失败
            map.put("message","修改失败");
        }
        map.put("isok",isok);
        return map;
    }

    @RequestMapping("/queryNews")
    public Map QueryNews(@RequestParam(required = true,defaultValue = "1") Integer pn,String key){
        Map map=new HashMap();
        //分页查询
        PageHelper.startPage(pn,6);
        List<News> nlist=newsService.queryNews(key);
        //分页后构建分页对象
        PageInfo<News> pageInfo=new PageInfo<>(nlist);
        map.put("records",nlist);
        map.put("pageInfo",pageInfo);
        return map;
    }

    @RequestMapping("/queryNewsById")
    public Map queryNewsById(Integer nid){
        Map map=new HashMap();
        News news=newsService.SelectById(nid);
        if (news==null){
            map.put("isok",false);
            map.put("message","没有这个新闻哦");
        }else {
            map.put("isok",true);
            map.put("news",news);
        }
        return map;
    }


}


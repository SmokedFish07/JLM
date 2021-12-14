package com.jlm.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jlm.entity.Admin;
import com.jlm.entity.Category;
import com.jlm.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ZXY
 * @since 2021-11-11
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/add")
    public Map add(Category category,@RequestParam(name = "filepart") MultipartFile file)throws IOException {
        Map map=new HashMap();
        boolean isok=categoryService.add(category,file);
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

    @RequestMapping("/queryNameId")
    public List<Category> queryNameId(){
        List<Category> clist=categoryService.queryNameId();
        return clist;
    }

    @RequestMapping("/queryById")
    public Map queryById(Integer cid){
        int sold= categoryService.querySoldById(cid);
        Category category=categoryService.selectById(cid);
        category.setSold(sold);
        Map map=new HashMap();
        if (category!=null){
            map.put("isok",true);
            map.put("category",category);
        }else {
            map.put("isok",false);
            map.put("message","传入id为空");
        }
        return map;
    }

    @RequestMapping("/query")
    public Map query(@RequestParam(required = true,defaultValue = "1") Integer pn,String key){
        Map map=new HashMap();
        //分页查询
        PageHelper.startPage(pn,2);
        List<Category> clist=categoryService.query(key);
        //分页后构建分页对象
        PageInfo<Category> pageInfo=new PageInfo<>(clist);
        map.put("records",clist);
        map.put("pageInfo",pageInfo);
        return map;
    }

    @RequestMapping("/update")
    public Map updateAdmin(Category category,@RequestParam(name = "filepart") MultipartFile file) throws IOException {
        Map map=new HashMap();
        boolean isok=categoryService.updateCategory(category,file);
        if(!isok){//操作失败
            map.put("message","修改失败");
        }
        map.put("isok",isok);
        return map;
    }

    @RequestMapping("/delete")
    public boolean delete(Integer id){
        return categoryService.delete(id);
    }

}


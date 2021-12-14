package com.jlm.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jlm.entity.Category;
import com.jlm.entity.Product;
import com.jlm.service.ProductService;
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
 * @since 2021-11-12
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/add")
    public Map add(Product product,@RequestParam(name = "filepart") MultipartFile file) throws IOException {
        Map map=new HashMap();
        boolean isok=productService.add(product,file);
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

    @RequestMapping("/query")
    public Map query(@RequestParam(required = true,defaultValue = "1") Integer pn,String key){
        Map map=new HashMap();
        //分页查询
        PageHelper.startPage(pn,2);
        List<Product> plist=productService.query(key);
        //分页后构建分页对象
        PageInfo<Product> pageInfo=new PageInfo<>(plist);
        map.put("records",plist);
        map.put("pageInfo",pageInfo);
        return map;
    }

    @RequestMapping("/deleteById")
    public boolean deleteUser(Integer id){
        return productService.deleteById(id);
    }

    @RequestMapping("/update")
    public Map updateProduct(Product product,@RequestParam(name = "filepart") MultipartFile file) throws IOException {
        Map map=new HashMap();
        boolean isok=productService.updateProduct(product,file);
        if (!isok){
            //失败
            map.put("isok",isok);
            map.put("message","修改失败");
            return map;
        }else {
            map.put("isok",isok);
            return map;
        }
    }

    @RequestMapping("/webQuery")
    public Map webQuery(@RequestParam(required = true,defaultValue = "1") Integer pn,String key){
        Map map=new HashMap();
        //分页查询
        PageHelper.startPage(pn,4);
        List<Product> plist=productService.query(key);
        //分页后构建分页对象
        PageInfo<Product> pageInfo=new PageInfo<>(plist);
        map.put("records",plist);
        map.put("pageInfo",pageInfo);
        return map;
    }

    @RequestMapping("/queryById")
    public Map queryById(Integer pid){
        Map map=new HashMap();
        Product product=productService.selectById(pid);
        if (product==null){
            map.put("isok",false);
            map.put("message","没有这个商品哦");
        }else {
            map.put("isok",true);
            map.put("product",product);
        }
        return map;
    }
}


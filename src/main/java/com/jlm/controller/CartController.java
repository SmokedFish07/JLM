package com.jlm.controller;


import cn.hutool.json.JSONObject;
import com.alibaba.fastjson.JSONArray;
import com.jlm.entity.Cart;
import com.jlm.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ZXY
 * @since 2021-11-13
 */
@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @RequestMapping("/add")
    public boolean add(Integer pid, Integer num, HttpServletRequest request){
        HttpSession session=request.getSession();
        if (session.getAttribute("uid")!=null){
            int uid=Integer.valueOf(session.getAttribute("uid").toString());
            Cart cart=new Cart();
            cart.setPid(pid);
            cart.setNum(num);
            cart.setUid(uid);
            return cartService.addCart(cart);
        }else {
            return false;
        }
    }

    @RequestMapping("/queryByUid")
    public List queryByUid(HttpServletRequest request){
        HttpSession session=request.getSession();
        int uid=Integer.valueOf(session.getAttribute("uid").toString());
        List<Cart> carts=cartService.queryByUid(uid);
        return carts;
    }

    @RequestMapping("/deleteByIds")
    public boolean deleteByIds(@RequestParam("ids") String ids, HttpServletRequest request){
        List<Integer> list= JSONArray.parseArray(ids,Integer.class);
        HttpSession session=request.getSession();
        int uid=Integer.valueOf(session.getAttribute("uid").toString());
        boolean result=cartService.deleteByIds(list,uid);
        return result;
    }


    @RequestMapping("/update")
    public boolean update(@RequestParam("cart") String cart){
        List<Cart> carts=JSONArray.parseArray(cart,Cart.class);
        boolean result=cartService.updateNum(carts);
        return result;
    }

}


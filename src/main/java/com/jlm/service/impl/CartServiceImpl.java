package com.jlm.service.impl;

import com.jlm.entity.Cart;
import com.jlm.mapper.CartMapper;
import com.jlm.service.CartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ZXY
 * @since 2021-11-13
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {
    @Autowired
    private CartMapper cartMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addCart(Cart cart) {
        int res = 0;
        if (cartMapper.selectByPid(cart.getPid())!=null){
            res=cartMapper.updateNum(cart);
        }else {
            res=cartMapper.insert(cart);
        }
        return res>0;
    }

    @Override
    public List<Cart> queryByUid(int uid) {
        return cartMapper.SelectByUid(uid);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteByIds(List<Integer> ids, int uid) {
        int res=cartMapper.deleteByIds(ids,uid);
        return res>0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateNum(List<Cart> carts) {
        int res=0;
        for (int i=0;i<carts.size();i++){
            Cart cart=carts.get(i);
//            System.out.println(cart);
            res+=cartMapper.setNum(cart);
        }
//        System.out.println(res);
        return res>0;
    }
}

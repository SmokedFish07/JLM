package com.jlm.service;

import com.jlm.entity.Cart;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ZXY
 * @since 2021-11-13
 */
public interface CartService extends IService<Cart> {

    boolean addCart(Cart cart);

    List<Cart> queryByUid(int uid);

    boolean deleteByIds(List<Integer> ids, int uid);

    boolean updateNum(List<Cart> carts);
}

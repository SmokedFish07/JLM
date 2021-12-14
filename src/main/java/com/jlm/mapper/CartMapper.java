package com.jlm.mapper;

import com.jlm.entity.Cart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ZXY
 * @since 2021-11-13
 */
@Repository
@Mapper
public interface CartMapper extends BaseMapper<Cart> {

    @Select("select * from cart where pid=#{pid}")
    Cart selectByPid(Integer pid);

    @Update("update cart set num=num+#{cart.num} where pid=#{cart.pid} and uid=#{cart.uid}")
    int updateNum(@Param("cart")Cart cart);

    List<Cart> SelectByUid(int uid);

    int deleteByIds(List<Integer> ids, int uid);

    @Update("update cart set num=#{cart.num} where id=#{cart.id} and uid=#{cart.uid}")
    int setNum(@Param("cart")Cart cart);
}

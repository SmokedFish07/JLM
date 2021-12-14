package com.jlm.mapper;

import com.jlm.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ZXY
 * @since 2021-11-11
 */
@Repository
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

    List<Category> selectByKey(String key);

    @Select("select clogo from category where cid = #{cid}")
    String selectPicById(Integer cid);

    @Select("select count(1) from product where pcid = #{cid}")
    Integer selectSoldByCid(Integer cid);
}

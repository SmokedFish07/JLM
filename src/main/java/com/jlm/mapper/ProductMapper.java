package com.jlm.mapper;

import com.jlm.entity.Product;
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
 * @since 2021-11-12
 */
@Repository
@Mapper
public interface ProductMapper extends BaseMapper<Product> {

    List<Product> selectByKey(String key);

    @Select("select ppic from product where pid = #{pid}")
    String selectPicById(Integer pid);

    Product selectPandCById(Integer pid);
}

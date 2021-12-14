package com.jlm.mapper.admin;

import com.jlm.entity.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
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
 * @since 2021-10-19
 */
@Repository
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

    List<Admin> selectAdmin(String key);

}

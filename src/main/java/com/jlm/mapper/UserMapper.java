package com.jlm.mapper;

import com.jlm.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ZXY
 * @since 2021-10-16
 */
public interface UserMapper extends BaseMapper<User> {

    List<User> selectUser(String key);

    int toBanned(Integer type,Integer id);

    @Update("update user set uImg=#{uImg} where uid = #{uid} ")
    void updateUserImgById(int uid, String uImg);

    @Update("update user set upwd=#{pwd} where uid=#{uid}")
    boolean updatePwd(int uid, String pwd);
}

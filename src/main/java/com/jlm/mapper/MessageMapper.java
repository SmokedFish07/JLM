package com.jlm.mapper;

import com.jlm.entity.Message;
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
public interface MessageMapper extends BaseMapper<Message> {

    List<Message> selectByPid(Integer pid);
}

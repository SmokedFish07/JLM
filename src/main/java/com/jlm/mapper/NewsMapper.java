package com.jlm.mapper;

import com.jlm.entity.News;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ZXY
 * @since 2021-11-07
 */
@Repository
@Mapper
public interface NewsMapper extends BaseMapper<News> {

    List<News> selectNews(String key);
}

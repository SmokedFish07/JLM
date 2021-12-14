package com.jlm.service;

import com.jlm.entity.News;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ZXY
 * @since 2021-11-07
 */
public interface NewsService extends IService<News> {

    boolean addNews(News news);

    List<News> queryNews(String key);

    boolean deleteNews(Integer id);

    boolean updateNews(News news);

    News SelectById(Integer nid);
}

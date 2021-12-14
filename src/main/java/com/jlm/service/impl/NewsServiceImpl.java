package com.jlm.service.impl;

import com.jlm.entity.News;
import com.jlm.mapper.NewsMapper;
import com.jlm.service.NewsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ZXY
 * @since 2021-11-07
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService {

    @Autowired
    private NewsMapper newsMapper;

    @Override
    public boolean addNews(News news) {
        int result= newsMapper.insert(news);
        return result>0?true:false;
    }

    @Override
    public List<News> queryNews(String key) {
        return newsMapper.selectNews(key);
    }

    @Override
    public boolean deleteNews(Integer id) {
        int result =newsMapper.deleteById(id);
        return result>0;
    }

    @Override
    public boolean updateNews(News news) {
        int result=newsMapper.updateById(news);
        return result>0?true:false;
    }

    @Override
    public News SelectById(Integer nid) {
        News news=newsMapper.selectById(nid);
        return news;
    }
}

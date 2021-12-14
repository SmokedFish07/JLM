package com.jlm.service;

import com.jlm.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.data.relational.core.sql.In;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ZXY
 * @since 2021-11-11
 */
public interface CategoryService extends IService<Category> {
    boolean add(Category category, MultipartFile file) throws IOException;
    List<Category> query(String  key);

    boolean updateCategory(Category category, MultipartFile file) throws IOException;

    boolean delete(Integer id);

    List<Category> queryNameId();

    Integer querySoldById(Integer cid);

    Category selectById(Integer cid);
}

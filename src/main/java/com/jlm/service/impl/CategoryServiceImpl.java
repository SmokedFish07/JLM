package com.jlm.service.impl;

import com.jlm.common.util.FileUploadUtil;
import com.jlm.entity.Category;
import com.jlm.mapper.CategoryMapper;
import com.jlm.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ZXY
 * @since 2021-11-11
 */
@Service
public class CategoryServiceImpl extends BaseServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public boolean add(Category category, MultipartFile file) throws IOException {
        String target="category";
        String imgname=upload(file,target);
        if (imgname!=null&&!imgname.equals("")){
            category.setClogo(imgname);
        }
        int result =categoryMapper.insert(category);
        return result>0?true:false;
    }

    @Override
    public List<Category> query(String key) {
        return categoryMapper.selectByKey(key);
    }

    @Override
    public boolean updateCategory(Category category, MultipartFile file) throws IOException {
        String target="category";
        //检查是否有旧图片，删除旧的
        if (!file.isEmpty()){
            String oldFname=categoryMapper.selectPicById(category.getCid());
            String imgname=upload(file,target);
            if (imgname!=null&&!imgname.equals("")){
                deleteOld(oldFname,target);
                category.setClogo(imgname);
            }
        }

        int result=categoryMapper.updateById(category);
        return result>0?true:false;
    }

    @Override
    public boolean delete(Integer id) {
        int result=categoryMapper.deleteById(id);
        return result>0;
    }

    @Override
    public List<Category> queryNameId() {
        return categoryMapper.selectList(null);
    }

    @Override
    public Integer querySoldById(Integer cid) {
        return categoryMapper.selectSoldByCid(cid);
    }

    @Override
    public Category selectById(Integer cid) {
        return categoryMapper.selectById(cid);
    }
}

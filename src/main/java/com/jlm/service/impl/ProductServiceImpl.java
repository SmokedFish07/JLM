package com.jlm.service.impl;

import com.jlm.entity.Product;
import com.jlm.mapper.ProductMapper;
import com.jlm.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ZXY
 * @since 2021-11-12
 */
@Service
public class ProductServiceImpl extends BaseServiceImpl<ProductMapper, Product> implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean add(Product product, MultipartFile file) throws IOException {
        String target="product";
        String imgname=upload(file,target);
        if (imgname!=null&&!imgname.equals("")){
            product.setPpic(imgname);
        }
        product.setPdate(LocalDateTime.now());
        int result =productMapper.insert(product);
        return result>0?true:false;
    }

    @Override
    public List<Product> query(String key) {
        return productMapper.selectByKey(key);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteById(Integer id) {
        int result =productMapper.deleteById(id);
        return result>0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateProduct(Product product, MultipartFile file) throws IOException {
        String target="product";
        //检查是否有旧图片，删除旧的
        if (!file.isEmpty()){
            String oldFname=productMapper.selectPicById(product.getPid());
            String imgname=upload(file,target);
            if (imgname!=null&&!imgname.equals("")){
                deleteOld(oldFname,target);
                product.setPpic(imgname);
            }
        }
        int result =productMapper.updateById(product);
        return result>0;
    }

    @Override
    public Product selectById(Integer pid) {
        return productMapper.selectPandCById(pid);
    }
}

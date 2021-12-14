package com.jlm.service;

import com.jlm.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ZXY
 * @since 2021-11-12
 */
public interface ProductService extends IService<Product> {

    Product selectById(Integer pid);

    boolean add(Product product, MultipartFile file) throws IOException;
    List<Product> query(String key);

    boolean deleteById(Integer id);

    boolean updateProduct(Product product, MultipartFile file) throws IOException;
}

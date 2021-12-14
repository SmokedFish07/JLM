package com.jlm.service.admin;

import com.jlm.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ZXY
 * @since 2021-10-19
 */
public interface AdminService extends IService<Admin> {

    public Admin loginAdmin(Admin admin);

    boolean addAdmin(Admin admin);

    List<Admin> queryAdmin(String key);

    boolean deleteAdmin(Integer id);

    boolean updateAdmin(Admin admin);
}

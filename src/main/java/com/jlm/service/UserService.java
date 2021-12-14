package com.jlm.service;

import com.jlm.entity.User;
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
 * @since 2021-10-16
 */
public interface UserService extends IService<User> {
    //用户注册
    public boolean insertUser(User user);
    //用户登陆
    public User loginUser(User user);
    //查询用户（全部或搜索）
    List<User> queryUser(String key);
    //删除用户
    boolean deleteUser(Integer id);
    //封禁用户
    boolean bannedById(Integer id,Long time);
    boolean unbannedById(Integer id);

    //修改用户
    boolean updateUser(User user);
    //修改用户前获取用户数据
    User selectUserById(int userId);
    //修改用户头像
    void updateUserImg(int userId, String uImg);
    //修改密码（需要邮箱）
    boolean updateUpwdByCode(int userId, String newPwd);

}

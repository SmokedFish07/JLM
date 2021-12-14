package com.jlm.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jlm.entity.User;
import com.jlm.mapper.UserMapper;
import com.jlm.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ZXY
 * @since 2021-10-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    UserMapper userMapper;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    @Transactional
    public boolean insertUser(User user) {
        user.setUdate(LocalDateTime.now());
        int result=userMapper.insert(user);
        return result>0?true:false;
    }

    @Override
    public User loginUser(User user) {
        Map<String,Object> map=new HashMap<>();
        map.put("uname",user.getUname());
        map.put("upwd",user.getUpwd());
        List<User> list =userMapper.selectByMap(map);
        if(list.isEmpty()&&list.size()<=0){
            return null;
        }else {
            return list.get(0);
        }
    }

    @Override
    public List<User> queryUser(String key) {
        return userMapper.selectUser(key);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteUser(Integer id) {
        int result =userMapper.deleteById(id);
        return result>0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean bannedById(Integer id,Long time) {
        String k="BannedID_"+id;
        Long btime=time;
        int result=userMapper.toBanned(1,id);
        if (result>0){
            stringRedisTemplate.opsForValue().set(
                    k,"1",btime, TimeUnit.DAYS
            );
        }
        return result>0;
    }

    @Override
    public boolean unbannedById(Integer id) {
        String k="BannedID_"+id;
        int result=userMapper.toBanned(0,id);
        if (result>0){
            stringRedisTemplate.delete(k);
        }
        return result>0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateUser(User user){
        int result=userMapper.updateById(user);
        return result>0?true:false;
    }

    @Override
    public User selectUserById(int userId) {
        User user=userMapper.selectById(userId);
        return user;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUserImg(int userId, String uImg) {
        userMapper.updateUserImgById(userId,uImg);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateUpwdByCode(int userId,String newPwd) {
        return userMapper.updatePwd(userId,newPwd);
    }
}

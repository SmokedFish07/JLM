package com.jlm.service.admin.impl;

import com.jlm.entity.Admin;
import com.jlm.mapper.admin.AdminMapper;
import com.jlm.service.admin.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ZXY
 * @since 2021-10-19
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    @Autowired
    AdminMapper adminMapper;

    @Override
    public Admin loginAdmin(Admin admin) {
        Map<String,Object> map=new HashMap<>();
        map.put("aname",admin.getAname());
        map.put("apwd",admin.getApwd());
        List<Admin> list=adminMapper.selectByMap(map);
        if(list.isEmpty()&&list.size()<=0){
            return null;
        }else {
            return list.get(0);
        }
    }

    @Override
    public boolean addAdmin(Admin admin) {
        int result=adminMapper.insert(admin);
        return result>0?true:false;
    }

    @Override
    public List<Admin> queryAdmin(String key) {
        return adminMapper.selectAdmin(key);
    }


    @Override
    public boolean deleteAdmin(Integer id) {
        int result=adminMapper.deleteById(id);
        return result>0;
    }

    @Override
    public boolean updateAdmin(Admin admin) {
        int result=adminMapper.updateById(admin);
        System.out.println("Impl"+admin);
        return result>0?true:false;
    }
}

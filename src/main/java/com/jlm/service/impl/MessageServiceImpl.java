package com.jlm.service.impl;

import com.jlm.entity.Message;
import com.jlm.mapper.MessageMapper;
import com.jlm.service.MessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addMsg(Message obj) {
        int result =messageMapper.insert(obj);
        return result>0 ;
    }

    @Override
    public List<Message> selectByPid(Integer pid) {
        return messageMapper.selectByPid(pid);
    }
}

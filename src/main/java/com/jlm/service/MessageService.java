package com.jlm.service;

import com.jlm.entity.Message;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ZXY
 * @since 2021-11-12
 */
public interface MessageService extends IService<Message> {

    boolean addMsg(Message obj);

    List<Message> selectByPid(Integer pid);
}

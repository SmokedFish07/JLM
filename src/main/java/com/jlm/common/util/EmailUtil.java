package com.jlm.common.util;

import com.jlm.common.properties.EmailProperties;
import io.github.biezhi.ome.OhMyEmail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EmailUtil {
    @Autowired
    EmailProperties emailProperties;

    /**
     *发送验证码
     * @param to 目标邮箱
     * @param title 标题
     * @param code 验证码
     */
    public void sendAuthCode(String to,String title,String code){
        try {
            OhMyEmail.config(OhMyEmail.SMTP_QQ(false),
                    emailProperties.getUsername(),
                    emailProperties.getPassword());
            OhMyEmail.subject(title)
                    .from(emailProperties.getUsername())
                    .to(to)
                    .html("<h1 font=red>"+code+"</h1>").send();
        }catch (Exception e){
            log.info(e.getMessage(),e);
        }

    }
}

package com.jlm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author ZXY
 * @since 2021-11-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "mid", type = IdType.AUTO)
    private Integer mid;

    private Integer muid;

    private String mtitle;

    private String mcontent;

    private Integer mpid;

    private LocalDateTime mdate;

    @TableField(exist = false)
    private String uimg;
    @TableField(exist = false)
    private String uname;


}

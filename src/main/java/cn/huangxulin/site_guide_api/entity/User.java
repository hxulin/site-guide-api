package cn.huangxulin.site_guide_api.entity;

import cn.huangxulin.site_guide_api.bean.BaseEntity;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 功能描述: 用户实体类
 *
 * @author hxulin
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("sg_user")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 下载授权码
     */
    private String authCode;

    /**
     * 局域网IP
     */
    private String lanIp;

    /**
     * IP地址最近一次更新时间
     */
    private Date lastUpdated;

    /**
     * 状态: 0正常, 1禁用
     */
    private Integer status;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    public User(String nickname, String authCode) {
        this.nickname = nickname;
        this.authCode = authCode;
    }

    public User(String nickname, String authCode, Integer status) {
        this.nickname = nickname;
        this.authCode = authCode;
        this.status = status;
    }
}

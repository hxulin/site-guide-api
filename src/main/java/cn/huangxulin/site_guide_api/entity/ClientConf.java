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
 * 功能描述: 客户端配置
 *
 * @author hxulin
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("sg_client_conf")
public class ClientConf extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 本地 DNS 网关地址, 如: 192.168.1.1
     */
    private String localDnsAddress;

    /**
     * 更新本地IP的最短时间间隔
     */
    private int minTime;

    /**
     * 更新本地IP的最长时间间隔
     */
    private int maxTime;

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

}

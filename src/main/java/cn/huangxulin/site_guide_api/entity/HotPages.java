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
 * 功能描述: 热点页面实体类
 *
 * @author hxulin
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("sg_hot_pages")
public class HotPages extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 页面名称
     */
    private String name;

    /**
     * 访问地址
     */
    private String url;

    /**
     * 排序号
     */
    private Integer sequence;

    /**
     * 状态: 0展示, 1隐藏
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

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

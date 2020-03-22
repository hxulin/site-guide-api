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
 * 功能描述: 项目实体类
 *
 * @author hxulin
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("sg_project")
public class Project extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 项目类型枚举, 前端类型
     */
    public static final int TYPE_FRONT_END = 0;

    /**
     * 项目类型枚举, 后端类型
     */
    public static final int TYPE_BACK_END = 1;

    /**
     * 项目名称
     */
    private String name;

    /**
     * 访问地址
     */
    private String entranceUrl;

    /**
     * 项目类型: 0前端项目, 1后端项目
     */
    private Integer type;

    /**
     * 排序号
     */
    private Integer sequence;

    /**
     * 状态: 0正常, 1关闭
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

}

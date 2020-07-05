package cn.huangxulin.site_guide_api.entity;

import cn.huangxulin.site_guide_api.bean.BaseEntity;
import cn.huangxulin.site_guide_api.bean.Const;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 功能描述: 任务指令实体类
 *
 * @author hxulin
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("sg_instruction")
public class Instruction extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 所属任务
     */
    private Long taskId;

    /**
     * 指令描述
     */
    @TableField("`desc`")
    private String desc;

    /**
     * 指令版本
     */
    private Integer version;

    /**
     * 指令内容
     */
    private String content;

    /**
     * 状态: 0 已上线, 1 待上线
     */
    private Integer status = Const.Status.DISABLE;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}

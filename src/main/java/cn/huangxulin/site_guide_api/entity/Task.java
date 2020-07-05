package cn.huangxulin.site_guide_api.entity;

import cn.huangxulin.site_guide_api.bean.BaseEntity;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 功能描述: 动态任务实体类
 *
 * @author hxulin
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("sg_task")
public class Task extends BaseEntity {

    private static final long serialVersionUID = 1L;

    // 任务状态, 运行中
    public static final int RUNNING = 0;

    // 任务状态, 已停止
    public static final int STOPPED = 1;

    // 任务状态, 正在启动
    public static final int STARTING = 2;

    // 任务状态, 正在关停
    public static final int STOPPING = 3;

    /**
     * 所属用户
     */
    private Long uid;

    /**
     * 任务名称
     */
    private String name;

    /**
     * 心跳时间, 单位: 秒
     */
    private Integer heartbeat = 3;

    /**
     * 状态: 0 运行中, 1 已停止, 2 正在启动, 3 正在关停
     */
    private Integer status = STARTING;

    /**
     * 备注
     */
    private String remark;

    /**
     * 访问标识
     * <p>
     * 隐含了指令的加解密密钥: 前16位为key, 后16位为iv
     */
    private String accessFlag;

    /**
     * 任务指令集合
     */
    private List<Instruction> instructions;

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

    /**
     * 计算任务状态是否需要更新
     */
    public boolean needUpdate() {
        return status == Task.STARTING || status == Task.STOPPING;
    }

    /**
     * 状态更新
     */
    public void updateStatus() {
        if (status == Task.STARTING) {
            status = Task.RUNNING;
        } else if (status == STOPPING) {
            status = Task.STOPPED;
        }
    }

}

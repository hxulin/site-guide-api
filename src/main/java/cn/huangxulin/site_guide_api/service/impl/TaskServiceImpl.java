package cn.huangxulin.site_guide_api.service.impl;

import cn.huangxulin.site_guide_api.entity.Instruction;
import cn.huangxulin.site_guide_api.entity.Task;
import cn.huangxulin.site_guide_api.entity.User;
import cn.huangxulin.site_guide_api.exception.BusinessExceptionAware;
import cn.huangxulin.site_guide_api.mapper.TaskMapper;
import cn.huangxulin.site_guide_api.service.IInstructionService;
import cn.huangxulin.site_guide_api.service.ITaskService;
import cn.huangxulin.site_guide_api.service.IUserService;
import cn.huangxulin.site_guide_api.util.AESUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 功能描述:
 *
 * @author hxulin
 */
@Service
@Transactional
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements ITaskService, BusinessExceptionAware {

    private IUserService userService;

    private IInstructionService instructionService;

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setInstructionService(IInstructionService instructionService) {
        this.instructionService = instructionService;
    }

    @Override
    public Task queryNewTask(String signature, int version) {
        User user = userService.getBySignature(signature);
        // 更新心跳时间
        userService.updateHeartbeatTime(user.getId());
        LambdaQueryWrapper<Task> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(Task::getId, Task::getHeartbeat, Task::getStatus);
        wrapper.eq(Task::getUid, user.getId());
        wrapper.in(Task::getStatus, Task.RUNNING, Task.STARTING, Task.STOPPING);
        List<Task> list = this.list(wrapper);
        if (list.size() > 0) {
            Task task = list.get(0);
            // 状态更新
            if (task.needUpdate()) {
                task.updateStatus();
                this.updateById(task);
            }
            // 指令加密
            String key = AESUtils.initKey();
            String iv = AESUtils.initIV();
            task.setAccessFlag(key + iv);
            List<Instruction> instructions = instructionService.queryNewInstruction(task.getId(), version);
            instructions.forEach(item -> {
                try {
                    item.setContent(AESUtils.encrypt(item.getContent(), key, iv));
                } catch (Exception e) {
                    throw error(e.getMessage());
                }
            });
            task.setInstructions(instructions);
            return task;
        }
        return null;
    }
}

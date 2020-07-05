package cn.huangxulin.site_guide_api.service.impl;

import cn.huangxulin.site_guide_api.bean.Const;
import cn.huangxulin.site_guide_api.entity.Instruction;
import cn.huangxulin.site_guide_api.mapper.InstructionMapper;
import cn.huangxulin.site_guide_api.service.IInstructionService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class InstructionServiceImpl extends ServiceImpl<InstructionMapper, Instruction> implements IInstructionService {

    @Override
    public List<Instruction> queryNewInstruction(Long taskId, int version) {
        LambdaQueryWrapper<Instruction> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(Instruction::getId, Instruction::getContent);
        wrapper.eq(Instruction::getTaskId, taskId);
        wrapper.gt(Instruction::getVersion, version);
        wrapper.eq(Instruction::getStatus, Const.Status.NORMAL);
        return this.list(wrapper);
    }
}

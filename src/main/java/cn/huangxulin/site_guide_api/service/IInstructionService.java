package cn.huangxulin.site_guide_api.service;

import cn.huangxulin.site_guide_api.entity.Instruction;

import java.util.List;

public interface IInstructionService {

    /**
     * 根据任务ID和指令版本查询最新的指令
     *
     * @param taskId  任务ID
     * @param version 指令版本
     */
    List<Instruction> queryNewInstruction(Long taskId, int version);

}

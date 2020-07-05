package cn.huangxulin.site_guide_api.service;

import cn.huangxulin.site_guide_api.entity.Task;

public interface ITaskService {

    /**
     * 查询新任务
     *
     * @param signature 用户签名
     * @param version   指令版本
     */
    Task queryNewTask(String signature, int version);

}

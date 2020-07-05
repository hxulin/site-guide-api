package cn.huangxulin.site_guide_api.controller;

import cn.huangxulin.site_guide_api.bean.ApiResponse;
import cn.huangxulin.site_guide_api.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 功能描述:
 *
 * @author hxulin
 */
@RestController
@RequestMapping("/task")
@Validated
public class TaskController {

    private ITaskService taskService;

    @Autowired
    public void setTaskService(ITaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * 客户端轮询请求任务
     *
     * @param desc    客户端描述信息
     * @param version 指令版本
     */
    @PostMapping("/ask")
    public ApiResponse ask(@RequestParam String desc, @RequestParam int version) {
        return ApiResponse.successOfData(taskService.queryNewTask(desc, version));
    }

}

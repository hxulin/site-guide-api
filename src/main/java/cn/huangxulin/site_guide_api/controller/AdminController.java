package cn.huangxulin.site_guide_api.controller;

import cn.huangxulin.site_guide_api.bean.ApiResponse;
import cn.huangxulin.site_guide_api.entity.Project;
import cn.huangxulin.site_guide_api.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述: 后台管理
 *
 * @author hxulin
 */
@RestController
@RequestMapping("/auth")
@Validated
public class AdminController {

    private IProjectService projectService;

    @Autowired
    public void setProjectService(IProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/project/add")
    public ApiResponse addProject(@RequestBody Project project) {

        projectService.save(project);
        return ApiResponse.successOfMessage("新增项目成功");
    }

}

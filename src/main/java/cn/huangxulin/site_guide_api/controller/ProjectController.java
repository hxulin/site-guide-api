package cn.huangxulin.site_guide_api.controller;

import cn.huangxulin.site_guide_api.bean.ApiResponse;
import cn.huangxulin.site_guide_api.entity.Project;
import cn.huangxulin.site_guide_api.query.ProjectQueryObject;
import cn.huangxulin.site_guide_api.service.IProjectService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 功能描述:
 *
 * @author hxulin
 */
@RestController
@RequestMapping("/project")
@Validated
public class ProjectController {

    private IProjectService projectService;

    @Autowired
    public void setProjectService(IProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/max_sequence")
    public ApiResponse getMaxSequence() {
        int count = projectService.count();
        return ApiResponse.success().addDataItem("maxSequence", count + 1);
    }

    /**
     * 项目列表查询
     */
    @PostMapping("/list")
    public ApiResponse listProject(@RequestBody ProjectQueryObject qo) {
        Page<Project> page = projectService.page(qo.getPageInfo(), qo.getWrapper());
        return ApiResponse.successOfData(page);
    }

}

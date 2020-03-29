package cn.huangxulin.site_guide_api.controller;

import cn.huangxulin.site_guide_api.bean.ApiResponse;
import cn.huangxulin.site_guide_api.bean.Const;
import cn.huangxulin.site_guide_api.entity.HotPages;
import cn.huangxulin.site_guide_api.entity.Project;
import cn.huangxulin.site_guide_api.service.IHotPagesService;
import cn.huangxulin.site_guide_api.service.IProjectService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 功能描述:
 *
 * @author hxulin
 */
@RestController
@RequestMapping("/home")
@Validated
public class HomeController {

    private IProjectService projectService;

    private IHotPagesService hotPagesService;

    @Autowired
    public void setProjectService(IProjectService projectService) {
        this.projectService = projectService;
    }

    @Autowired
    public void setHotPagesService(IHotPagesService hotPagesService) {
        this.hotPagesService = hotPagesService;
    }

    /**
     * 首页项目列表信息查询、热点页面信息查询
     */
    @GetMapping("/list")
    public ApiResponse list() {

        // 查询热点页面
        LambdaQueryWrapper<HotPages> hotPagesWrapper = new LambdaQueryWrapper<>();
        hotPagesWrapper.eq(HotPages::getStatus, Const.Status.NORMAL).orderByAsc(HotPages::getSequence);
        List<HotPages> hotPages = hotPagesService.list(hotPagesWrapper);

        // 查询前端页面
        LambdaQueryWrapper<Project> frontendWrapper = new LambdaQueryWrapper<>();
        frontendWrapper.eq(Project::getStatus, Const.Status.NORMAL)
                .eq(Project::getType, Project.TYPE_FRONT_END).orderByAsc(Project::getSequence);
        List<Project> frontendList = projectService.list(frontendWrapper);

        // 查询后端页面
        LambdaQueryWrapper<Project> backendWrapper = new LambdaQueryWrapper<>();
        backendWrapper.eq(Project::getStatus, Const.Status.NORMAL)
                .eq(Project::getType, Project.TYPE_BACK_END).orderByAsc(Project::getSequence);
        List<Project> backendList = projectService.list(backendWrapper);

        return ApiResponse.success()
                .addDataItem("hotPages", hotPages)
                .addDataItem("frontend", frontendList)
                .addDataItem("backend", backendList);
    }

}

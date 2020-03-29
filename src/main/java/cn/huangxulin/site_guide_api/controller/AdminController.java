package cn.huangxulin.site_guide_api.controller;

import cn.huangxulin.site_guide_api.bean.ApiResponse;
import cn.huangxulin.site_guide_api.entity.ClientConf;
import cn.huangxulin.site_guide_api.entity.Project;
import cn.huangxulin.site_guide_api.service.IClientConfService;
import cn.huangxulin.site_guide_api.service.IHotPagesService;
import cn.huangxulin.site_guide_api.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 功能描述: 后台管理
 *
 * @author hxulin
 */
@RestController
@RequestMapping("/auth")
@Validated
public class AdminController {

    private IClientConfService clientConfService;

    private IProjectService projectService;

    private IHotPagesService hotPagesService;

    @Autowired
    public void setClientConfService(IClientConfService clientConfService) {
        this.clientConfService = clientConfService;
    }

    @Autowired
    public void setProjectService(IProjectService projectService) {
        this.projectService = projectService;
    }

    @Autowired
    public void setHotPagesService(IHotPagesService hotPagesService) {
        this.hotPagesService = hotPagesService;
    }

    /**
     * 更新客户端配置信息
     */
    @PostMapping("/client_conf/upd")
    public ApiResponse upd(@RequestBody ClientConf clientConf) {
        List<ClientConf> list = clientConfService.list();
        if (list.size() <= 1) {
            if (list.size() == 1) {
                clientConf.setId(list.get(0).getId());
            }
            if (clientConfService.saveOrUpdate(clientConf)) {
                return ApiResponse.successOfMessage("配置信息更新成功");
            }
        }
        return ApiResponse.failOfMessage("配置信息更新失败");
    }

    @PostMapping("/project/add")
    public ApiResponse addProject(@RequestBody Project project) {

        projectService.save(project);
        return ApiResponse.successOfMessage("新增项目成功");
    }
}

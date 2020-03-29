package cn.huangxulin.site_guide_api.controller;

import cn.huangxulin.site_guide_api.bean.ApiResponse;
import cn.huangxulin.site_guide_api.entity.ClientConf;
import cn.huangxulin.site_guide_api.entity.Project;
import cn.huangxulin.site_guide_api.service.IClientConfService;
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

    @Autowired
    public void setClientConfService(IClientConfService clientConfService) {
        this.clientConfService = clientConfService;
    }

    @Autowired
    public void setProjectService(IProjectService projectService) {
        this.projectService = projectService;
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

    @PostMapping("/project/save")
    public ApiResponse saveHotPages(@RequestBody Project project) {
        projectService.saveOrUpd(project);
        return ApiResponse.successOfMessage("项目保存成功");
    }

    @PostMapping("/project/del")
    public ApiResponse delHotPages(@RequestBody Project[] projects) {
        projectService.delete(projects);
        return ApiResponse.successOfMessage("删除成功");
    }

}

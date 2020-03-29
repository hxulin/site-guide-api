package cn.huangxulin.site_guide_api.controller;

import cn.huangxulin.site_guide_api.bean.ApiResponse;
import cn.huangxulin.site_guide_api.context.AppContext;
import cn.huangxulin.site_guide_api.entity.ClientConf;
import cn.huangxulin.site_guide_api.entity.Project;
import cn.huangxulin.site_guide_api.entity.User;
import cn.huangxulin.site_guide_api.service.IClientConfService;
import cn.huangxulin.site_guide_api.service.IProjectService;
import cn.huangxulin.site_guide_api.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    private IUserService userService;

    @Autowired
    public void setClientConfService(IClientConfService clientConfService) {
        this.clientConfService = clientConfService;
    }

    @Autowired
    public void setProjectService(IProjectService projectService) {
        this.projectService = projectService;
    }

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
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

    /**
     * 项目新增、修改
     */
    @PostMapping("/project/save")
    public ApiResponse saveProject(@RequestBody Project project) {
        projectService.saveOrUpd(project);
        return ApiResponse.successOfMessage("项目保存成功");
    }

    /**
     * 项目删除
     */
    @PostMapping("/project/del")
    public ApiResponse delProject(@RequestBody Project[] projects) {
        projectService.delete(projects);
        return ApiResponse.successOfMessage("删除成功");
    }

    /**
     * 用户新增、修改
     */
    @PostMapping("/user/save")
    public ApiResponse saveUser(@RequestBody User user) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(User::getLanIp, User::getStatus).eq(User::getId, user.getId());
        if (userService.update(new User(user.getLanIp(), user.getStatus()), wrapper)) {
            return ApiResponse.successOfMessage("用户信息更新成功");
        }
        return ApiResponse.failOfMessage("用户信息更新失败");
    }

    /**
     * 用户删除
     */
    @PostMapping("/user/del")
    public ApiResponse delUser(@RequestBody User[] users) {
        if (AppContext.getAppConfig().isUserDeletionProtection()) {
            return ApiResponse.failOfMessage("系统检测到你的操作异常，已触发防护机制，本次请求被拒绝");
        }
        List<Long> ids = Arrays.stream(users).map(User::getId).collect(Collectors.toList());
        userService.removeByIds(ids);
        return ApiResponse.successOfMessage("删除成功");
    }

}

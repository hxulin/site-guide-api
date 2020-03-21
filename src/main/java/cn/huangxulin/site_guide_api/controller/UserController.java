package cn.huangxulin.site_guide_api.controller;

import cn.huangxulin.site_guide_api.bean.ApiResponse;
import cn.huangxulin.site_guide_api.bean.Const;
import cn.huangxulin.site_guide_api.context.AppContext;
import cn.huangxulin.site_guide_api.entity.User;
import cn.huangxulin.site_guide_api.service.IUserService;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 功能描述:
 *
 * @author hxulin
 */
@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    private IUserService userService;

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ApiResponse add() {
        User user = new User("Samples", "test");
        userService.saveOrUpdate(user);
        return ApiResponse.successOfMessage("用户新增成功");
    }

    @PostMapping("/upd_ip")
    public ApiResponse updateLanIp(@RequestParam String desc,
                                   @RequestParam @Length(max = 50) String lanIp) {
        userService.updateLanIp(desc, lanIp);
        return ApiResponse.successOfMessage("IP地址更新成功");
    }

    /**
     * 用户登录
     *
     * @param password 口令
     */
    @PostMapping("/login")
    public ApiResponse login(@RequestParam String password) {
        if (Const.UserGroup.DEFAULT_GROUP.equals(userService.findUserGroup(password))) {
            return ApiResponse.failOfMessage("登录口令错误");
        }
        return ApiResponse.success().addDataItem("token", userService.login(password));
    }

    /**
     * 获取用户信息
     */
    @GetMapping("/info")
    public ApiResponse getUserInfoByToken(@RequestParam String token) {
        return ApiResponse.success().addDataItem("group", AppContext.getTokenKit().getUserGroup(token));
    }
}

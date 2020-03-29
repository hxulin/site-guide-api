package cn.huangxulin.site_guide_api.controller;

import cn.huangxulin.site_guide_api.bean.ApiResponse;
import cn.huangxulin.site_guide_api.bean.Const;
import cn.huangxulin.site_guide_api.context.TokenKit;
import cn.huangxulin.site_guide_api.entity.User;
import cn.huangxulin.site_guide_api.query.UserQueryObject;
import cn.huangxulin.site_guide_api.service.IUserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
        if (Const.UserGroup.DEFAULT_GROUP.equals(TokenKit.getUserGroupByPassword(password))) {
            return ApiResponse.failOfMessage("登录口令错误");
        }
        return ApiResponse.success().addDataItem("token", TokenKit.generateToken(password));
    }

    /**
     * 获取用户信息
     */
    @GetMapping("/info")
    public ApiResponse getUserInfoByToken(@RequestParam String token) {
        return ApiResponse.success().addDataItem("group", TokenKit.getUserGroupByToken(token));
    }

    /**
     * 查询用户列表信息
     */
    @PostMapping("/list")
    public ApiResponse listUser(@RequestBody UserQueryObject qo) {
        Page<User> page = userService.page(qo.getPageInfo(), qo.getWrapper());
        return ApiResponse.successOfData(page);
    }
}

package cn.huangxulin.site_guide_api.controller;

import cn.huangxulin.site_guide_api.bean.ApiResponse;
import cn.huangxulin.site_guide_api.entity.User;
import cn.huangxulin.site_guide_api.service.IUserService;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}

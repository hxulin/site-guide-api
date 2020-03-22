package cn.huangxulin.site_guide_api.controller;

import cn.huangxulin.site_guide_api.bean.ApiResponse;
import cn.huangxulin.site_guide_api.entity.ClientConf;
import cn.huangxulin.site_guide_api.service.IClientConfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 功能描述: 客户端配置信息控制器
 *
 * @author hxulin
 */
@RestController
@RequestMapping("/client_conf")
public class ClientConfController {

    private IClientConfService clientConfService;

    @Autowired
    public void setClientConfService(IClientConfService clientConfService) {
        this.clientConfService = clientConfService;
    }

    @GetMapping("/get")
    public ApiResponse get() {
        List<ClientConf> list = clientConfService.list();
        if (list.size() > 0) {
            return ApiResponse.successOfData(list.get(0));
        }
        return ApiResponse.fail();
    }

}

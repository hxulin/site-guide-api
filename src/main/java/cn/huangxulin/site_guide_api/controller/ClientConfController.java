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

    /**
     * 更新客户端配置信息
     */
    @PostMapping("/upd")
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
}

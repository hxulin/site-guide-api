package cn.huangxulin.site_guide_api.controller;

import cn.huangxulin.site_guide_api.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Pattern;

/**
 * 功能描述: 客户端控制器
 *
 * @author hxulin
 */
@Controller
@RequestMapping("/client")
@Validated
public class ClientController {

    private IClientService clientService;

    @Autowired
    public void setClientService(IClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/download")
    public void download(
            @RequestParam @Pattern(regexp = "^[\u4E00-\u9FA5A-Za-z0-9]{1,10}$") String nickname,
            @RequestParam @Pattern(regexp = "[A-Za-z0-9]{1,10}") String authCode,
            HttpServletResponse response) throws Exception {
        clientService.download(nickname, authCode, response);
    }
}

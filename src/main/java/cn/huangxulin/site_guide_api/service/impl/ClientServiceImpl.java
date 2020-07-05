package cn.huangxulin.site_guide_api.service.impl;

import cn.huangxulin.site_guide_api.bean.Const;
import cn.huangxulin.site_guide_api.config.AppConfig;
import cn.huangxulin.site_guide_api.context.AppContext;
import cn.huangxulin.site_guide_api.context.ClientKit;
import cn.huangxulin.site_guide_api.entity.User;
import cn.huangxulin.site_guide_api.exception.BusinessExceptionAware;
import cn.huangxulin.site_guide_api.service.IClientService;
import cn.huangxulin.site_guide_api.service.IUserService;
import cn.huangxulin.site_guide_api.util.AESUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;

/**
 * 功能描述:
 *
 * @author hxulin
 */
@Service
@Transactional
public class ClientServiceImpl implements IClientService, BusinessExceptionAware {

    private IUserService userService;

    private ClientKit clientKit;

    private AppConfig appConfig = AppContext.getAppConfig();

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setClientKit(ClientKit clientKit) {
        this.clientKit = clientKit;
    }

    @Override
    public void  download(String nickname, String authCode, HttpServletResponse response) throws Exception {
        String signature = AESUtils.encrypt(nickname, appConfig.getSignatureAesKey(), appConfig.getSignatureAesIv());
        /*
         * 数据格式校验: 一个汉字占用两个字符长度
         * 签名长度校验: 签名长度为24
         */
        if (nickname.replaceAll("[\u4E00-\u9FA5]+?", "aa").length() > 10
                || signature.length() != ClientKit.SIGNATURE_LENGTH) {
            throw error("昵称格式错误");
        }
        User user = userService.getOne(new QueryWrapper<User>().eq("nickname", nickname));
        if (user == null) {
            user = new User(nickname, authCode, Const.Status.NORMAL);
            userService.save(user);
        } else if (!user.getAuthCode().equals(authCode)) {
            throw error("授权码错误");
        }
        byte[] bytes = clientKit.generate(signature);
        // 设置响应类型格式
        response.setContentType("application/octet-stream");
        response.getOutputStream().write(bytes);
    }
}
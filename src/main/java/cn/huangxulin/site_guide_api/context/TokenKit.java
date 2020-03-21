package cn.huangxulin.site_guide_api.context;

import cn.huangxulin.site_guide_api.bean.Status;
import cn.huangxulin.site_guide_api.config.AppConfig;
import cn.huangxulin.site_guide_api.exception.BusinessExceptionAware;
import cn.huangxulin.site_guide_api.service.IUserService;
import cn.huangxulin.site_guide_api.util.AESUtils;
import cn.huangxulin.site_guide_api.util.IpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 功能描述:
 *
 * @author hxulin
 */
@Component
public class TokenKit implements BusinessExceptionAware {

    /**
     * Token组成元素的分隔符
     */
    private static final String TOKEN_ELEMENT_SEPARATOR = "#";

    /**
     * Token签名密钥在Token组成元素中的位置
     */
    private static final int SECRET_KEY_INDEX = 2;

    /**
     * 用户口令在Token组成元素中的位置
     */
    private static final int PASSWORD_INDEX = 3;

    private AppConfig appConfig = AppContext.getAppConfig();

    private IUserService userService;

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    /**
     * 通过登录口令生成Token
     *
     * @param password 登录口令
     * @return Token
     */
    public String generateToken(String password) {
        String[] tokenElements = new String[]{
                String.valueOf(System.currentTimeMillis()),
                IpUtils.getIpAddr(AppContext.getRequest()),
                appConfig.getTokenSecretKey(),
                password
        };
        // 1584623533398#127.0.0.1#token-secret-key#user
        String tokenPlaintext = String.join(TOKEN_ELEMENT_SEPARATOR, tokenElements);
        try {
            return AESUtils.encrypt(tokenPlaintext, appConfig.getTokenAesKey(), appConfig.getTokenAesIv());
        } catch (Exception e) {
            throw error("Token 加密错误");
        }
    }

    /**
     * 获取用户组信息
     */
    public String getUserGroup(String token) {
        try {
            String tokenPlaintext = AESUtils.decrypt(token, appConfig.getTokenAesKey(), appConfig.getTokenAesIv());
            String[] tokenElements = tokenPlaintext.split(TOKEN_ELEMENT_SEPARATOR);
            if (appConfig.getTokenSecretKey().equals(tokenElements[SECRET_KEY_INDEX])) {
                // 获取用户组名
                return userService.findUserGroup(tokenElements[PASSWORD_INDEX]);
            }
            throw error(Status.FORBIDDEN.getCode(), "Token 签名密钥异常");
        } catch (Exception e) {
            throw error(Status.FORBIDDEN.getCode(),"Token 信息无效");
        }
    }
}

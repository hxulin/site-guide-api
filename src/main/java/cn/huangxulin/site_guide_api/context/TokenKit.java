package cn.huangxulin.site_guide_api.context;

import cn.huangxulin.site_guide_api.bean.Const;
import cn.huangxulin.site_guide_api.bean.Status;
import cn.huangxulin.site_guide_api.config.AppConfig;
import cn.huangxulin.site_guide_api.exception.BusinessException;
import cn.huangxulin.site_guide_api.util.AESUtils;
import cn.huangxulin.site_guide_api.util.IpUtils;

/**
 * 功能描述:
 *
 * @author hxulin
 */
public final class TokenKit {

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

    private static AppConfig appConfig = AppContext.getAppConfig();

    /**
     * 通过登录口令生成Token
     *
     * @param password 登录口令
     * @return Token
     */
    public static String generateToken(String password) {
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
            throw BusinessException.ofMessage("Token 加密错误");
        }
    }

    /**
     * 通过登录口令获取用户组信息
     *
     * @param password 登录口令
     * @return 用户组名
     */
    public static String getUserGroupByPassword(String password) {
        if (appConfig.getAdminGroup().contains(password)) {
            return Const.UserGroup.ADMIN_GROUP;  // 管理员组用户
        } else if (appConfig.getUserGroup().contains(password)) {
            return Const.UserGroup.USER_GROUP;  // 用户组用户
        }
        return Const.UserGroup.DEFAULT_GROUP;
    }

    /**
     * 通过Token获取用户组信息
     *
     * @param token token
     * @return 用户组名
     */
    public static String getUserGroupByToken(String token) {
        try {
            String tokenPlaintext = AESUtils.decrypt(token, appConfig.getTokenAesKey(), appConfig.getTokenAesIv());
            String[] tokenElements = tokenPlaintext.split(TOKEN_ELEMENT_SEPARATOR);
            if (appConfig.getTokenSecretKey().equals(tokenElements[SECRET_KEY_INDEX])) {
                // 获取用户组名
                return getUserGroupByPassword(tokenElements[PASSWORD_INDEX]);
            }
            throw BusinessException.of(Status.FORBIDDEN.getCode(), "Token 签名密钥异常");
        } catch (Exception e) {
            throw BusinessException.of(Status.FORBIDDEN.getCode(), "Token 信息无效");
        }
    }

}

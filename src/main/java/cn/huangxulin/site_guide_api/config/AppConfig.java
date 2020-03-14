package cn.huangxulin.site_guide_api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 功能描述: 系统相关配置信息
 *
 * @author hxulin
 */
@Configuration
@ConfigurationProperties(prefix = "app.config")
public class AppConfig {

    private String signatureAesKey;

    private String signatureAesIv;

    private int tokenExpireTime = 30;

    private int captchaExpireTime = 10;

    private String loginPasswordSalt = "";

    public String getSignatureAesKey() {
        return signatureAesKey;
    }

    public void setSignatureAesKey(String signatureAesKey) {
        this.signatureAesKey = signatureAesKey;
    }

    public String getSignatureAesIv() {
        return signatureAesIv;
    }

    public void setSignatureAesIv(String signatureAesIv) {
        this.signatureAesIv = signatureAesIv;
    }

    public int getTokenExpireTime() {
        return tokenExpireTime;
    }

    public void setTokenExpireTime(int tokenExpireTime) {
        this.tokenExpireTime = tokenExpireTime;
    }

    public int getCaptchaExpireTime() {
        return captchaExpireTime;
    }

    public void setCaptchaExpireTime(int captchaExpireTime) {
        this.captchaExpireTime = captchaExpireTime;
    }

    public String getLoginPasswordSalt() {
        return loginPasswordSalt;
    }

    public void setLoginPasswordSalt(String loginPasswordSalt) {
        this.loginPasswordSalt = loginPasswordSalt;
    }
}

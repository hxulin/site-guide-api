package cn.huangxulin.site_guide_api.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述: 系统相关配置信息
 *
 * @author hxulin
 */
@Configuration
@ConfigurationProperties(prefix = "app.config")
@Getter
@Setter
public class AppConfig {

    /**
     * 客户端签名的AES加密密钥
     */
    private String signatureAesKey;

    /**
     * 客户端签名的AES初始向量
     */
    private String signatureAesIv;

    /**
     * 管理员组用户名
     */
    private List<String> adminGroup = new ArrayList<>();

    /**
     * 用户组用户名
     */
    private List<String> userGroup = new ArrayList<>();

    /**
     * Token密钥, 更换后所有登录过的信息将失效
     */
    private String tokenSecretKey = "token-secret-key";
    /**
     * Token AES加密密钥
     */
    private String tokenAesKey;
    /**
     * Token AES加密初始向量
     */
    private String tokenAesIv;



    private String loginPasswordSalt = "";

}

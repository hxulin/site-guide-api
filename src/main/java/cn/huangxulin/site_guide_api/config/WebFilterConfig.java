package cn.huangxulin.site_guide_api.config;

import cn.huangxulin.site_guide_api.filter.TokenFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 功能描述: 过滤器相关配置
 *
 * @author hxulin
 */
@Configuration
public class WebFilterConfig {

    @Bean
    public FilterRegistrationBean<TokenFilter> tokenFilterFilterRegistrationBean() {
        TokenFilter tokenFilter = new TokenFilter();
        FilterRegistrationBean<TokenFilter> registration = new FilterRegistrationBean<>(tokenFilter);
        registration.addUrlPatterns("/*");
        registration.setOrder(1);
        return registration;
    }

}

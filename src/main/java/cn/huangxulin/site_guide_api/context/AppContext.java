package cn.huangxulin.site_guide_api.context;

import cn.huangxulin.site_guide_api.config.AppConfig;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 功能描述: 应用上下文
 *
 * @author hxulin
 */
@Component
public class AppContext implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }

    public static AppConfig getAppConfig() {
        return AppContext.getBean(AppConfig.class);
    }

    public static TokenKit getTokenKit() {
        return AppContext.getBean(TokenKit.class);
    }

    /**
     * 获取当前请求的 Request 对象
     */
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获取当前 Session 对象
     */
    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * 获取当前请求的 Response 对象
     */
    public static HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    /**
     * 获取 Spring 上下文对象
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 根据名字获取 Spring 容器中注册的 Bean 实例
     * 如果找不到该 Bean 的实例, 则抛出 org.springframework.beans.factory.NoSuchBeanDefinitionException
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name){
        return (T) applicationContext.getBean(name);
    }

    /**
     * 根据类型获取 Spring 容器中注册的 Bean 实例
     * 如果找不到该 Bean 的实例, 则抛出 org.springframework.beans.factory.NoSuchBeanDefinitionException
     */
    public static <T> T getBean(Class<T> clz){
        return applicationContext.getBean(clz);
    }

}

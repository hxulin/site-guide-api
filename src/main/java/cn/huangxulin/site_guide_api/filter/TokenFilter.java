package cn.huangxulin.site_guide_api.filter;

import cn.huangxulin.site_guide_api.bean.Const;
import cn.huangxulin.site_guide_api.bean.Status;
import cn.huangxulin.site_guide_api.context.TokenKit;
import cn.huangxulin.site_guide_api.exception.BusinessException;
import org.springframework.http.HttpMethod;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 功能描述:
 *
 * @author hxulin
 */
public class TokenFilter extends OncePerRequestFilter {

    private final List<String> WHITELIST = Arrays.asList("/client_conf/get", "/user/upd_ip", "/user/login", "/user/info");

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) {
        String token = req.getHeader(Const.TOKEN_KEY);
        ContextManager.addAttribute(Const.TOKEN_KEY, token);
        try {
            if (!HttpMethod.OPTIONS.matches(req.getMethod()) && !WHITELIST.contains(req.getRequestURI())) {
                if (token != null) {
                    String userGroup = TokenKit.getUserGroupByToken(token);
                    if (req.getRequestURI().startsWith("/auth/")) {  // 管理员可访问的接口
                        if (!Const.UserGroup.ADMIN_GROUP.equals(userGroup)) {
                            throw BusinessException.ofStatus(Status.FORBIDDEN);
                        }
                    } else {  // 普通用户可访问的接口
                        if (!Const.UserGroup.ADMIN_GROUP.equals(userGroup) && !Const.UserGroup.USER_GROUP.equals(userGroup)) {
                            throw BusinessException.ofStatus(Status.FORBIDDEN);
                        }
                    }
                } else {
                    throw BusinessException.ofStatus(Status.NOT_LOGIN);
                }
            }
            // 所有用户可访问的接口
            chain.doFilter(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ContextManager.removeContext();
        }
    }

}

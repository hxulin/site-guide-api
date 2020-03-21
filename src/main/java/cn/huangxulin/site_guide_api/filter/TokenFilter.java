package cn.huangxulin.site_guide_api.filter;

import cn.huangxulin.site_guide_api.bean.Const;
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
            if (!WHITELIST.contains(req.getRequestURI())) {



            }
            chain.doFilter(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ContextManager.removeContext();
        }
    }

}

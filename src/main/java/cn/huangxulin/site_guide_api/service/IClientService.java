package cn.huangxulin.site_guide_api.service;

import javax.servlet.http.HttpServletResponse;

/**
 * 功能描述:
 *
 * @author hxulin
 */
public interface IClientService {

    /**
     * 客户端下载
     *
     * @param nickname  用户昵称
     * @param authCode  授权码
     * @param response  响应
     */
    void download(String nickname, String authCode, HttpServletResponse response) throws Exception;

}

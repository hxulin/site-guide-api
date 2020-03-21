package cn.huangxulin.site_guide_api.service;

import cn.huangxulin.site_guide_api.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 功能描述:
 *
 * @author hxulin
 */
public interface IUserService extends IService<User> {

    /**
     * 更新局域网IP
     */
    void updateLanIp(String signature, String lanIp);

    /**
     * 根据登录口令获取用户组名
     *
     * @param password 登录口令
     * @return 用户组名
     */
    String findUserGroup(String password);

    /**
     * 用户登录
     *
     * @param password 登录口令
     * @return 登录凭证 token
     */
    String login(String password);

}

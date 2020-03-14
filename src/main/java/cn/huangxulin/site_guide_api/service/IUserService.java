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
}

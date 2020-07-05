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
     * 通过签名获取用户信息
     */
    User getBySignature(String signature);

    /**
     * 更新局域网IP
     */
    void updateLanIp(String signature, String lanIp);

    /**
     * 更新任务扫描心跳时间
     */
    void updateHeartbeatTime(Long uid);

}

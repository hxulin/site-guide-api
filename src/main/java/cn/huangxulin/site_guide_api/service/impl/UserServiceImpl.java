package cn.huangxulin.site_guide_api.service.impl;

import cn.huangxulin.site_guide_api.bean.Const;
import cn.huangxulin.site_guide_api.bean.Status;
import cn.huangxulin.site_guide_api.config.AppConfig;
import cn.huangxulin.site_guide_api.context.AppContext;
import cn.huangxulin.site_guide_api.entity.User;
import cn.huangxulin.site_guide_api.exception.BusinessExceptionAware;
import cn.huangxulin.site_guide_api.mapper.UserMapper;
import cn.huangxulin.site_guide_api.service.IUserService;
import cn.huangxulin.site_guide_api.util.AESUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 功能描述:
 *
 * @author hxulin
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService, BusinessExceptionAware {

    private UserMapper userMapper;

    private AppConfig appConfig = AppContext.getAppConfig();

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User getBySignature(String signature) {
        String nickname;
        try {
            nickname = AESUtils.decrypt(signature, appConfig.getSignatureAesKey(), appConfig.getSignatureAesIv());
        } catch (Exception e) {
            throw error(Status.FORBIDDEN.getCode(), "签名错误，拒绝访问");
        }
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("nickname", nickname));
        if (user == null) {
            throw error(Status.FORBIDDEN.getCode(), "用户信息不存在，请求被拒绝");
        }
        return user;
    }

    @Override
    public void updateLanIp(String signature, String lanIp) {
        User user = this.getBySignature(signature);
        if (user.getStatus() == Const.Status.DISABLE) {
            throw error(Status.FORBIDDEN.getCode(), "你的账号暂时被冻结");
        }
        user.setLastUpdated(new Date());
        user.setLanIp(lanIp);
        // 更新用户 IP 地址信息
        userMapper.updateById(user);
    }

    @Override
    public void updateHeartbeatTime(Long uid) {
        User user = new User();
        user.setId(uid);
        user.setLastHeartbeat(new Date());
        this.updateById(user);
    }
}

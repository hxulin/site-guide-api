package cn.huangxulin.site_guide_api.query;

import cn.huangxulin.site_guide_api.entity.User;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * 功能描述:
 *
 * @author hxulin
 */
@Getter
@Setter
public class UserQueryObject extends QueryObject<User> {

    // 昵称
    private String nickname;

    @Override
    protected void customizedQuery(LambdaQueryWrapper<User> wrapper) {
        wrapper.select(User::getId, User::getNickname, User::getLanIp,
                User::getLastUpdated, User::getStatus, User::getCreateTime, User::getUpdateTime);
        if (StringUtils.isNotBlank(nickname)) {
            wrapper.like(User::getNickname, nickname);
        }
        wrapper.orderByAsc(User::getCreateTime);
    }
}

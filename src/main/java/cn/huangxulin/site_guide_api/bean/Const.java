package cn.huangxulin.site_guide_api.bean;

/**
 * 功能描述: 系统数据常量
 *
 * @author hxulin
 */
public interface Const {

    /**
     * Token 的名称
     */
    String TOKEN_KEY = "Authorization";

    /**
     * 用户组
     */
    interface UserGroup {

        String DEFAULT_GROUP = "default";

        String ADMIN_GROUP = "admin";

        String USER_GROUP = "user";
    }

    /**
     * 状态
     */
    interface Status {
        int NORMAL = 0;
        int DISABLE = 1;
    }

}

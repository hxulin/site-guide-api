package cn.huangxulin.site_guide_api.exception;

import cn.huangxulin.site_guide_api.bean.Status;

/**
 * 功能描述: 异常增强接口
 *
 * @author hxulin
 */
public interface BusinessExceptionAware {

    default BusinessException error(String msg) {
        return BusinessException.ofMessage(msg);
    }

    default BusinessException error(Status status) {
        return BusinessException.ofStatus(status);
    }

    default BusinessException error(int code, String msg) {
        return BusinessException.of(code, msg);
    }
}

package cn.huangxulin.site_guide_api.exception;

import cn.huangxulin.site_guide_api.bean.Status;
import lombok.Getter;

/**
 * 功能描述: 业务异常
 *
 * @author hxulin
 */
@Getter
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private int code;

    private String msg;

    private BusinessException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return msg;
    }

    public static BusinessException ofMessage(String msg) {
        return of(Status.INTERNAL_SERVER_ERROR.getCode(), msg);
    }

    public static BusinessException ofStatus(Status status) {
        return of(status.getCode(), status.getMsg());
    }

    public static BusinessException of(int code, String msg) {
        return new BusinessException(code, msg);
    }

}

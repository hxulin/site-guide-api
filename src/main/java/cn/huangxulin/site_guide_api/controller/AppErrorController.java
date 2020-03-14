package cn.huangxulin.site_guide_api.controller;

import cn.huangxulin.site_guide_api.bean.ApiResponse;
import cn.huangxulin.site_guide_api.bean.Status;
import cn.huangxulin.site_guide_api.exception.BusinessException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

/**
 * 功能描述: 全局错误处理控制器
 *
 * @author hxulin
 */
@RestController
public class AppErrorController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    @RequestMapping(ERROR_PATH)
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse errorApiHandler(HttpServletRequest request) {
        Integer status = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (status == 400 || status == 404 || status == 405) {
            return ApiResponse.create(Status.getByCode(status));
        }
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        if (throwable != null) {

            while(throwable.getCause() != null) {
                throwable = throwable.getCause();
            }

            if (throwable instanceof BusinessException) {
                // 业务异常
                BusinessException ex = (BusinessException) throwable;
                return ApiResponse.create(ex.getCode(), ex.getMsg());
            } else if (throwable instanceof ConstraintViolationException) {
                // 请求参数校验异常
                return ApiResponse.create(Status.NOT_VALID_PARAM);
            }
        }
        return ApiResponse.fail();
    }

}

package com.cloud.common.exception;

import com.cloud.common.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理类
 */

@ControllerAdvice
public class GlobalExceptionHandler extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 业务异常
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(BusiException.class)
    @ResponseBody
    public String busiExcepitonHandler(HttpServletRequest request, BusiException e) {
        logger.error(e.getMessage(),e);
        return formatResponseParams(EXEC_ERROR,null,e.getMessage());
    }

    /**
     * 系统异常
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String defultExcepitonHandler(HttpServletRequest request, Exception e) {
        logger.error(e.getMessage(),e);
        return formatResponseParams(EXEC_ERROR,null,"系统繁忙,请稍后重试!");
    }

}

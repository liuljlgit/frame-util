package com.cloud.common.exception;

import com.cloud.common.webcomm.CodeEnum;
import com.cloud.common.webcomm.RespEntity;
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
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 默认异常
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String defultExcepitonHandler(HttpServletRequest request, Exception e) {
        logger.error(e.getMessage(),e);
        return RespEntity.commonResp(CodeEnum.EXEC_ERROR,null);
    }

    /**
     * 业务异常
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(BusiException.class)
    @ResponseBody
    public String busiExcepitonHandler(HttpServletRequest request, BusiException e) {
        Integer code = e.getCode() == null ? CodeEnum.EXEC_ERROR.getCode() : e.getCode();
        String msg = e.getMsg() == null ? CodeEnum.EXEC_ERROR.getMsg() : e.getMsg();
        logger.error(msg,e);
        return RespEntity.commonResp(code,msg,null);
    }

}

package com.dcode.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * dumb-code
 */
@ControllerAdvice
public class CommonExtHandler {

    private Logger logger = LoggerFactory.getLogger(CommonExtHandler.class);

    @ResponseBody
    @ExceptionHandler(value = BusinessException.class)
    public Response<?> handle(BusinessException businessException) {
        logger.error("操作异常",businessException);
        return Response.failed(businessException.getMessage());
    }

}

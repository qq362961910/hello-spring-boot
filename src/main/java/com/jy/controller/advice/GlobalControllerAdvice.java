package com.jy.controller.advice;

import com.jy.controller.result.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@ControllerAdvice
public class GlobalControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(GlobalControllerAdvice.class);

    /**
     * 404
     */
    @ResponseBody
    @ExceptionHandler({NoHandlerFoundException.class})
    public Object noHandlerFound(NoHandlerFoundException e) {
        logger.error("no handler found", e);
        return JsonResult.fail().setCode("404");
    }

    @ResponseBody
    @RequestMapping(value = "/404")
    public Object resourceNotFound(HttpServletRequest request, HttpServletResponse response) {
        logger.error("page not found, url: {}", request.getRequestURL());
        return JsonResult.fail().setCode("page_not_found");
    }

    /**
     * 500
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Object exception(Exception e) {
        if (e instanceof RuntimeException) {
            RuntimeException businessException = (RuntimeException)e;
            logger.error("business exception, error code: {}, error msg: {}", "businessException.getErrorCode()", "businessException.getErrorMessage()");
            return JsonResult.fail().setCode("businessException.getErrorCode()").setMsg("businessException.getErrorMessage()");
        }
        else {
            logger.error("runtime exception", e);
            return JsonResult.fail().setCode("500");
        }

    }

}

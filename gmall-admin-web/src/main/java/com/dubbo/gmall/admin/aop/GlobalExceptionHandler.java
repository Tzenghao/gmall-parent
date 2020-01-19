package com.dubbo.gmall.admin.aop;

import com.dubbo.gmall.to.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一处理所有异常，给前端返回500的json
 *      可以根据不同的异常进行不同的处理
 * @Author ZengHao
 * @Date 2020/1/14 9:49
 */
@ControllerAdvice //表示异常处理类
@Slf4j
//@ResponseBody
public class GlobalExceptionHandler {
    @ResponseBody //返回json
    @ExceptionHandler(value = ArithmeticException.class)  //标注处理什么异常
    public Object handlerException(Exception e){
        //getStackTrace获得堆栈信息
        log.error("系统全局异常感知，信息:{}",e.getStackTrace());
        return new CommonResult().validateFailed("算术运算异常...");
    }
    @ResponseBody
    @ExceptionHandler(value = NullPointerException.class)  //标注处理什么异常
    public Object handlerException0(Exception e){
        log.error("系统全局异常感知，信息:{}",e.getStackTrace());
        return new CommonResult().validateFailed("空指针异常...");
    }
}

package com.dubbo.gmall.admin.aop;

import com.dubbo.gmall.to.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

/**
 * 切面编写
 *      1、需要导入依赖spring-boot-starter-aop切面场景
 *      2、编写切面
 *          1）添加注解
 *          2）切入点表达式
 *          3）通知
 *              前置通知：方法执行之前触发
 *              后置通知：方法执行之后触发
 *              返回通知：方法正常返回之后触发
 *              异常通知：方法出现异常触发
 *           正常执行： 前置通知==》返回通知==》后置通知
 *           异常执行： 前置通知==》异常通知==》后置通知
 *              环绕通知：4合一,拦截方法的执行
 * @Author ZengHao
 * @Date 2020/1/13 17:00
 */

/**
 * 利用aop完成统一的数据校验，数据校验出错就返回前端错误提示（这里使用环绕通知处理）
 */
@Aspect
@Component
@Slf4j
public class DataVaildAspect {
    //目标方法的异常一般都需要再次抛出，让别人感知
    //第一个*表示可以是任意的返回值。后面的表示匹配admin下的任意包下的任意一个以Controller结尾的类里的任意方法,并且是任意的参数都可以。
    @Around("execution(* com.dubbo.gmall.admin..*Controller.*(..))") //环绕注解及它的通知表达式
    public Object vaildAround(ProceedingJoinPoint point){
        Object proceed = null;
        try {
            //获得目标方法的值
            Object[] args = point.getArgs();
            for (Object obj : args) {
                if (obj instanceof BindingResult){
                    BindingResult r = (BindingResult) obj;
                    if (r.getErrorCount()>0){
                        //框架自动校验检测到错误,正常返回
                        return new CommonResult().validateFailed(r);
                    }
                }
            }
            log.debug("校验切面介入工作....");
            //System.out.println("前置通知的位置");
            //就是我们反射的 method.invoke();
            proceed = point.proceed(point.getArgs());
            log.debug("校验切面将目标方法已经放行....{}",proceed);
            //System.out.println("返回通知的位置");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            //System.out.println("异常通知的位置");
            throw new RuntimeException(throwable);
        }finally {
            //System.out.println("后置通知的位置");
        }
        return proceed;
    }
}

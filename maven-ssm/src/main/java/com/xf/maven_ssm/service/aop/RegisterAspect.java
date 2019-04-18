package com.xf.maven_ssm.service.aop;

import com.xf.maven_ssm.service.ItemsService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * 注册切面
 */
@Aspect
@Component
public class RegisterAspect {
    private static final Logger logger = LoggerFactory.getLogger(RegisterAspect.class);

//    通知执行模型
//
//    try{
//        try{
//            //@Before
//            method.invoke(..);
//        }finally{
//            //@After
//        }
//        //@AfterReturning
//    }catch(){
//        //@AfterThrowing
//    }

    /**
     * 前置通知：目标方法执行之前执行以下方法体的内容
     * @param jp
     */
    @Before("execution(* com.xf.maven_ssm.service.impl.ItemsServiceImpl.findById(..))")
    public void beforeMethod(JoinPoint jp){
        String methodName = jp.getSignature().getName();
        System.out.println("【前置通知】the method 【" + methodName + "】 begins with " + Arrays.asList(jp.getArgs()));
    }

    /**
     * 返回通知：目标方法正常执行完毕时执行以下代码
     * @param jp 接入点
     * @param result 目标方法的返回结果
     */
    @AfterReturning(value="execution(* com.xf.maven_ssm.service.impl.ItemsServiceImpl.findById(..))",returning="result")
    public void afterReturningMethod(JoinPoint jp, Object result){
        String methodName = jp.getSignature().getName();
        System.out.println("【返回通知】the method 【" + methodName + "】 ends with 【" + result + "】");
    }

    /**
     * 后置通知：目标方法执行之后执行以下方法体的内容，不管是否发生异常。
     * @param jp
     */
    @After("execution(* com.xf.maven_ssm.service.impl.ItemsServiceImpl.findById(..))")
    public void afterMethod(JoinPoint jp){
        System.out.println("【后置通知】this is a afterMethod advice...");
    }

    /**
     * 异常通知：目标方法发生异常的时候执行以下代码
     */
    @AfterThrowing(value="execution(* com.xf.maven_ssm.service.impl.ItemsServiceImpl.findById(..))",throwing="e")
    public void afterThorwingMethod(JoinPoint jp, NullPointerException e){
        String methodName = jp.getSignature().getName();
        System.out.println("【异常通知】the method 【" + methodName + "】 occurs exception: " + e);
    }

    /**
     * 环绕通知：目标方法执行前后分别执行一些代码，发生异常的时候执行另外一些代码
     * @return
     */
    /*@Around(value="execution(* com.xf.maven_ssm.service.impl.ItemsServiceImpl.findById(..))")
    public Object aroundMethod(ProceedingJoinPoint jp){
        String methodName = jp.getSignature().getName();
        Object result = null;
        try {
            System.out.println("【环绕通知中的--->前置通知】：the method 【" + methodName + "】 begins with " + Arrays.asList(jp.getArgs()));
            //执行目标方法
            result = jp.proceed();
            System.out.println("【环绕通知中的--->返回通知】：the method 【" + methodName + "】 ends with " + result);
        } catch (Throwable e) {
            System.out.println("【环绕通知中的--->异常通知】：the method 【" + methodName + "】 occurs exception " + e);
        }

        System.out.println("【环绕通知中的--->后置通知】：-----------------end.----------------------");
        return result;
    }*/

}
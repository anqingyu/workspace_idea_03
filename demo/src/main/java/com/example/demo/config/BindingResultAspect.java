package com.example.demo.config;

import com.example.demo.domain.common.ResultObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * Hibernate Validator 校验框架的配置类
 */
@Aspect
@Component
@Order(2)
public class BindingResultAspect {
    @Pointcut("execution(public * com.example.demo.controller.*.*(..))")
    public void BindingResult() {
    }

    @Around("BindingResult()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof BindingResult) {
                BindingResult result = (BindingResult) arg;
                if (result.hasErrors()) {
                    FieldError fieldError = result.getFieldError();
                    if(fieldError!=null){
                        return ResultObject.validateFailed(fieldError.getDefaultMessage());
                    }else{
                        return ResultObject.validateFailed();
                    }
                }
            }
        }
        return joinPoint.proceed();
    }
}

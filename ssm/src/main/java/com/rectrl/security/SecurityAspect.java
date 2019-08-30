package com.rectrl.security;

import com.rectrl.service.AuthService;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author zc
 * @version 1.0 2017-09-03
 * @title 权限校验切面类
 * @describe
 */
// 声明为一个切面
@Aspect
@Component
public class SecurityAspect {

    @Autowired
    private AuthService authService;

    // 使用要拦截标注有AdminOnly的注解进行操作
    @Pointcut("@annotation(AdminOnly)")
    public void adminOnly() {
        System.out.println("切入点");
    }

    @Before("adminOnly()")
    public void check() {
        System.out.println("before");
        authService.checkAccess();
    }

    @After("adminOnly()")
    public void after() {
        System.out.println("after");
    }

}

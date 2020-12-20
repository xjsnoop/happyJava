package cn.xujian.aop;

import org.aspectj.lang.ProceedingJoinPoint;

//切面类，就是增强方法的类
public class MyAspect {
    public void before(){
        System.out.println("前置增强方法");
    }
    public void after(){
        System.out.println("后置增强");
    }
    //ProceedingJoinPoint   正在执行的切入点
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("环绕前增强");
        Object proceed = proceedingJoinPoint.proceed();//切入点方法
        System.out.println("环绕后增强");
        return proceed;
    }
    //异常抛出增强
    public void afterThrowing(){
        System.out.println("异常抛出");
    }
}

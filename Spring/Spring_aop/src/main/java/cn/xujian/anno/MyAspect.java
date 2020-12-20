package cn.xujian.anno;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

//切面类，就是增强方法的类
@Component("myAspect")  //注入spring
@Aspect   //标注当前类是一个切面类
public class MyAspect {
    @Before("execution(void cn.xujian.anno.Target.*(..))")
    public void before(){
        System.out.println("前置增强方法");
    }

    //ProceedingJoinPoint   正在执行的切入点
    @Around("execution(void cn.xujian.anno.Target.*(..))")
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
    //定义一个切点表达式   切点表达式抽取
    @Pointcut("execution(void cn.xujian.anno.Target.*(..))")
    public void pointcut(){
    }
    @After("pointcut()")
    public void after(){
        System.out.println("后置增强");
    }
}

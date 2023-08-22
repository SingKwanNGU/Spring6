package com.atguigu.spring6.aop.annoaop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

//切面类
@Aspect//切面类
@Component//ioc容器
public class LogAspect {
    //设置切入点和通知类型
    //切入点表达式：execution（）
    //通知类型：前置@Before() 返回@AfterReturning 异常@AfterThrowing 后置@After 环绕@Around
//    前置@Before()
        @Before(value = "execution(public int com.atguigu.spring6.aop.annoaop.CalculatorImpl.*(..))")
        public  void beforeMethod(JoinPoint joinPoint){
            String methodName = joinPoint.getSignature().getName();
            Object[] args = joinPoint.getArgs();
            System.out.println("Logger-->前置通知,方法名称:"+methodName+",参数:"+ Arrays.toString(args));
        }
//    返回@AfterReturning
        @AfterReturning(value = "execution(* com.atguigu.spring6.aop.annoaop.CalculatorImpl.*(..))",returning = "result")
        public void afterReturningMethod(JoinPoint joinPoint,Object result){
            String methodName = joinPoint.getSignature().getName();
            System.out.println("Logger-->返回通知,方法名称"+methodName+",返回结果:"+result);
}
//    异常@AfterThrowing
        @AfterThrowing(value = "execution(* com.atguigu.spring6.aop.annoaop.CalculatorImpl.*(..))",throwing = "ex")
        public void afterThrowingMethod(JoinPoint joinPoint,Throwable ex){
            String methodName = joinPoint.getSignature().getName();
            System.out.println("Logger-->异常通知,方法名称:"+methodName+",异常信息:"+ex);
}
//    后置@After
        @After(value = "pointcut()")
        public void afterMethod(JoinPoint joinPoint){
            String methodName = joinPoint.getSignature().getName();
            System.out.println("Logger-->后置通知,方法名称:"+methodName);
        }
//    环绕@Around
        @Around(value = "com.atguigu.spring6.aop.annoaop.LogAspect.pointcut()")
        public Object aroundMethod(ProceedingJoinPoint joinPoint){
            String methodName = joinPoint.getSignature().getName();
            Object[] args = joinPoint.getArgs();
            String argString = Arrays.toString(args);
            Object result=null;
            try {
                System.out.println("环绕通知==目标方法之前");
                result = joinPoint.proceed();
                System.out.println("环绕通知==目标方法返回值之后");
            }catch (Throwable throwable){
                throwable.printStackTrace();
                System.out.println("环绕通知==目标方法出现异常执行");
            }finally {
                System.out.println("环绕通知==目标方法执行完毕");
            }
            return result;
        }
        @Pointcut(value = "execution(* com.atguigu.spring6.aop.annoaop.CalculatorImpl.*(..))")
        public void pointcut(){

        }
}
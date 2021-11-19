//package com.example.permissions;
//
//import android.app.Fragment;
//import android.content.Context;
//import android.util.Log;
//
//import com.hzhu.aop.HhzDataAPI;
//import com.hzhu.permissions.PermissionCanceled;
//import com.hzhu.permissions.PermissionInterface;
//import com.hzhu.permissions.PermissionReject;
//import com.example.permissions.PermissionRequestActivity;
//import com.hzhu.permissions.RequestPermission;
//import com.hzhu.permissions.bean.Cancel;
//import com.hzhu.permissions.bean.Reject;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.util.List;
//
///**
// * Created by zouxipu on 2021/3/16.
// * zouxipu@haohaozhu.com
// * Traveling Light
// */
//@Aspect
//public class HzPermission {
//
//    Context ctx;
//    private static final String PERMISSION_REQUEST_POINTCUT = "execution(@com.hzhu.permissions.RequestPermission * *(..))";
//
//    //
//    @Pointcut(PERMISSION_REQUEST_POINTCUT + " && @annotation(requestPermission)")
//    public void requestPermissionMethod(RequestPermission requestPermission) {
//
//    }
//
//    @Around("requestPermissionMethod(requestPermission)")
//    public void AroundJoinPoint(final ProceedingJoinPoint joinPoint, RequestPermission requestPermission) {
//        final Object object = joinPoint.getThis();
//        if (object == null) return;
//
//        if (object instanceof Context) {
//            ctx = (Context) object;
//        } else if (object instanceof Fragment) {
//            ctx = ((Fragment) object).getActivity();
//        } else {
//            //获取切入点方法上的参数列表
//            Object[] objects = joinPoint.getArgs();
//            if (objects.length > 0) {
//                //非静态方法且第一个参数为context
//                if (objects[0] instanceof Context) {
//                    ctx = (Context) objects[0];
//                } else {
//                    //没有传入context 默认使用application
//                    ctx = HhzDataAPI.getApp();
//                }
//            } else {
//                ctx = HhzDataAPI.getApp();
//            }
//
//        }
//        if (ctx == null || requestPermission == null) return;
//        PermissionRequestActivity.requestPermission(ctx, requestPermission.permissions(), requestPermission.requestCode(), new PermissionInterface() {
//            @Override
//            public void PermissionGranted() {
//                try {
//                    Log.i("zouxipu", "切面通过");
//                    joinPoint.proceed();
//                } catch (Throwable throwable) {
//                    throwable.printStackTrace();
//                }
//            }
//
//            @Override
//            public void PermissionCanceled(int requestCode) {
//                Log.i("zouxipu", "切面取消");
//
//                Class<?> cls = object.getClass();
//                Method[] methods = cls.getDeclaredMethods();
//                if (methods.length == 0) return;
//                for (Method method : methods) {
//                    //过滤不含自定义注解PermissionCanceled的方法
//                    boolean isHasAnnotation = method.isAnnotationPresent(PermissionCanceled.class);
//                    if (isHasAnnotation) {
//                        method.setAccessible(true);
//                        //获取方法类型
//                        Class<?>[] types = method.getParameterTypes();
//                        if (types.length != 1) return;
//                        //获取方法上的注解
//                        PermissionCanceled aInfo = method.getAnnotation(PermissionCanceled.class);
//                        if (aInfo == null) return;
//                        //解析注解上对应的信息
//                        Cancel bean = new Cancel();
////                        bean.setContext(ctx);
//                        bean.setRequestCode(requestCode);
//                        try {
//                            method.invoke(object, bean);
//                        } catch (IllegalAccessException e) {
//                            e.printStackTrace();
//                        } catch (InvocationTargetException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//
//            @Override
//            public void PermissionReject(int requestCode, List<String> strings) {
//                Log.i("zouxipu", "切面拒绝" + strings.toString());
//
//                Class<?> cls = object.getClass();
//                Method[] methods = cls.getDeclaredMethods();
//                if (methods.length == 0) return;
//                for (Method method : methods) {
//                    //过滤不含自定义注解PermissionDenied的方法
//                    boolean isHasAnnotation = method.isAnnotationPresent(PermissionReject.class);
//                    if (isHasAnnotation) {
//                        method.setAccessible(true);
//                        //获取方法类型
//                        Class<?>[] types = method.getParameterTypes();
//                        if (types.length != 1) return;
//                        //获取方法上的注解
//                        PermissionReject aInfo = method.getAnnotation(PermissionReject.class);
//                        if (aInfo == null) return;
//                        //解析注解上对应的信息
//                        Reject bean = new Reject();
//                        bean.setRequestCode(requestCode);
////                        bean.setContext(ctx);
//                        bean.setRejectList(strings);
//                        try {
//                            method.invoke(object, bean);
//                        } catch (IllegalAccessException e) {
//                            e.printStackTrace();
//                        } catch (InvocationTargetException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        });
//
//    }
//}

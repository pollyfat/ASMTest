package com.example.permissions;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.annotation.Keep;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Created by zouxipu on 2020/6/5.
 * zouxipu@haohaozhu.com
 * Traveling Light
 */
@Keep
public class HhzDataAPI {
    private final String TAG = "aop";
    public static final String SDK_VERSION = "1.0.0";
    private static HhzDataAPI INSTANCE;
    private static final Object mLock = new Object();
    private static Map<String, Object> mDeviceInfo;
    private String mDeviceId;
    private static Application sApplication;

    @Keep
    @SuppressWarnings("UnusedReturnValue")
    public static HhzDataAPI init(Application application) {
        if (sApplication == null) {
            if (application == null) {
                sApplication = getApplicationByReflect();
            } else {
                sApplication = application;
            }
        } else {
            if (application != null && application.getClass() != sApplication.getClass()) {
                sApplication = application;
            }
        }
        synchronized (mLock) {
            if (null == INSTANCE) {
                INSTANCE = new HhzDataAPI();
            }
            return INSTANCE;
        }
    }

    @Keep
    public static HhzDataAPI getInstance() {
        return INSTANCE;
    }

    public static Application getApp() {
        if (sApplication != null) return sApplication;
        Application app = getApplicationByReflect();
        init(app);
        return app;
    }


    /**
     * ？
     *
     * @return
     */
    private static Application getApplicationByReflect() {
        try {
            @SuppressLint("PrivateApi")
            Class<?> activityThread = Class.forName("android.app.ActivityThread");
            Object thread = activityThread.getMethod("currentActivityThread").invoke(null);
            Object app = activityThread.getMethod("getApplication").invoke(thread);
            if (app == null) {
                throw new NullPointerException("u should init first");
            }
            return (Application) app;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        throw new NullPointerException("u should init first");
    }

}

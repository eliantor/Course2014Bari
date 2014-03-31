package me.aktor.corsobari.app.utils;

import android.content.Intent;
import android.os.UserHandle;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by eto on 25/03/14.
 */
public class ReflectUtil {
    public static final String METHOD_NAME = "startActivityAsUser";

    private static final Method METHOD;
    private static final Class<?> CLAZZ;

    static {
        try {

            Class<?> actClz = Class.forName("android.app.Activity");
            Method m =actClz.getMethod(METHOD_NAME, Intent.class, UserHandle.class);
            m.setAccessible(true);
            CLAZZ = actClz;
            METHOD = m;
        } catch (ClassNotFoundException e) {
            throw new Error();
        } catch (NoSuchMethodException e) {
            throw new Error();
        }
    }


}

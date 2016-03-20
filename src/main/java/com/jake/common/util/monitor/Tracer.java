package com.jake.common.util.monitor;

import com.jake.common.util.StringUtils;
import org.slf4j.Logger;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 监控输出器接口
 * Created by Jake on 3/20 0020.
 */
public abstract class Tracer {

    /** 一次取值时间间隔 */
    public static final int TRACE_INTERVAL = 2000;

    /**
     * 此方法用于实现控制输出内容
     */
    public abstract void run() throws IOException, InterruptedException;

    /**
     * 输出mBean的可用数据
     * @param mXbean Object
     * @param logger Logger
     */
    public void printAll(Object mXbean, Logger logger) {
        // for
        for (Method method : mXbean.getClass().getDeclaredMethods()) {
            method.setAccessible(true);
            if (method.getName().startsWith("get")
                    && Modifier.isPublic(method.getModifiers())) {
                Object value;
                try {
                    value = method.invoke(mXbean);
                } catch (Exception e) {
                    value = e;
                } // try
                if (method.getName().endsWith("Size") &&
                        value instanceof Number) {
                    logger.info(method.getName().substring(3) + ": " +
                            StringUtils.formatFileSize(Long.valueOf(value.toString()), false));
                } else {
                    logger.info(method.getName().substring(3) + ": " + value);
                }
            } // if
        }
    }

    // 一次取值时间间隔
    protected void sleep() throws InterruptedException {
//      testSort();
        Thread.sleep(TRACE_INTERVAL);
    }

    // for test
    protected void testSort() {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0;i < 10000;i++) {
            list.add(new Random().nextInt(10000));
            Collections.sort(list);
        }
    }
}

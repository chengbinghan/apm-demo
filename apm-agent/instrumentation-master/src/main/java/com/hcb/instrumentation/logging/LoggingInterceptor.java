/*
 * Copyright 2014 brutusin.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hcb.instrumentation.logging;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.hcb.commons.json.spi.JsonCodec;
import com.hcb.commons.utils.FileUtils;
import com.hcb.instrumentation.Interceptor;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

public class LoggingInterceptor extends Interceptor {

    private File rootFile;
    private final Map<String, Long> startMap = new HashMap();

    @Override
    public void init(String arg) throws Exception {
        if (arg == null) {
            throw new IllegalArgumentException(LoggingInterceptor.class.getCanonicalName() + " failed. Argument required specifying the value of logging root-path");
        }
        this.rootFile = new File(arg);
        if (!rootFile.exists()) {
            FileUtils.forceMkdir(rootFile);
        }
        System.err.println("[LoggingInterceptor agent] Logging to " + rootFile);
    }

    @Override
    public boolean interceptClass(String className, byte[] byteCode) {
        if (className.endsWith("Bootstrap111111111111111111") || className.endsWith("SimpleClass")
                ) {
            return true;
        }
        return false;
    }

    @Override
    public boolean interceptMethod(ClassNode cn, MethodNode mn) {
/*        if ("org/apache/catalina/startup/Bootstrap".equals(cn.name) && "main".equals(mn.name)) {
            return true;
        }*/


        return true;
    }

    @Override
    protected void doOnStart(Object source, Object[] arg, String executionId) {
        long start = System.currentTimeMillis();
        startMap.put(executionId, start);
        File file = getFile(source, executionId);

        trace(file, "#Source: " + source);
        trace(file, "#Start: " + new Date(start));
        trace(file, "#Parameters:");
        trace(file, toString(arg));
    }

    @Override
    protected void doOnThrowableThrown(Object source, Throwable throwable, String executionId) {
    }

    @Override
    protected void doOnThrowableUncatched(Object source, Throwable throwable, String executionId) {
        long start = startMap.remove(executionId);

        File file = getFile(source, executionId);
        trace(file, "#Elapsed: " + (System.currentTimeMillis() - start) + " ms");
        trace(file, "#Thrown:");
        trace(file, toString(throwable));
    }

    @Override
    protected void doOnFinish(Object source, Object result, String executionId) {
        long start = startMap.remove(executionId);
        File file = getFile(source, executionId);
        trace(file, "#Elapsed: " + (System.currentTimeMillis() - start) + " ms");
        trace(file, "#Returned:");
        trace(file, toString(result));
    }

    private static void trace(File f, String s) {
        if (s == null) {
            return;
        }
        try {
            FileOutputStream fos = new FileOutputStream(f, true);
            try {
                fos.write(s.getBytes());
                fos.write("\n".getBytes());
            } finally {
                fos.close();
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private File getFile(Object source, String executionId) {
        String loggingFolderPath = "JVM-" + ManagementFactory.getRuntimeMXBean().getStartTime() + "/" + Thread.currentThread().getName() + "-" + Thread.currentThread().getId() + "/" + executionId + "-";
        if (source instanceof Method) {
            Method m = (Method) source;
            loggingFolderPath += m.getDeclaringClass().getName() + "." + m.getName() + "()";
        } else if (source instanceof Constructor) {
            Constructor c = (Constructor) source;
            String className = c.getDeclaringClass().getName();
            if (className != null && className.length() > 0) {
                loggingFolderPath += className + ".init()";
            } else {
                loggingFolderPath += "init()";
            }
        } else {
            loggingFolderPath += source;
        }
        loggingFolderPath += ".log";
        loggingFolderPath = loggingFolderPath.replaceAll("[<>:]", "-");
        File ret = new File(rootFile, loggingFolderPath);
        try {
            FileUtils.forceMkdir(ret.getParentFile());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return ret;
    }

    private static String toString(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return JsonCodec.getInstance().transform(obj);
        } catch (Throwable th) {
            return obj.toString();
        }
    }
}

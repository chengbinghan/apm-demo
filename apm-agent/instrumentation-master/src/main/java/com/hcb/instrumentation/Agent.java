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
package com.hcb.instrumentation;

import com.hcb.instrumentation.transform.ParamTransForm;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;

public class Agent {

    private static int counter;

    /**
     * @param agentArgs       eg:-javaagent:myagent.jar=mypackage.MyInterceptor;an_interceptor_optional_parameter
     * @param instrumentation
     * @throws InstantiationException
     */
    public static void premain(final String agentArgs, Instrumentation instrumentation) throws InstantiationException {
        counter++;
        final String callbackId = String.valueOf(counter);
        try {
            if (agentArgs == null) {
                throw new IllegalArgumentException("Agent argument is required of the form 'interceptor-class-name[;interceptor-custom-args]'");
            }
            String[] tokens = agentArgs.split(";", 2);
            Class<?> clazz = Agent.class.getClassLoader().loadClass(tokens[0]);
            final Interceptor interceptor = (Interceptor) clazz.newInstance();
            if (tokens.length == 2) {
                interceptor.init(tokens[1]);
            } else {
                interceptor.init(null);
            }
            Callback.registerCallback(callbackId, interceptor);

            final ParamTransForm paramTransForm = new ParamTransForm(interceptor, callbackId);
            instrumentation.addTransformer(paramTransForm);

        } catch (Throwable th) {
            th.printStackTrace(System.err);
        }
    }

    private static boolean isAncestor(ClassLoader ancestor, ClassLoader cl) {
        if (ancestor == null || cl == null) {
            return false;
        }
        if (ancestor.equals(cl)) {
            return true;
        }
        return isAncestor(ancestor, cl.getParent());
    }
}

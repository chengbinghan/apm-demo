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
package com.hcb.commons;

/**
 * Object wrapper. Useful when accessing and modifying values from anonymous classes.
 * <pre>
Example:
    {@code 

    final Bean<Boolean> onFinishCalled = new Bean<Boolean>();
    
    LineReader lr = new LineReader(createInputStream()) {
     
        protected void processLine(String line) throws Exception {}
        
        protected void onExceptionFound(Exception ex) {}

        protected void onFinish() {
            // Asserts only called once
            assertNull(onFinishCalled.getValue());
            onFinishCalled.setValue(Boolean.TRUE);
        }
    };
    lr.run();}
* </pre>

 */
public class Bean<T> {

    private T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        if (value == null) {
            return "null";
        }
        return value.toString();
    }
}

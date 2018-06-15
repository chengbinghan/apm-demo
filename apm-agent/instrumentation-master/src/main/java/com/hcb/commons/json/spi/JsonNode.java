/*
 * Copyright 2015 brutusin.org
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
package com.hcb.commons.json.spi;

import java.util.Iterator;


public interface JsonNode {

    public enum Type {

        OBJECT,
        ARRAY,
        BOOLEAN,
        NULL,
        INTEGER,
        NUMBER,
        STRING,
        ANY
    }

    public Type getNodeType();

    public Boolean asBoolean();

    public Integer asInteger();

    public Long asLong();

    public Double asDouble();

    public String asString();

    public int getSize();

    public JsonNode get(int i);

    public Iterator<String> getProperties();

    public JsonNode get(String property);

}

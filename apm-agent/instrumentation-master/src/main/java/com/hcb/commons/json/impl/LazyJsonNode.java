/*
 * Copyright 2015 Ignacio del Valle Alles idelvall@brutusin.org.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hcb.commons.json.impl;

import java.util.Iterator;

import com.hcb.commons.json.ParseException;
import com.hcb.commons.json.spi.JsonCodec;
import com.hcb.commons.json.spi.JsonNode;


public final class LazyJsonNode implements JsonNode {

    private final String json;
    private volatile JsonNode jsonNode;

    public LazyJsonNode(String json) {
        this.json = json;
    }

    @Override
    public Type getNodeType() {
        return getJsonNode().getNodeType();
    }

    @Override
    public Boolean asBoolean() {
        return getJsonNode().asBoolean();
    }

    @Override
    public Integer asInteger() {
        return getJsonNode().asInteger();
    }

    @Override
    public Long asLong() {
        return getJsonNode().asLong();
    }

    @Override
    public Double asDouble() {
        return getJsonNode().asDouble();
    }

    @Override
    public String asString() {
        return getJsonNode().asString();
    }

    @Override
    public int getSize() {
        return getJsonNode().getSize();
    }

    @Override
    public JsonNode get(int i) {
        return getJsonNode().get(i);
    }

    @Override
    public Iterator<String> getProperties() {
        return getJsonNode().getProperties();
    }

    @Override
    public JsonNode get(String property) {
        return getJsonNode().get(property);
    }

    public JsonNode getJsonNode() {
        if (jsonNode == null) {
            synchronized (this) {
                if (jsonNode == null) {
                    try {
                        this.jsonNode = JsonCodec.getInstance().parse(json);
                    } catch (ParseException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        }
        return jsonNode;
    }

    @Override
    public String toString() {
        return json;
    }
}

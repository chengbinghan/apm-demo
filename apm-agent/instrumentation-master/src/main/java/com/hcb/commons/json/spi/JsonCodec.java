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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

import com.hcb.commons.json.ParseException;


public abstract class JsonCodec implements JsonDataCodec, JsonSchemaCodec {

    private static JsonCodec instance;

    static {
        ServiceLoader<JsonCodec> sl = ServiceLoader.load(JsonCodec.class);
        Iterator<JsonCodec> it = sl.iterator();
        List<JsonCodec> instances = new ArrayList<JsonCodec>();
        while (it.hasNext()) {
            instances.add(it.next());
        }
        if (instances.isEmpty()) {
            throw new Error("No '" + JsonCodec.class.getSimpleName() + "' service provider found.");
        } else if (instances.size() > 1) {
            throw new Error("Multiple '" + JsonCodec.class.getSimpleName() + "' service providers found: " + instances);
        } else {
            instance = instances.get(0);
        }
    }

    @Override
    public final String getSchemaString(Class<?> clazz, String title, String description) {
        String ret = getSchemaString(clazz);
        if (ret != null && (title != null || description != null)) {
            StringBuilder sb = new StringBuilder(ret.trim());
            if (description != null) {
                sb.insert(1, "\"description\":\"" + quoteAsUTF8(description) + "\",");
            }
            if (title != null) {
                sb.insert(1, "\"title\":\"" + quoteAsUTF8(title) + "\",");
            }
            ret = sb.toString();
        }
        return ret;
    }

    @Override
    public JsonSchema getSchema(Class clazz) {
        try {
            return parseSchema(getSchemaString(clazz));
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public <T> T load(JsonNode node, Class<T> clazz) {
        try {
            return parse(node.toString(), clazz);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static JsonCodec getInstance() {
        return instance;
    }
}

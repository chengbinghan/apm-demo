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
package com.hcb.commons.json.util;

import com.hcb.commons.json.spi.JsonNode;


public class JsonSchemaUtils {

    /**
     * Returns the type of the elements of map (an object with
     * additionalProperties of type specified) schema.
     *
     * @param schema
     * @return null if is not map schema
     */
    public static JsonNode.Type getMapValueType(JsonNode schema) {
        JsonNode.Type type = JsonNode.Type.valueOf(schema.get("type").asString().toUpperCase());
        JsonNode child;
        if (type == JsonNode.Type.OBJECT && (child = schema.get("additionalProperties")) != null) {
            return getFirstNonArrayValueType(child);
        }
        return null;
    }

    /**
     * Returns the type of the elements of an array.
     *
     * @param schema
     * @return null if is not map schema
     */
    public static JsonNode.Type getArrayValueType(JsonNode schema) {
        JsonNode.Type type = JsonNode.Type.valueOf(schema.get("type").asString().toUpperCase());
        JsonNode child;
        if (type == JsonNode.Type.ARRAY && (child = schema.get("items")) != null) {
            return getFirstNonArrayValueType(child);
        }
        return null;
    }

    /**
     * 
     * @param schema
     * @return 
     */
    public static JsonNode.Type getFirstNonArrayValueType(JsonNode schema) {
        JsonNode.Type type = JsonNode.Type.valueOf(schema.get("type").asString().toUpperCase());
        JsonNode child;
        if (type == JsonNode.Type.ARRAY && (child = schema.get("items")) != null) {
            return getFirstNonArrayValueType(child);
        } else {
            return type;
        }
    }

}

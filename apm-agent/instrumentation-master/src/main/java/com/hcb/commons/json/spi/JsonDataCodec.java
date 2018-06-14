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

import com.hcb.commons.json.ParseException;


public interface JsonDataCodec {

    public String quoteAsUTF8(String s);

    public <T> T parse(String json, Class<T> clazz) throws ParseException;

    public <T> T load(JsonNode node, Class<T> clazz);

    public JsonNode parse(String json) throws ParseException;

    public String transform(Object o);

    public String prettyPrint(String json) throws ParseException;
}

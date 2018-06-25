package com.apusic.agent.core;

/**
 * @author ChengBing Han
 * @date 14:02  2018/6/22
 * @description
 */
public class ParamHandler {


    public void handlerParam(String str, Object obj) {

        if (obj instanceof Integer) {
            str += " param1:" + obj;

        }
        if (obj instanceof String) {
            str += " param1:" + obj;
        }

        ResultCollection.list.add(str);

    }
}

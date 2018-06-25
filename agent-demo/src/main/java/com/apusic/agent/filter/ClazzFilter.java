package com.apusic.agent.filter;

import com.apusic.agent.context.DetectorContext;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author ChengBing Han
 * @date 10:46  2018/6/22
 * @description
 */
public class ClazzFilter {

    public static boolean filterClazz(String str) {
        final CopyOnWriteArrayList<String> list = DetectorContext.list;
        final Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            final String next = iterator.next();
            if (next == null) {
                continue;
            }
            if (next.contains(str)) {
                return true;
            }
        }
        return false;

    }

}

package com.apusic.agent.util;

/**
 * @author ChengBing Han
 * @date 10:27  2018/6/22
 * @description
 */
public class StringUtil {
    private StringUtil(){}


    public static boolean isEmpty(String str){

      if(str == null){
          return true;
      }

      if("".equals(str.trim())){
          return true;
      }
      return false;
    }
}

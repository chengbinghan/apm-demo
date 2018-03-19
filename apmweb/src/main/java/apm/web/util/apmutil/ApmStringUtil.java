package apm.web.util.apmutil;

/**
 * @author ChengBing Han
 * @date 17:36  2018/1/27
 * @description String 相关处理类
 */
public class ApmStringUtil {
    private ApmStringUtil(){}

    public static boolean isEmpty(String string){
        if(string == null || "".equals(string)){
            return true;
        }
        return false;
    }
}

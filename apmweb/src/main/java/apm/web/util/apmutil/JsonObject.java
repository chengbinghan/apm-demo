package apm.web.util.apmutil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zy
 * @Description: 返回结果对象
 * @Date: 2017/12/24
 */
public class JsonObject<T> {


    /**
     * 是否成功
     */
    private boolean isSuccess;

    /**
     * 错误信息
     */
    private String errMsg;

    /**
     * 响应编码
     */
    private String responeCode;


    /**
     * 返回list集合
     */
    private List<T> objectList;

    /**
     * 返回map集合
     */
    private Map<Object, Object> objectMaps;

    /**
     * 返回对象
     */
    private T obj;

    /**
     * 其他信息
     */
    private static Map<Integer, String> maps = new HashMap<Integer, String>();

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = "error: " + errMsg;
    }

    public String getResponeCode() {
        return responeCode;
    }

    public void setResponeCode(Integer responseCode) {
        String str = maps.get(responseCode);
        if(str != null){

        this.responeCode = str;
        }else {
            this.responeCode = "other";
        }

    }



    public List<T> getObjectList() {
        return objectList;
    }

    public void setObjectList(List<T> objectList) {
        this.objectList = objectList;
    }

    public Map<Object, Object> getObjectMaps() {
        return objectMaps;
    }

    public void setObjectMaps(Map<Object, Object> objectMaps) {
        this.objectMaps = objectMaps;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public JsonObject() {}

    static {
        maps.put(200, "ok");
        maps.put(201, "Created");
        maps.put(202, "Accepted");
        maps.put(203, "Non-Authoritative Information");
        maps.put(204, "No Content");
        maps.put(205, "Reset Content");
        maps.put(206, "Partial Content");
        maps.put(207, "Multi-Status");
        maps.put(300, "Multiple Choices");
        maps.put(301, "Moved Permanently");
        maps.put(302, "Move temporarily");
        maps.put(303, "See Other");
        maps.put(304, "Not Modified");
        maps.put(305, "Use Proxy");
        maps.put(306, "Switch Proxy");
        maps.put(307, "Temporary Redirect");
        maps.put(400, "Bad Request");
        maps.put(401, "Unauthorized");
        maps.put(402, "Payment Required");
        maps.put(403, "Forbidden");
        maps.put(404, "Not Found");
        maps.put(405, "Not Acceptable");
        maps.put(406, "Unauthorized");
        maps.put(407, "Proxy Authentication Required");
        maps.put(401, "Unauthorized");
        maps.put(408, "Request Timeout");
        maps.put(409, "Conflict");
        maps.put(410, "Gone");
        maps.put(411, "Length Required");
        maps.put(412, "Precondition Failed");
        maps.put(413, "Request Entity Too Large");
        maps.put(414, "Request-URI Too Long");
        maps.put(415, "Requested Range Not Satisfiable");
        maps.put(416, "Request Entity Too Large");
        maps.put(417, "Expectation Failed");
        maps.put(421, "There are too many connections from your internet address");
        maps.put(422, "Unprocessable Entity");
        maps.put(423, "Locked");
        maps.put(424, "Failed Dependency");
        maps.put(425, "Unordered Collection");
        maps.put(426, "Upgrade Required");
        maps.put(449, "Retry With");
        maps.put(451, "Unavailable For Legal Reasons");
        maps.put(500, "Internal Server Error");
        maps.put(501, "Not Implemented");
        maps.put(502, "Bad Gateway");
        maps.put(503, "Service Unavailable");
        maps.put(504, "Gateway Timeout");
        maps.put(505, "HTTP Version Not Supported");
        maps.put(506, "Variant Also Negotiates");
        maps.put(507, "Insufficient Storage");
        maps.put(509, "Bandwidth Limit Exceeded");
        maps.put(510, "Not Extended");
        maps.put(600, "Unparseable Response Headers");

        maps.put(1000, "Can't Get Message");
        maps.put(1001, "Operate failed");
        maps.put(1002, "Insert data failed");
        maps.put(1003, "输入的范围不满足要求，请确认");
        maps.put(1004,"该数据已经存在");
    }


   
}

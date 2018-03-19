package apm.web.beans.vo;

/**
 * @author ChengBing Han
 * @date 22:34  2018/1/31
 * @description 数据库响应时间曲线图,w
 */
public class DatabaseResponseVO {
    //某条sql执行时间
    private Long executeTime;
    //某条sql消耗的时间
    private Long elapsed;
    //sql 信息
    private String sqlInfo;
    //执行sql的api 信息
    private String apiInfo;
    //rpc
    private String rpc;

    public String getSimpleApiInfo() {
        /**
         * apiInfo 有两种情况
         * 1、com.mysql.jdbc.ConnectionImpl.prepareStatement(java.lang.String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability)
         * 2、Tomcat Servlet Asynchronous Process
         * 对于第一种情况，取为c.m.j.C.prepareStatement
         *      二     ，取前50个字符，如果多的用...代替后面的
         *
         */
        String myApiInfo = apiInfo;

        StringBuilder sb = new StringBuilder();
        sb.append(rpc).append(":");
        if (apiInfo.contains("(")) {
            myApiInfo = apiInfo.substring(0, apiInfo.indexOf('('));
            String[] packageArr = myApiInfo.split("\\.");
            for (int i = 0; i < packageArr.length; i++) {
                if (i < packageArr.length - 1) {
                    sb.append(packageArr[i].charAt(0)).append(".");

                } else {
                    sb.append(packageArr[i]);
                }
            }
        } else {
            if (myApiInfo.length() >= 30) {
                sb.append(myApiInfo.substring(0, 30)).append("...");
            } else {
                sb.append(myApiInfo);
            }

        }


        sb.append("/").append(getSqlInfo().substring(0,6));

        return sb.toString();
    }
    public String getSpanEventId() {
        return spanEventId;
    }

    public void setSpanEventId(String spanEventId) {
        this.spanEventId = spanEventId;
    }

    private  String spanEventId;
    public Long getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Long executeTime) {
        this.executeTime = executeTime;
    }

    public Long getElapsed() {
        return elapsed;
    }

    public void setElapsed(Long elapsed) {
        this.elapsed = elapsed;
    }

    public String getSqlInfo() {
        return sqlInfo;
    }

    public void setSqlInfo(String sqlInfo) {
        this.sqlInfo = sqlInfo;
    }

    public String getApiInfo() {
        return apiInfo;
    }

    public void setApiInfo(String apiInfo) {
        this.apiInfo = apiInfo;
    }

    public String getRpc() {
        return rpc;
    }

    public void setRpc(String rpc) {
        this.rpc = rpc;
    }
}

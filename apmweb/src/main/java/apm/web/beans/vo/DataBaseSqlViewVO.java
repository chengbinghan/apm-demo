package apm.web.beans.vo;

/**
 * @author ChengBing Han
 * @date 13:05  2018/1/30
 * @description 数据库==数据库一览 VO 对象
 */
public class DataBaseSqlViewVO {

    private String rpc;
    private String apiInfo;
    private String sqlInfo;
    private Integer sqlId;
    private String totalTime;

    public Integer getSqlId() {
        return sqlId;
    }

    public void setSqlId(Integer sqlId) {
        this.sqlId = sqlId;
    }


    public String getApiInfo() {
        return apiInfo;
    }

    public String getRpc() {
        return rpc;
    }

    public void setRpc(String rpc) {
        this.rpc = rpc;
    }

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

       if(getSqlInfo() != null){
        sb.append("/").append(getSqlInfo().substring(0,6));

       }

        return sb.toString();
    }

    public void setApiInfo(String apiInfo) {
        this.apiInfo = apiInfo;
    }

    public String getSqlInfo() {
        return sqlInfo;
    }

    public void setSqlInfo(String sqlInfo) {
        this.sqlInfo = sqlInfo;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }
}

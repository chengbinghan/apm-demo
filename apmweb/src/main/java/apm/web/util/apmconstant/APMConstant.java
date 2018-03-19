package apm.web.util.apmconstant;

/**
 * @author ChengBing Han
 * @date 11:23  2018/2/1
 * @description
 */
public class APMConstant {

    //时间轴，用于前端作图，时间轴分为几个区间
    public static final Integer TIME_FENCE_COUNT = 8;
    //默认查询错误条数,用户未指明一次一次查询多少条错误。默认值
    public static final Integer DEFAULT_ERROR_COUNT = 30;


    /*
    apdex 值相关常量
     */
    //apdex 计算说明
    /*
   Apdex值，是一个APM领域的计算公式，内容如下：
   首先确保	求的响应时间，制定三个响应时间区间：0~3秒、3~12秒、大于12秒，分别代表“满意”、“容忍”、“失望”，统计一段时间内某个请求发生的次数（通过在span表中按照endTimeDelta字段统计），在span表中查出请求响应时间，然后将请求次数分散到三个响应时间区间里面，通过如下公司计算apdex值：
   Apdex指数 = (1*满意数+0.5*容忍数)/请求总数
   例如：如果30分钟内某个请求发生了10次：6次满意，2次容忍，2次失望，则apdex值为
   apdex = (1*6 + 0.5*2)/10 = 0.7   (apdex范围为0~1，0代表最失望，1代表最满意)
   最后按照从小到大排序，显示apdex最低的几个事务。
   */

    //返回时间在0.3以内为满意
    public static final Double SATISFACTION_TIME = 0.3;
    //返回时间在0.3-0.7 合格，大于0.7为失望
    public static final Double QUALIFIED_TIME = 0.7;


    /*
    常用参数，作为map的key,定义为常量
     */
    public static final String APP_ID = "appId";
    public static final String CURRENT_TIME_MILLISECOND = "currentTime";
    public static final String TIME_SECTION = "timeSection";
    public static final String APDEX_QUALIFIED_TIME = "qualifiedTime";
    public static final String APDEX_SATISFACTION_TIME = "satisfactionTime";
    public static final String DATABASE_SPAN_ERRORCODE = "spanErrorCode";

    public static final String RPC="rpc";

    /*
    定义应用列表详情查询的时间限制常量，后期可以是一个接口来设置查询时间。
     */
    //默认引用列表，详情查询时间限制常量，默认1小时
    // TODO: 2018/3/7  测试期间，该值设置为10万小时，正式测试改为1小时的毫秒值 
    public static final Long DEFAULT_APP_INFO_TIMESECTION = 100000 * 60 * 60 * 1000L;
    // TODO: 2018/3/7  测试期间，该值设置为10万小时，正式测试改为1小时的毫秒值
    public static final Long DEFAULT_TRANSACTION_TIMESECTION = 100000 * 60 * 60 * 1000L;

    // TODO: 2018/3/7  测试期间，该值设置为10万小时，正式测试改为1小时的毫秒值
    //调用拓扑表格，时间
    public static final Long DEFAULT_APP_INVOKE_TIMESECTION = 100000 * 60 * 60 * 1000L;


    /*
     noSql 相关常量之redis 类型
     */
    public static final String NOSQL_REDIS_TYPE = "REDIS";

    /*
    NOSQL 常量类型之 MEMCACHED
     */
    public static final String NOSQL_MEMCACHED_TYEP = "MEMCACHED";

    /*
    默认NOSQL,查询时间范围
     */
    // TODO: 2018/3/7  测试期间，该值设置为10万小时，正式测试改为1小时的毫秒值
    public static final Long DEFAULT_NOSQL_TIME_SECTION = 100000 * 60 * 60 * 1000L;


}

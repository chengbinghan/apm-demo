package apm.web.core.plugin;

import apm.web.core.properties.ApmProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ChengBing Han
 * @date 15:21  2018/3/9
 * @description 每个ServiceCode 都有一定的范围,
 *
 */
public class SingletonServiceCode {

    public static final String SERVER = "server";
    public static final String DB_CLIENT = "db";
    public static final String CACHE_CLIENT = "cache";
    public static final String RPC_CLIENT = "rpc";
    public static final String OTHER = "others";

    private static volatile SingletonServiceCode serviceCode = null;


    static Logger logger = LoggerFactory.getLogger(SingletonServiceCode.class);

    public  Map<String, Scope> serviceCodeScopeMap;


    public static SingletonServiceCode getInstance() {
        if (serviceCode == null) {

            synchronized (ApmProperties.class) {
                if (serviceCode == null) {
                    serviceCode = new SingletonServiceCode();
                }
            }
        }
        return serviceCode;

    }

    private SingletonServiceCode() {


        try {
            serviceCodeScopeMap = new HashMap<>();
            ApmProperties apmProperties = ApmProperties.getApmProperties();

            String server = (String) apmProperties.get(SERVER);
            String[] serverScopeArr = server.split(",");
            String serverStart = serverScopeArr[0].trim();
            String serverEnd = serverScopeArr[1].trim();
            Scope serverScope = new Scope();
            serverScope.setStart(Integer.parseInt(serverStart));
            serverScope.setEnd(Integer.parseInt(serverEnd));

            serviceCodeScopeMap.put(SERVER, serverScope);


            String db = (String) apmProperties.get(DB_CLIENT);
            String[] dbScopeArr = db.split(",");
            String dbStart = dbScopeArr[0].trim();
            String dbEnd = dbScopeArr[1].trim();
            Scope dbScope = new Scope();
            dbScope.setStart(Integer.parseInt(dbStart));
            dbScope.setEnd(Integer.parseInt(dbEnd));
            serviceCodeScopeMap.put(DB_CLIENT, dbScope);


            String cache = (String) apmProperties.get(CACHE_CLIENT);
            String[] cacheArr = cache.split(",");
            String cacheStart = cacheArr[0].trim();
            String cacheEnd = cacheArr[1].trim();
            Scope cacheScope = new Scope();
            cacheScope.setStart(Integer.parseInt(cacheStart));
            cacheScope.setEnd(Integer.parseInt(cacheEnd));
            serviceCodeScopeMap.put(CACHE_CLIENT, cacheScope);

            String rpc = (String) apmProperties.get(RPC_CLIENT);
            String[] rpcArr = rpc.split(",");
            String rpcStart = rpcArr[0].trim();
            String rpcEnd = rpcArr[1].trim();
            Scope rpcScope = new Scope();
            rpcScope.setStart(Integer.parseInt(rpcStart));
            rpcScope.setEnd(Integer.parseInt(rpcEnd));
            serviceCodeScopeMap.put(RPC_CLIENT, rpcScope);

            String other = (String) apmProperties.get(OTHER);
            String[] otherArr = other.split(",");
            String otherStart = otherArr[0].trim();
            String otherEnd = otherArr[1].trim();
            Scope otherScope = new Scope();
            otherScope.setStart(Integer.parseInt(otherStart));
            otherScope.setEnd(Integer.parseInt(otherEnd));
            serviceCodeScopeMap.put(OTHER, otherScope);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }





}

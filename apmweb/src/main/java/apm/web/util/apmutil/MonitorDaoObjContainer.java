package apm.web.util.apmutil;

import apm.web.dao.apmmysql.ApmTransactionDao;
import apm.web.dao.apmmysql.MonitorProDao;
import apm.web.dao.apmmysql.UserDao;
import org.springframework.stereotype.Component;

/**
 * @author ChengBing Han
 * @date 16:27  2018/2/17
 * @description 多线程spring 的@Resource 不会注入， 同时mybatis 面向接口， 无法用new。 定义一个容器，在多线程之前获取注入的对象。
 */

public class MonitorDaoObjContainer {



    private MonitorProDao monitorProDao;
    private UserDao userDao;
    private ApmTransactionDao apmTransactionDao;


    public ApmTransactionDao getApmTransactionDao() {
        return apmTransactionDao;
    }

    public void setApmTransactionDao(ApmTransactionDao apmTransactionDao) {
        this.apmTransactionDao = apmTransactionDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public MonitorProDao getMonitorProDao() {
        return monitorProDao;
    }

    public void setMonitorProDao(MonitorProDao monitorProDao) {
        this.monitorProDao = monitorProDao;
    }
}

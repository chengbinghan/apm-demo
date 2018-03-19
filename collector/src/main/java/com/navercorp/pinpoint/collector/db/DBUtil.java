package com.navercorp.pinpoint.collector.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/** 
*
* 数据库工具类
* 
* @author liaomingyao
* @date 2017/10/12
* 
*/
public class DBUtil {

	//获取数据库连接
	public static Connection getConnection(){
		 MyDataSource myDataSource = MyDataSource.getInstance();
	     Connection conn = myDataSource.getConnection();
	     return conn;
	}
	
	//释放数据库资源
	public static void release(ResultSet rs, PreparedStatement ps, Connection conn){  
		MyDataSource myDataSource = MyDataSource.getInstance();
		myDataSource.release(rs, ps, conn);
    }  
	
}

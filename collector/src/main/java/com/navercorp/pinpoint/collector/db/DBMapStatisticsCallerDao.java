package com.navercorp.pinpoint.collector.db;

import com.navercorp.pinpoint.common.trace.ServiceType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBMapStatisticsCallerDao {

	public void insertApiMetaDataToDB(ServiceType callerServiceType, ServiceType calleeServiceType, boolean isError){
		if(callerServiceType == null || calleeServiceType == null){
    		return;
    	}
    	
    	String sql = "insert into map_statistics_caller(timestamp,caller_code,caller_desc,callee_code,callee_desc,isError) values(?,?,?,?,?,?)";
    	Connection conn = null;
    	PreparedStatement ps = null;
    	try {
    		conn = DBUtil.getConnection();
    		ps = conn.prepareStatement(sql);
    		
    		//Setup sql parameters
    		ps.setLong(1, System.currentTimeMillis());
    		ps.setInt(2, callerServiceType.getCode());
    		ps.setString(3, callerServiceType.getDesc());
    		ps.setInt(4, calleeServiceType.getCode());
    		ps.setString(5, calleeServiceType.getDesc());
    		ps.setInt(6, isError?1:0); 
    		
    		ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, ps, conn);
		}
	}
	
}

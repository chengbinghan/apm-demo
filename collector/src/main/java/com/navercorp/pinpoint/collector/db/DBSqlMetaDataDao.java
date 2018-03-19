package com.navercorp.pinpoint.collector.db;

import com.navercorp.pinpoint.thrift.dto.TSqlMetaData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBSqlMetaDataDao {

	//Insert sql data info to mysql database.
    public void insertSqlMetaDataToDB(TSqlMetaData sqlMetaData){
    	    	
    	String sql = "insert into sql_meta_data_ver2(agentId,agentStartTime,sqlId,sqlInfo) values(?,?,?,?)";
    	Connection conn = null;
    	PreparedStatement ps = null;
    	try {
    		conn = DBUtil.getConnection();
    		ps = conn.prepareStatement(sql);
    		
    		//Setup sql parameters
    		ps.setString(1, sqlMetaData.getAgentId());
    		ps.setLong(2, sqlMetaData.getAgentStartTime());
    		ps.setInt(3, sqlMetaData.getSqlId());
    		ps.setString(4, sqlMetaData.getSql());
    		
    		ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, ps, conn);
		}
    }
	
}

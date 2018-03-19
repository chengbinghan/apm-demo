package com.navercorp.pinpoint.collector.db;

import com.navercorp.pinpoint.thrift.dto.TApiMetaData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBApiMetaDataDao {

	public void insertApiMetaDataToDB(TApiMetaData apiMetaData){
		if(apiMetaData == null){
    		return;
    	}
		//首先查询该方法是否已经存入数据库，如果已经存入数据库，则不做插入操作
    	String sql_query = "select * from api_meta_data where apiId=?";
    	String sql = "insert into api_meta_data(agentId,agentStartTime,apiId,apiInfo,line,type) values(?,?,?,?,?,?)";
    	Connection conn = null;
    	PreparedStatement ps_query = null;
    	PreparedStatement ps_insert = null;
    	ResultSet rs = null;
    	try {
    		conn = DBUtil.getConnection();
    		ps_query = conn.prepareStatement(sql_query);
    		ps_query.setInt(1, apiMetaData.getApiId());
    		rs = ps_query.executeQuery(); 
    		if(!rs.next()){
    			ps_insert = conn.prepareStatement(sql);
        		//Setup sql parameters
    			ps_insert.setString(1, apiMetaData.getAgentId());
    			ps_insert.setLong(2, apiMetaData.getAgentStartTime());
    			ps_insert.setInt(3, apiMetaData.getApiId());
    			ps_insert.setString(4, apiMetaData.getApiInfo());
    			ps_insert.setInt(5, apiMetaData.getLine());
    			ps_insert.setInt(6, apiMetaData.getType());
    			ps_insert.execute();
    		}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, ps_query, null);
			DBUtil.release(rs, ps_insert, conn);
		}
	}
	
}

package com.navercorp.pinpoint.collector.db;

import com.navercorp.pinpoint.thrift.dto.TStringMetaData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBStringMetaDataDao {

	 //Insert String data info to mysql database.
    public void insertStringMetaDataToDB(TStringMetaData stringMetaData){
    	    	
    	String sql = "insert into string_meta_data(agentId,agentStartTime,stringId,stringValue) values(?,?,?,?)";
    	Connection conn = null;
    	PreparedStatement ps = null;
    	try {
    		conn = DBUtil.getConnection();
    		ps = conn.prepareStatement(sql);
    		
    		//Setup sql parameters
    		ps.setString(1, stringMetaData.getAgentId());
    		ps.setLong(2, stringMetaData.getAgentStartTime());
    		ps.setInt(3, stringMetaData.getStringId());
    		ps.setString(4, stringMetaData.getStringValue());
    		
    		ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(null, ps, conn);
		}
    }
	
}

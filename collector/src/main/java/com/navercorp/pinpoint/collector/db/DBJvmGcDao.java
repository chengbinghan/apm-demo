package com.navercorp.pinpoint.collector.db;

import com.navercorp.pinpoint.common.server.bo.stat.JvmGcBo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DBJvmGcDao {

	public void insertCpuLoadToDB(List<JvmGcBo> jvmGcBos){
		if(jvmGcBos == null){
    		return;
    	}
    	
    	String sql = "insert into jvm_gc(agentId,startTimestamp,heapUsed,heapMax,timestamp) values(?,?,?,?,?)";
    	Connection conn = null;
    	PreparedStatement ps = null;
    	try {
    		conn = DBUtil.getConnection();
    		ps = conn.prepareStatement(sql);
    		//批量插入
            int len = jvmGcBos.size();
        	for(int i=0;i<len;i++){
        		JvmGcBo jvmGcBo = jvmGcBos.get(i);
        		if(jvmGcBo != null){
        			long timestamp = jvmGcBo.getTimestamp();
        			ps.setString(1, jvmGcBo.getAgentId());
        			ps.setLong(2, jvmGcBo.getStartTimestamp());
        			ps.setLong(3, jvmGcBo.getHeapUsed());
        			ps.setLong(4, jvmGcBo.getHeapMax());
        			ps.setLong(5, timestamp);
        			ps.addBatch();
        		}
        		//每次批量插入100条记录
        		if((i+1)%50 == 0){
                    ps.executeBatch();
                }
        	}
        	//执行剩余的
        	ps.executeBatch();
        	
    	} catch(SQLException e){
    		e.printStackTrace();
    	} finally{
    		DBUtil.release(null, ps, conn);
    	}
	}
	
}

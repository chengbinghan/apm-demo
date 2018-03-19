package com.navercorp.pinpoint.collector.db;

import com.navercorp.pinpoint.common.server.bo.stat.CpuLoadBo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DBCpuLoadDao {

	public void insertCpuLoadToDB(List<CpuLoadBo> cpuLoadBos){
		if(cpuLoadBos == null){
    		return;
    	}
    	
    	String sql = "insert into cpu_load(agentId,startTimestamp,timestamp,jvmCpuLoad,systemCpuLoad) values(?,?,?,?,?)";
    	Connection conn = null;
    	PreparedStatement ps = null;
    	try {
    		conn = DBUtil.getConnection();
    		ps = conn.prepareStatement(sql);
    		//批量插入
            int len = cpuLoadBos.size();
        	for(int i=0;i<len;i++){
        		CpuLoadBo cpuLoadBo = cpuLoadBos.get(i);
        		if(cpuLoadBo != null){
        			ps.setString(1, cpuLoadBo.getAgentId());
        			ps.setLong(2, cpuLoadBo.getStartTimestamp());
        			ps.setLong(3, cpuLoadBo.getTimestamp());
        			ps.setDouble(4, cpuLoadBo.getJvmCpuLoad());
        			ps.setDouble(5, cpuLoadBo.getSystemCpuLoad());
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

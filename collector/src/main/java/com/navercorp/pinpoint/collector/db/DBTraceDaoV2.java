package com.navercorp.pinpoint.collector.db;

import com.navercorp.pinpoint.common.server.bo.AnnotationBo;
import com.navercorp.pinpoint.common.server.bo.SpanBo;
import com.navercorp.pinpoint.common.server.bo.SpanEventBo;
import com.navercorp.pinpoint.common.server.bo.serializer.trace.v2.SpanEncoder;
import com.navercorp.pinpoint.common.trace.AnnotationKey;
import com.navercorp.pinpoint.common.util.TransactionId;
import com.navercorp.pinpoint.thrift.dto.TIntStringStringValue;

import java.sql.*;
import java.util.List;

public class DBTraceDaoV2 {

	//Insert span data info to mysql database.
    public void insertSpanToDB(SpanBo spanBo){
    	    	
    	StringBuffer sb = new StringBuffer();
    	sb.append("insert into span(transactionId,");
    	sb.append("agentId,");
    	sb.append("agentStartTime,");
    	sb.append("transactionSequence,");
    	sb.append("acceptedTime,");
    	sb.append("type,");
    	sb.append("applicationId,");
    	sb.append("version,");
    	sb.append("serviceType,");
    	sb.append("parentSpanId,");
    	sb.append("startTime,");
        sb.append("elapsed,");
		sb.append("rpc,");
		sb.append("endPoint,");
		sb.append("remoteAddr,");
		sb.append("apiId,");
		sb.append("errCode,");
		sb.append("exceptionId,");
		sb.append("exceptionMessage,");
		sb.append("flag,");
		sb.append("loggingTransactionInfo,");
		sb.append("acceptorHost) ");
	    sb.append("values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
    	
    	Connection conn = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	try {
    		conn = DBUtil.getConnection();
    		ps = conn.prepareStatement(sb.toString(), Statement.RETURN_GENERATED_KEYS);
    		
    		String transactionId = "";
    		TransactionId transaction = spanBo.getTransactionId();
    		long transactionSequence = 0;
    		if(transaction !=null ){
    			transactionSequence = transaction.getTransactionSequence();
    			transactionId = transaction.getAgentId() + transaction.getAgentStartTime() + transactionSequence;
    		}
    		long agentStartTime = spanBo.getAgentStartTime();
    		//Setup sql parameters
    		ps.setString(1, transactionId);
    		ps.setString(2, spanBo.getAgentId());
    		ps.setLong(3, agentStartTime);
    		ps.setLong(4, transactionSequence);
    		ps.setLong(5, spanBo.getCollectorAcceptTime());
    		ps.setInt(6, SpanEncoder.TYPE_SPAN);
    		ps.setString(7, spanBo.getApplicationId());
    		ps.setInt(8, spanBo.getVersion());
    		ps.setInt(9, spanBo.getServiceType());
    		ps.setLong(10, spanBo.getParentSpanId());
    		ps.setLong(11, spanBo.getStartTime());
    		ps.setInt(12, spanBo.getElapsed());
    		ps.setString(13, spanBo.getRpc());
    		ps.setString(14, spanBo.getEndPoint());
    		ps.setString(15, spanBo.getRemoteAddr());
    		ps.setInt(16, spanBo.getApiId());
    		ps.setInt(17, spanBo.getErrCode());
    		ps.setInt(18,spanBo.getExceptionId());
    		ps.setString(19, spanBo.getExceptionMessage());
    		ps.setInt(20, spanBo.getFlag());
    		ps.setInt(21, spanBo.getLoggingTransactionInfo());
    		ps.setString(22, spanBo.getAcceptorHost());
    		
    		ps.executeUpdate();
    		
    		//获取数据库自动生成的主键
    		long spanId = 0;
    		rs = ps.getGeneratedKeys();
    		if(rs.next()){
                 spanId = rs.getLong(1); 
            }
    		
    		//插入span对应的spanevents记录
    		List<SpanEventBo> spanEventBos = spanBo.getSpanEventBoList();
    		if(spanEventBos != null){
    			insertSpanEventsToDB(spanEventBos, spanId, transactionId);
    		}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(rs, ps, conn);
		}
    }

    //Insert span_events data info to mysql database.
    public void insertSpanEventsToDB(List<SpanEventBo> spanEventBos, long spanId, String transactionId){
    	
    	StringBuffer sb = new StringBuffer();
    	sb.append("insert into span_event(");
    	sb.append("transactionId,");
    	sb.append("spanId,");
    	sb.append("startElapsed,");
    	sb.append("endElapsed,");
    	sb.append("sequence,");
    	sb.append("depth,");
    	sb.append("serviceType,");
    	sb.append("rpc,");
    	sb.append("endPoint,");
    	sb.append("destinationId,");
    	sb.append("apiId,");
    	sb.append("sqlId,");
    	sb.append("nextSpanId,");
    	sb.append("exceptionId,");
    	sb.append("exceptionMessage,");
    	sb.append("nextAsyncId) ");
    	sb.append("values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
    	
    	Connection conn = null;
    	PreparedStatement ps = null;
    	try {
    		conn = DBUtil.getConnection();
    		ps = conn.prepareStatement(sb.toString());
    		
    		//获取 spanevent 的个数
        	int len = spanEventBos.size();
        	
        	for(int i=0;i<len;i++){
        		SpanEventBo spanEventBo = spanEventBos.get(i);
        		if(spanEventBo != null){
        			ps.setString(1, transactionId);
        			ps.setLong(2, spanId);
        			ps.setInt(3, spanEventBo.getStartElapsed());
        			ps.setInt(4, spanEventBo.getEndElapsed());
        			ps.setInt(5, spanEventBo.getSequence());
        			ps.setInt(6, spanEventBo.getDepth());
        			ps.setInt(7, spanEventBo.getServiceType());
        			ps.setString(8, spanEventBo.getRpc());
        			ps.setString(9, spanEventBo.getEndPoint());
        			ps.setString(10, spanEventBo.getDestinationId());
        			ps.setInt(11, spanEventBo.getApiId());
        			ps.setInt(12, getSqlIdFromSpanEvent(spanEventBo));
        			ps.setLong(13, spanEventBo.getNextSpanId());
        			ps.setInt(14, spanEventBo.getExceptionId());
        			ps.setString(15, spanEventBo.getExceptionMessage());
        			ps.setInt(16, spanEventBo.getNextAsyncId());
        			ps.addBatch();
        		}
        		
        		//每次批量插入100条记录
        		if((i+1)%50 == 0){
                    ps.executeBatch();
                }
        	}
        	//执行剩余的
        	ps.executeBatch();
            //conn.commit();
        	
    	} catch(SQLException e){
    		e.printStackTrace();
    	} finally{
    		DBUtil.release(null, ps, conn);
    	}
    }
    
    //获取sqlId
    public int getSqlIdFromSpanEvent(SpanEventBo spanEventBo){
    	int sqlId = 0;
    	List<AnnotationBo> annotationBos = spanEventBo.getAnnotationBoList();
    	if(annotationBos != null){
    		int len = annotationBos.size();
    		for(int i=0; i<len; i++){
    			AnnotationBo annotationBo = annotationBos.get(i);
    			if(annotationBo != null){
    				int key = annotationBo.getKey();
    				if(key == AnnotationKey.SQL_ID.getCode()){
    					Object value = annotationBo.getValue();
        				if(value!=null && (value instanceof TIntStringStringValue)){
        					sqlId = ((TIntStringStringValue)value).getIntValue();
        					break;
        				}
    				}
    			}
    		}
    	}
    	return sqlId;
    }
	
}

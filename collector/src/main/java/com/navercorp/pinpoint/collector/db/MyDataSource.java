package com.navercorp.pinpoint.collector.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class MyDataSource{

	//单例模式
	private static MyDataSource instance;
	
	//数据库连接池 
    private LinkedList<Connection> pool;
    
    //默认的等待超时时间
    private long default_timeout = 5000;
    
    //默认的连接池大小
    private int default_maxsize = 50;
    
    private Properties prop;
    
    public synchronized static MyDataSource getInstance(){
    	if(instance == null){
    		instance = new MyDataSource();
    	}
    	return instance;
    }
    
    private MyDataSource(){
    	if(pool != null){
			return;
		}
		prop = loadJdbcConfig("database.properties"); 
		if(prop == null){
			return;
		}
		//获取配置文件中的数据库连接信息
		String driverClassName = prop.getProperty("db_driver");
		String dataBaseUrl = prop.getProperty("db_url");
		String userName = prop.getProperty("db_username");
		String password = prop.getProperty("db_password");
		
		int maxsize = default_maxsize;
		String str_maxsize = prop.getProperty("db_maxsize");
		if(str_maxsize != null){
			maxsize = Integer.parseInt(str_maxsize);
		}
		
		pool = new LinkedList<Connection>();  
		
		try {
			Class.forName(driverClassName);
			for(int i = 0; i < maxsize; i++){  
                Connection conn = DriverManager.getConnection(dataBaseUrl, userName, password);
                pool.addLast(conn);       
            }  
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public Properties loadJdbcConfig(String filename){  
    	InputStream in = null;  
        try {  
            in = MyDataSource.class.getClassLoader().getResourceAsStream(filename);
            Properties prop = new Properties();  
            prop.load(in);  
            return prop;  
        }catch(IOException e){
        	e.printStackTrace();
        	return null;
        }finally{  
            if(in != null){  
                try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}  
            }  
        }
    }
    
	public Connection getConnection() {		
		synchronized (pool) {  
            if(pool.size() == 0){  
                try {
                	long timeout = default_timeout;
                	String str_timeout = prop.getProperty("db_timeout");
                	if(str_timeout != null){
                		timeout = Long.parseLong(str_timeout);
                	}
                    pool.wait(timeout);
                } catch (InterruptedException e) {  
                	e.printStackTrace();
                    return null;
                }  
            }
            Connection conn = pool.removeFirst();
            return new ConnectionWrapper(conn, pool);  
        }  
	}

	public void release(ResultSet rs, PreparedStatement ps,	Connection connection) {
		if(rs != null){
            try {
                rs.close();
                rs = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
          
        if(ps != null){
            try {
                ps.close();
                ps = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        if(connection != null){
            try {
            	connection.close();
            	connection = null;
            } catch (SQLException e) {
                e.printStackTrace();  
            }
        }
	}
	
	//包装一个自定义的连接类，采用的是代理模式
	class ConnectionWrapper implements Connection {  
		  
        private Connection conn;  
        
        //Connection pool  
        private LinkedList<Connection> pool;  
        
        public void close() throws SQLException {  
            synchronized (pool) {  
                pool.addLast(conn);  
                pool.notify();  
            }  
        }
          
        public ConnectionWrapper(Connection conn, LinkedList<Connection> pool) {  
            this.conn = conn;
            this.pool = pool;
        }
        
        public <T> T unwrap(Class<T> iface) throws SQLException {  
            return conn.unwrap(iface);
        }
  
        public boolean isWrapperFor(Class<?> iface) throws SQLException {  
            return conn.isWrapperFor(iface);
        }
  
        public Statement createStatement() throws SQLException {  
            return conn.createStatement();
        }
  
        public PreparedStatement prepareStatement(String sql)  throws SQLException {  
            return conn.prepareStatement(sql);
        }
  
        public CallableStatement prepareCall(String sql) throws SQLException {  
            return conn.prepareCall(sql);
        }
  
        public String nativeSQL(String sql) throws SQLException {  
            return conn.nativeSQL(sql);
        }
  
        public void setAutoCommit(boolean autoCommit) throws SQLException {  
            conn.setAutoCommit(autoCommit);
        }
  
        public boolean getAutoCommit() throws SQLException {  
            return conn.getAutoCommit();
        }
  
        public void commit() throws SQLException {  
            conn.commit();
        }
  
        public void rollback() throws SQLException {  
            conn.rollback();
        }
  
        public boolean isClosed() throws SQLException {  
            return conn.isClosed();
        }
  
        public DatabaseMetaData getMetaData() throws SQLException {  
            return conn.getMetaData();
        }
  
        public void setReadOnly(boolean readOnly) throws SQLException {  
            conn.setReadOnly(readOnly);
        }
  
        public boolean isReadOnly() throws SQLException {  
            return conn.isReadOnly();
        }
  
        public void setCatalog(String catalog) throws SQLException {  
            conn.setCatalog(catalog);
        }
  
        public String getCatalog() throws SQLException {  
            return conn.getCatalog();
        }
  
        public void setTransactionIsolation(int level) throws SQLException {  
            conn.setTransactionIsolation(level);
        }
  
        public int getTransactionIsolation() throws SQLException {  
            return conn.getTransactionIsolation();
        }
  
        public SQLWarning getWarnings() throws SQLException {  
            return conn.getWarnings();
        }
  
        public void clearWarnings() throws SQLException {  
            conn.clearWarnings();
        }
  
        public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {  
            return conn.createStatement(resultSetType, resultSetConcurrency);  
        }  
  
        public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {  
            return conn.prepareStatement(sql, resultSetConcurrency);  
        }  
  
        public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {  
            return conn.prepareCall(sql, resultSetType, resultSetConcurrency);  
        }  
  
        public Map<String, Class<?>> getTypeMap() throws SQLException {  
            return conn.getTypeMap();  
        }  
  
        public void setTypeMap(Map<String, Class<?>> map) throws SQLException {  
            conn.setTypeMap(map);  
        }  
  
        public void setHoldability(int holdability) throws SQLException {  
            conn.setHoldability(holdability);  
        }  
  
        public int getHoldability() throws SQLException {  
            return conn.getHoldability();  
        }  
  
        public Savepoint setSavepoint() throws SQLException {  
            return conn.setSavepoint();  
        }  
  
        public Savepoint setSavepoint(String name) throws SQLException {  
            return conn.setSavepoint(name);  
        }  
  
        public void rollback(Savepoint savepoint) throws SQLException {  
            conn.rollback(savepoint);  
        }  
  
        public void releaseSavepoint(Savepoint savepoint) throws SQLException {  
            conn.releaseSavepoint(savepoint);  
        }  
  
        public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {  
            return conn.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);  
        }  
  
        public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {  
            return conn.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);  
        }  
  
        public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {  
            return conn.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);  
        }  
  
        public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
            return conn.prepareStatement(sql, autoGeneratedKeys);  
        }  
  
        public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
              return conn.prepareStatement(sql, columnIndexes);
        }  
  
        public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {  
            return conn.prepareStatement(sql, columnNames);
        }
  
        public Clob createClob() throws SQLException {  
            return conn.createClob();  
        }  
  
        public Blob createBlob() throws SQLException {  
            return conn.createBlob();  
        }  
  
        public NClob createNClob() throws SQLException {  
            return conn.createNClob();  
        }  
  
        public SQLXML createSQLXML() throws SQLException {  
            return conn.createSQLXML();  
        }  
  
        public boolean isValid(int timeout) throws SQLException {
            return conn.isValid(timeout);  
        }  
  
        public void setClientInfo(String name, String value) throws SQLClientInfoException {  
            conn.setClientInfo(name, value);
        }
  
        public void setClientInfo(Properties properties) throws SQLClientInfoException {  
            conn.setClientInfo(properties);
        }
  
        public String getClientInfo(String name) throws SQLException {  
            return conn.getClientInfo(name);  
        }  
  
        public Properties getClientInfo() throws SQLException {  
            return conn.getClientInfo();  
        }  
  
        public Array createArrayOf(String typeName, Object[] elements) throws SQLException {  
            return conn.createArrayOf(typeName, elements);  
        }  
  
        public Struct createStruct(String typeName, Object[] attributes) throws SQLException {  
            return conn.createStruct(typeName, attributes);
        }

		public void setSchema(String schema) throws SQLException {
		}

		public String getSchema() throws SQLException {
			return null;
		}

		public void abort(Executor executor) throws SQLException {
		}

		public void setNetworkTimeout(Executor executor, int milliseconds)	throws SQLException {
		}

		public int getNetworkTimeout() throws SQLException {
			return 0;
		}  
    }  
	
}

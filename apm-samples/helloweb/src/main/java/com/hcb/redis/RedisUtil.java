package com.hcb.redis;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;

/**
 * Redis相关操作
 * @author xianglj
 */
public class RedisUtil {
	
	private static final Charset UTF_8 = Charset.forName("utf-8");

	/**
	 * 向redis新增字符串键值对
	 * @param key
	 * @param value
	 */
	public static boolean setString(String key, String value) {
		if(null == key || value == null ) {
			return false;
		}
		
		return setBytes(key.getBytes(UTF_8), value.getBytes(UTF_8));
	}
	
	/**
	 * 向Redis中储存键值对的byte数组，最长不能超过1GB的字节
	 * @param key 键
	 * @param value 值
	 * @return
	 */
	public static boolean setBytes(byte[] key, byte[] value) {
		if(null == key || null == value) {
			return false;
		}
		
		Jedis jedis = RedisTool.getJedis();
		String statusCode = jedis.set(key, value);
		System.out.println("状态码:(" + statusCode + ")");
		RedisTool.returnResource(jedis);
		return true;
	}
	
	/**
	 * 获取String类型的值
	 * @param key 键的值
	 * @return
	 */
	public static String getString(String key) {
		if(null == key) {
			return null;
		}
		
		byte[] val = getBytes(key.getBytes(UTF_8));
		if(val == null) {
			return null;
		}
		return new String(val, UTF_8);
	}
	
	/**
	 * 获取Redis中的缓存值
	 * @param key
	 * @return
	 */
	public static byte[] getBytes(byte[] key) {
		if(null == key) {
			return null;
		}
		
		Jedis jedis = RedisTool.getJedis();
		byte[] val = jedis.get(key);
		RedisTool.returnResource(jedis);
		return val;
	}
	
	/**
	 * 删除某个键，如果键被删除，再次请求相同键时，返回null
	 * @param key
	 */
	private static boolean del(byte[] key) {
		if(null == key) {
			return true;
		}
		
		Jedis jedis = RedisTool.getJedis();
		jedis.del(key);
		return true;
	}
	
	/**
	 * 操作字符串类型(String),删除键
	 * @param key
	 * @return
	 */
	public static boolean delString(String key) {
		if(null == key) {
			return true;
		}
		
		byte[] k = key.getBytes(UTF_8);
		return del(k);
	}
	
	/**
	 * 批量插入缓存:<br>
	 * key,value,key,value<br>
	 * 例如<br>
	 * name，johnny,age,12<br>
	 * 则会新增name=johnny,age=12的缓存，如果在缓存中已经存在相同的缓存，则会立即更新。
	 * @param keyValues
	 * @return
	 */
	public static boolean fetchSet(String ... keyValues) {
		if(keyValues == null) {
			return false;
		}
		
		Jedis jedis = RedisTool.getJedis();
		jedis.mset(keyValues);
		RedisTool.returnResource(jedis);
		return true;
	}
	
	/**
	 * 插入一个简单类型的Map
	 * @param key
	 * @param map
	 */
	public static void addMap(String key, Map<String, String> map) {
		if(null == key || null == map) {
			return;
		}
		
		Jedis jedis = RedisTool.getJedis();
		jedis.hmset(key, map);
		RedisTool.returnResource(jedis);
	}
	
	public static void addMapVal(String key, String field, String value) {
		if(null == key || field == null || null == value) {
			return;
		}
		Jedis jedis = RedisTool.getJedis();
		jedis.hsetnx(key, field, value);
		RedisTool.returnResource(jedis);
	}
	
	public static void addMapVal(byte[] key, byte[] field, byte[] value) {
		if(null == key || field == null || null == value) {
			return;
		}
		Jedis jedis = RedisTool.getJedis();
		jedis.hsetnx(key, field, value);
		RedisTool.returnResource(jedis);
	}
	
	/**
	 * 向Redis中插入一个Map的值
	 * @param key
	 * @param mapByte
	 */
	public static void addMap(byte[] key, Map<byte[], byte[]> mapByte) {
		if(null == key || null == mapByte) {
			return;
		}
		
		Jedis jedis = RedisTool.getJedis();
		//总是会返回OK,并不会执行失败
		String status = jedis.hmset(key, mapByte);
		System.out.println("执行状态:" + status);
		RedisTool.returnResource(jedis);
	}
	
	/**
	 * 获取Map中的值，只能够
	 * @param key
	 * @return
	 */
	public static List<String> getMapVal(String key, String ... fields) {
		if(null == key) {
			return null;
		}
		
		Jedis jedis = RedisTool.getJedis();
		
		List<String> rtnList = null;
		if(null == fields || fields.length == 0) {
			rtnList = jedis.hvals(key);
		} else {
			rtnList = jedis.hmget(key, fields);
		}
		
		RedisTool.returnResource(jedis);
		return rtnList;
	}
	
	/**
	 * 获取Map中的值
	 * @param key
	 * @param fields
	 * @return
	 */
	public static List<byte[]> getMapVal(byte[] key, byte[] ... fields) {
		if(null == key) {
			return null;
		}
		Jedis jedis = RedisTool.getJedis();
		
		if(!jedis.exists(key)) {
			return null;
		}
		List<byte[]> rtnList = null;
		if(null == fields || fields.length == 0) {
			rtnList = jedis.hvals(key);
		} else {
			rtnList = jedis.hmget(key, fields);
		}
		
		return rtnList;
	}
	
	/**
	 * 向Redis中添加set集合
	 * @param key
	 * @param values
	 */
	public static void addSet(String key, String ... values) {
		if(null == key || values == null) {
			return;
		}
		Jedis jedis = RedisTool.getJedis();
		jedis.sadd(key, values);
	}
	
	public static void delSetVal(String key, String ... fields) {
		if(null == key) {
			return;
		}
		
		if(fields == null || fields.length == 0) {
			del(key.getBytes(UTF_8));
			return;
		}
		
		Jedis jedis = RedisTool.getJedis();
		jedis.srem(key, fields);
		RedisTool.returnResource(jedis);
	}
	
	public static void addSetBytes(byte[] key, byte[]...values) {
		if(null == key || values == null) {
			return;
		}
		
		Jedis jedis = RedisTool.getJedis();
		jedis.sadd(key, values);
		RedisTool.returnResource(jedis);
	}
	
	public static void delSetVal(byte[] key, byte[]...values) {
		if(null == key) {
			return;
		}
		if(values == null || values.length == 0) {
			del(key);
			return;
		}
		Jedis jedis = RedisTool.getJedis();
		jedis.srem(key, values);
		RedisTool.returnResource(jedis);
	}
	
	/**
	 * 获取所有的值
	 * @param key
	 */
	public static Set<byte[]> getSetVals(byte[] key) {
		if(null == key) {
			return null;
		}
		Jedis jedis = RedisTool.getJedis();
		Set<byte[]> rtnList = jedis.smembers(key);
		return rtnList;
	}
	
	public static Set<String> getSetVals(String key) {
		if(null == key) {
			return null;
		}
		Jedis jedis = RedisTool.getJedis();
		Set<String> rtnSet = jedis.smembers(key);
		RedisTool.returnResource(jedis);
		return rtnSet;
	}
	
	/**
	 * 判断是否Set集合中包含元素
	 * @param key
	 * @param field
	 * @return
	 */
	public static boolean isSetContain(String key, String field) {
		if(null == key || field == null) {
			return false;
		}
		Jedis jedis = RedisTool.getJedis();
		boolean isContain = jedis.sismember(key, field);
		RedisTool.returnResource(jedis);
		return isContain;
	}
	
	public static boolean isSetContain(byte[] key, byte[] field) {
		if(null == key || field == null) {
			return false;
		}
		Jedis jedis = RedisTool.getJedis();
		boolean isSuccess = jedis.sismember(key, field);
		RedisTool.returnResource(jedis);
		return isSuccess;
	}
	
	/**
	 * 返回Set集合中的元素个数
	 * @param key
	 * @return
	 */
	public static Long getSetLength(String key) {
		if(null == key) {
			return 0L;
		}
		Jedis jedis = RedisTool.getJedis();
		Long length = jedis.scard(key);
		return length;
	}
	
	public static Long getSetLength(byte[] key) {
		if(null == key) {
			return 0L;
		}
		Jedis jedis = RedisTool.getJedis();
		Long length = jedis.scard(key);
		RedisTool.returnResource(jedis);
		return length;
	}
	
	/**
	 * 向list集合中添加元素
	 * @param key
	 * @param values
	 */
	public static void addList(String key, String ...values) {
		if(null == key || values == null) {
			return;
		}
		
		Jedis jedis = RedisTool.getJedis();
		jedis.rpush(key, values);
		RedisTool.returnResource(jedis);
	}
	
	/**
	 * 向list集合中添加元素
	 * @param key
	 * @param values
	 */
	public static void addList(byte[] key, byte[] ...values) {
		if(null == key || values == null) {
			return;
		}
		
		Jedis jedis = RedisTool.getJedis();
		jedis.rpush(key, values);
		RedisTool.returnResource(jedis);
	}
	
	/**
	 * 获取start到end范围的值，超出list的范围，不会抛出异常
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public static List<String> getListVals(String key, int start, int end) {
		if(null == key) {
			return null;
		}
		
		Jedis jedis = RedisTool.getJedis();
		List<String> rtnList = jedis.lrange(key, start, end);
		RedisTool.returnResource(jedis);
		return rtnList;
	}
	
	/**
	 * 获取start到end范围的值，超出list的范围，不会抛出异常
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public static List<byte[]> getListVals(byte[] key, int start, int end) {
		if(null == key) {
			return null;
		}
		
		Jedis jedis = RedisTool.getJedis();
		List<byte[]> rtnList = jedis.lrange(key, start, end);
		RedisTool.returnResource(jedis);
		return rtnList;
	}
	
	public static List<String> getListAll(String key) {
		if(null == key) {
			return null;
		}
		return getListVals(key, 0, -1);
	}
	
	public static List<byte[]> getListAll(byte[] key) {
		if(null == key) {
			return null;
		}
		return getListVals(key, 0, -1);
	}
	
	public static String popList(String key) {
		if(null == key) {
			return null;
		}
		Jedis jedis = RedisTool.getJedis();
		return jedis.lpop(key);
	}
	public static byte[] popList(byte[] key) {
		if(null == key) {
			return null;
		}
		Jedis jedis = RedisTool.getJedis();
		return jedis.lpop(key);
	}
}


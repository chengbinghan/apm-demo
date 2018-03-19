package com.hcb.redis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;



public class RedisUtilTest {
	
	private static final Charset UTF_8 = Charset.forName("utf-8");

	@Test
	public void testString() {
		System.err.println("测试String类型开始>>\r\n\t");
		
		String key = "Program Name";
		String value = "Redis For Windows";
		String value1 = "Input Redis For bytes";
		RedisUtil.setString(key, value);
		RedisUtil.setBytes(key.getBytes(UTF_8), value1.getBytes(UTF_8));
		
		System.out.println("从Redis中获取name:>>>\r\n\t");
		String val = RedisUtil.getString(key);
		System.out.println("输出:\r\n\t" + val);
		
		/*System.out.println("从Redis中获取name bytes:>>>>\r\n\t");
		byte[] bytes = RedisUtil.getBytes(key.getBytes(UTF_8));
		System.out.println("输出bytes:\r\n\t" + Arrays.toString(bytes));
		val = new String(bytes, UTF_8);
		System.out.println("转换后String:\r\n\t" + val);
		
		System.out.println("删除name的键:\r\n\t");
		RedisUtil.delString(key);
		val = RedisUtil.getString(key);
		System.out.println("再次获取:" + (val==null?"该键已被删除..":val));*/
	}
	
	@Test
	public void testMap() {
		
		System.err.println("测试Redis For Map 开始:>>>>");
		
		//简单的string map
		Map<String, String> strMap = new HashMap<String, String>();
		//复杂点的map
		Map<byte[], byte[]> bytesMap = new HashMap<byte[], byte[]>();
		
		//测试储存新的地址
		strMap.put("OS", "Windows 10");
		strMap.put("Language", "ch");
		strMap.put("Tool", "Redis For Windows");
		String skey = "String For Redis";
		RedisUtil.addMap(skey, strMap);
		
		//从获取所有的值
		List<String> sList = RedisUtil.getMapVal(skey);
		System.out.println("所有结果值:" + sList);
		
		//按照给出的field顺序给出值
		sList = RedisUtil.getMapVal(skey, "Tool", "OS", "Language", "dd");
		//发现取出的值和输入的field的顺序一致
		System.out.println("输出值[Tool, OS, Language, dd]:\r\n\t"+ sList);
		
		//尝试在Redis中存储对象
		Person person = new Person("Johnny", 23, "男");
		//序列化对象
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = null;
		ObjectInputStream bis = null;
		try {
			oos = new ObjectOutputStream(baos);
			//创建对象
			oos.writeObject(person);
			//获取序列化之后的字节码
			byte[] bytes = baos.toByteArray();
			bytesMap.put(person.getName().getBytes(UTF_8), bytes);
			RedisUtil.addMap(person.getName().getBytes(UTF_8), bytesMap);
			
			//从Redis中读取对象
			List<byte[]> list= RedisUtil.getMapVal(person.getName().getBytes(UTF_8), person.getName().getBytes(UTF_8));
			if(list.size() == 1) {
				bytes = list.get(0);
				ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
				bis = new ObjectInputStream(bais);
				Person p = (Person) bis.readObject();
				System.out.println("获取到对象:" + p);
				
				bais.close();
				bis.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				if(baos != null) {
					baos.close();
				}
				if(null != oos) {
					oos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//向已经存在的key中新增键值对
		RedisUtil.addMapVal(person.getName().getBytes(UTF_8), "AddTest".getBytes(UTF_8), "Test Redis Adding A Val For Exist Key".getBytes(UTF_8));
		//获取刚插入的值
		System.out.println("获取刚插入的值:\r\n\t" + 
		new String(RedisUtil.getMapVal(person.getName().getBytes(UTF_8), "AddTest".getBytes(UTF_8)).get(0)));
		
		//尝试向不存在的Key中插值
		RedisUtil.addMapVal("AddNewKey", "AddNewMapKey", "AddNewMapVal");
		//能够获取到值，因此也说明在进行不存在key的插值时，会自动创建对象的键值对以保存。
		System.out.println("尝试获取刚插入的值:\r\n\t" + RedisUtil.getMapVal("AddNewKey", "AddNewMapKey"));
	}
	
	@Test
	public void testSet() {
		System.err.println("测试Redis For Set 开始:>>>>>>>");
		//向Redis添加元素
		RedisUtil.addSet("AddNewSet", "set1", "set2", "set3");
		//获取set中的值
		System.out.println("Set集合的长度:\r\n\t" + RedisUtil.getSetLength("AddNewSet"));
		System.out.println("Set集合元素:\r\n\t" + RedisUtil.getSetVals("AddNewSet"));
		//尝试移除元素
		RedisUtil.delSetVal("AddNewSet", "set2");
		System.out.println("Set集合的长度:\r\n\t" + RedisUtil.getSetLength("AddNewSet"));
		System.out.println("Set集合元素:\r\n\t" + RedisUtil.getSetVals("AddNewSet"));
		
		//判断是否包含元素
		System.out.println("是否包含set2的值:" + RedisUtil.isSetContain("AddNewSet", "set2"));
		System.out.println("是否包含set2的值:" + RedisUtil.isSetContain("AddNewSet", "set3"));
	}
	
	@Test
	public void testList() {
		System.err.println("测试Redis For List 开始:>>>>>>");
		//向List中添加元素
		RedisUtil.addList("ValList", "List1", "List2", "List3");
		//获取List中的值
		System.out.println("Redis For List中的值为:" + RedisUtil.getListAll("ValList"));
		//弹出list的第一个元素
		System.out.println("弹出第一个元素:" + RedisUtil.popList("ValList"));
		System.out.println("Redis For List中的值为:" + RedisUtil.getListAll("ValList"));
	}
}

class Person implements Serializable {
	private static final long serialVersionUID = 8737363017319228700L;
	private String name;
	private int age;
	private String sex;
	public Person(String name, int age, String sex) {
		this.name = name;
		this.age = age;
		this.sex = sex;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", sex=" + sex + "]";
	}
	
}


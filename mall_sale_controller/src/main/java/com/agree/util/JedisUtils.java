package com.agree.util;

import com.agree.bean.OBJECT_T_MALL_SKU;
import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class JedisUtils {

	public static JedisPoolConfig c = new JedisPoolConfig(); // 连接池配置
	public static JedisPool jedisPool = null; // 连接池

	static {
		// c.setBlockWhenExhausted(true); // 连接耗尽则阻塞
		c.setLifo(true); // 后进先出
		c.setMaxIdle(10); // 最大空闲连接数为10
		c.setMinIdle(0); // 最小空闲连接数为0
		c.setMaxTotal(100); // 最大连接数为100
		c.setMaxWaitMillis(-1); // 设置最大等待毫秒数：无限制
		c.setMinEvictableIdleTimeMillis(180); // 逐出连接的最小空闲时间：30分钟
		c.setTestOnBorrow(true); // 获取连接时是否检查连接的有效性：是
		c.setTestWhileIdle(true); // 空闲时是否检查连接的有效性：是

		jedisPool = new JedisPool(c, MyPropertyUtil.getProperty("redis.properties", "url"), 6379); // 初始化连接池
	}

	/**
	 * 获取Jedis连接
	 * 
	 * @return Jedis连接
	 */
	public static Jedis getJedis() {
		return jedisPool.getResource();
	}

	public static <T> List<T> getList(String key, Class<T> t) {
		Jedis jedis = null;
		List<T> list = new ArrayList<T>();
		try {
			jedis = JedisUtils.getJedis();
			Set<String> set = jedis.zrange(key, 0, -1);
			for (String str : set) {
				str = URLDecoder.decode(str, "utf-8");
				list.add(JSONObject.parseObject(str,t));
			}
			return list;
		} catch (Exception e) {
			//记日志
			e.printStackTrace();
		}
		return null;
	}

	public static void setList(String key, List<OBJECT_T_MALL_SKU> list) {
		Jedis jedis = null;
		try {
			jedis = JedisUtils.getJedis();
			jedis.del(key);
			for(int i=0; i<list.size();i++){
				String jsonstr = URLEncoder.encode(JSONObject.toJSONString(list.get(i)),"utf-8");
				jedis.zadd(key,i,jsonstr);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public static List<OBJECT_T_MALL_SKU> getListByAttr(String[] key) {
		Jedis jedis = null;
		List<OBJECT_T_MALL_SKU> list = new ArrayList<OBJECT_T_MALL_SKU>();
		try {
			jedis = JedisUtils.getJedis();
			//生成动态的key，避免操作相同key导致并发问题
			String k0 = "combine";
			for(int i = 0;i < key.length; i++){
				k0 = k0 + "_" + key[i];
			}
			if(!JedisUtils.existsKey(k0)) {
				jedis.zinterstore(k0, key);
			}
			Set<String> set = jedis.zrange(k0,0,-1);
			for (String str : set) {
				str = URLDecoder.decode(str, "utf-8");
				list.add(JSONObject.parseObject(str,OBJECT_T_MALL_SKU.class));
			}
			return list;
		}catch (Exception e){
			e.printStackTrace();
		}

		return null;
	}

	public static Boolean existsKey(String key){
		Jedis jedis = null;
		try {
			jedis = JedisUtils.getJedis();
			return jedis.exists(key);
		}catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}
}
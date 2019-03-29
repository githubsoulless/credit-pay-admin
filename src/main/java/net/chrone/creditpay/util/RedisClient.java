package net.chrone.creditpay.util;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


public class RedisClient {

	private static Jedis jedis;//非切片客户端连接,用于单机redis
	private static JedisPool jedisPool;//非切片客户端连接池,用于单机redis
	
	private static final String REDIS_IP = ConfigReader.getConfig("redisIp");
	private static final Integer REDIS_PORT = ConfigReader.getInt("redisPort");
	private static final String REDIS_PASSWORD = ConfigReader.getConfig("redisPasswd");
	
	public static final String CACHE_PREFIX_PMSBANKINF = "redis_prefix_pmsbankinf_";
	public static final String CACHE_PREFIX_ROOTBANK = "redis_prefix_rootbank_";
	public static final String CACHE_PREFIX_SYSPARAM = "redis_prefix_sysparam_";
	public static final String CACHE_PREFIX_CREDITROOTBANK = "redis_prefix_creditrootbank_";
	public static final String CACHE_PREFIX_SEQ_NO_ = "redis_prefix_seq_no_";
	
	
	
	static{
		initalPool();//初始化连接池
	}
	
	

	private static void initalPool() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(2000);
		config.setMaxIdle(10);
		config.setMaxWaitMillis(10000);
		config.setTestOnBorrow(false);
		jedisPool = new JedisPool(config,REDIS_IP,REDIS_PORT,2000,REDIS_PASSWORD);
	}
	 

	public synchronized static Jedis getClient(){
		jedis.select(0);
		return jedis;
	}
	
	public synchronized static String getByKey(String key){
		Jedis jedis = null;
		try{
			jedis = jedisPool.getResource();
			return jedis.get(key);
		}finally{
			jedis.close();
		}
	}
	
	public synchronized static String set(String key,String value){
		 Jedis jedis = null;
			try{
				jedis = jedisPool.getResource();
				return jedis.set(key, value);
			}finally{
				jedis.close();
			}
	}
	public  static Long inc(String key){
		Jedis jedis = null;
		try{
			jedis = jedisPool.getResource();
			return jedis.incr(key);
		}finally{
			jedis.close();
		}
	}
	public synchronized static Boolean exisit(String key){
		Jedis jedis = null;
		try{
			jedis = jedisPool.getResource();
			return jedis.exists(key);
		}finally{
			jedis.close();
		}
	}
	
	
}

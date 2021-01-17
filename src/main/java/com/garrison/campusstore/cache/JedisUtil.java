package com.garrison.campusstore.cache;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.util.SafeEncoder;

import java.util.Set;

public class JedisUtil {
    //  操作key的方法
    public Keys KEYS;
    // 对存储结构为String类型的操作
    public Strings STRINGS;
    // redis 连接池对象
    private JedisPool jedisPool;
    // 获取redis连接池
    public JedisPool getJedisPool(){
        return jedisPool;
    }
    // 设置redis连接池
    public void setJedisPool(JedisPoolWriper jedisPoolWriper){
        this.jedisPool = jedisPoolWriper.getJedisPool();
    }
    //从jedis连接池中获取jedis对象
    public Jedis getJedis(){
        return jedisPool.getResource();
    }

    // 内部类 Keys
    public class Keys{
        //清空所有key
        public String flushAll(){
            Jedis jedis = getJedis();
            String stata = jedis.flushAll();
            jedis.close();
            return stata; // 返回删除的Key
        }
        //删除keys对应的记录，可以是多个key
        public long del(String... keys){
            Jedis jedis = getJedis();
            long count = jedis.del(keys);
            jedis.close();
            return count; //返回删除的记录数
        }
        //判断key是否存在
        public boolean exist(String key){
            Jedis sjedis = getJedis();
            boolean exis = sjedis.exists(key);
            sjedis.close();
            return exis;
        }
        //查找所有匹配给定的模式的键
        // String key 的表达式，*表示多个，？表示一个
        public Set<String> keys(String pattern){
            Jedis jedis = getJedis();
            Set<String> set = jedis.keys(pattern);
            jedis.close();
            return set;
        }
    }

    //内部类 Strings
    public class Strings{
        // 根据key获取记录
        public String get(String key){
            Jedis sjedis = getJedis();
            String value = sjedis.get(key);
            sjedis.close();
            return value;
        }
        // 添加记录，如果记录已存在将覆盖原有的value,将key和value通过SafeEncoder转换成byte【】数组，传入到下面的set方法中进行操作
        // 返回的是一个状态码
        public String set(String key, String value){
            return set(SafeEncoder.encode(key), SafeEncoder.encode(value));
        }
        public String set(byte[] key, byte[] value){
            Jedis jedis = getJedis();
            String status = jedis.set(key,value);
            jedis.close();
            return status;
        }
    }


}

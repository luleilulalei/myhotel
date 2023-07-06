package org.ysu.dbutils;

import org.ysu.utils.YamlUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.exceptions.JedisException;
import redis.clients.jedis.params.SetParams;

import java.util.Map;

public class JedisUtils {
    private static JedisPool jedisPool;

    static {
        Map<?, ?> appCfg =  YamlUtil.loadYaml("application.yml");
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(10);
        @SuppressWarnings("unchecked")
        Map<String,Object> redis = (Map<String,Object>)YamlUtil.getProperty(appCfg, "redis");
        if(redis == null){
            throw new RuntimeException();
        }
        jedisPool = new JedisPool((String) redis.get("ip"), (Integer) redis.get("port"));
    }

    public static void main(String[] args) {
        set("k2", "v2");
        System.out.println(get("k2"));
    }


    /**
     * 获取jedis
     *
     * @return
     */
    public static Jedis getJedis() {
        return jedisPool.getResource();
    }

    /**
     * jedis放回连接池
     *
     * @param jedis
     */
    public static void close(Jedis jedis) {
        //从源码可以分析得到，如果是使用连接池的形式，这个并非真正的close,而是把连接放回连接池中
        if (jedis != null) {
            jedis.close();
        }
    }

    /**
     * get
     *
     * @param key
     * @return
     */
    public static String get(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            throw new JedisException(e.getMessage(),e);
        } finally {
            close(jedis);
        }
    }

    /**
     * set
     *
     * @param key
     * @param value
     * @return
     */
    public static void set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            throw new JedisException(e.getMessage(),e);
        } finally {
            close(jedis);
        }
    }

    /**
     * set with expire milliseconds
     *
     * @param key
     * @param value
     * @param seconds
     * @return
     */
    public static void set(String key, String value, int seconds) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            //* @param nxxx NX|XX, NX -- Only set the key if it does not already exist. XX -- Only set the key
            //     *                     *          if it already exist.
            //     *                     * @param expx EX|PX, expire time units: EX = seconds; PX = milliseconds

            jedis.set(key, value, SetParams.setParams().ex(seconds));
        } catch (Exception e) {
            e.printStackTrace();
            throw new JedisException(e.getMessage(),e);
        } finally {
            close(jedis);
        }
    }
}

/*
 * Copyright (C) 1997-2020 康成投资（中国）有限公司
 *
 * http://www.rt-mart.com
 *
 * 版权归本公司所有，不得私自使用、拷贝、修改、删除，否则视为侵权
 */
package com.example.faqan.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Map;

/**
 * @author xiaoyi.yang
 * @date 2021/11/29
 */
@Service
public class RedisUtils {
    @Autowired
    JedisPool jedisPool;


    /**
     * 设置失效时间
     * @param key
     * @param value
     * @return
     */
    public Long setnx(String key ,String value){
        Jedis jedis =null;
        Long result = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.setnx(key,value);
        }catch (Exception e){
            jedisPool.returnResource(jedis);
            return  result;
        }
        jedisPool.returnResource(jedis);
        return  result;

    }
    /**
     * 设置key的有效期，单位是秒
     * @param key
     * @param exTime
     * @return
     */
    public Long expire(String key,int exTime){
        Jedis jedis = null;
        Long result = null;
        try {
            jedis =  jedisPool.getResource();
            result = jedis.expire(key,exTime);
        } catch (Exception e) {
            jedisPool.returnBrokenResource(jedis);
            return result;
        }
        jedisPool.returnResource(jedis);
        return result;
    }

    /**
     * 获取当个对象
     * */
//    public <T> T get(KeyPrefix prefix, String key,  Class<T> clazz) {
//        Jedis jedis = null;
//        try {
//            jedis =  jedisPool.getResource();
//            //生成真正的key
//            String realKey  = prefix.getPrefix() + key;
//            String  str = jedis.get(realKey);
//            T t =  stringToBean(str, clazz);
//            return t;
//        }finally {
//            returnToPool(jedis);
//        }
//    }

    public  String get(String key){
        Jedis jedis = null;
        String result = null;
        try {
            jedis =  jedisPool.getResource();
            result = jedis.get(key);
        } catch (Exception e) {
//            log.error("expire key:{} error",key,e);
            jedisPool.returnBrokenResource(jedis);
            return result;
        }
        jedisPool.returnResource(jedis);
        return result;
    }
    public List<String> hmget(String key, String filed){
        Jedis jedis = null;
        List<String> result = null;
        try {
            jedis =  jedisPool.getResource();
            result = jedis.hmget(key, filed);
        } catch (Exception e) {
//            log.error("expire key:{} error",key,e);
            jedisPool.returnBrokenResource(jedis);
            return result;
        }
        jedisPool.returnResource(jedis);
        return result;
    }


//    public  String getset(String key,String value){
//        Jedis jedis = null;
//        String result = null;
//        try {
//            jedis =  jedisPool.getResource();
//            result = jedis.getSet(key,value);
//        } catch (Exception e) {
//            log.error("expire key:{} error",key,e);
//            jedisPool.returnBrokenResource(jedis);
//            return result;
//        }
//        jedisPool.returnResource(jedis);
//        return result;
//    }
    /**
     *
     * */
    public <T> boolean set(int id, String key,  T value, int time) {
        Jedis jedis = null;
        try {
            jedis =  jedisPool.getResource();
            String str = String.valueOf(value);
            if(str == null || str.length() <= 0) {
                return false;
            }
            //生成真正的key
            String realKey  =  id + key;
            if(time <= 0) {
                jedis.set(realKey, str);
            }else {
                jedis.setex(realKey, time, str);
            }
            return true;
        }finally {
            returnToPool(jedis);
        }
    }

    public <T> boolean set(int id, String key, String value,String nx, String ex, int time) {
        Jedis jedis = null;
        try {
            jedis =  jedisPool.getResource();
            String str = String.valueOf(value);
            if(str == null || str.length() <= 0) {
                return false;
            }
            //生成真正的key
            String realKey  =  id + key;
            jedis.set(realKey, value, nx, ex, time);
            return true;
        }finally {
            returnToPool(jedis);
        }
    }

    public <T> boolean hmset(int id, String key, Map value, int time) {
        Jedis jedis = null;
        try {
            jedis =  jedisPool.getResource();
            if(value == null || value.size() <= 0) {
                return false;
            }
            //生成真正的key
            String realKey  =  id + key;
            jedis.hmset(realKey, value);
            return true;
        }finally {
            returnToPool(jedis);
        }
    }

    public long lpushString(String key, String... value) {
        Jedis jedis = null;
        long ret = 0;
        try {
            jedis = jedisPool.getResource();
            if (jedis == null) {
                return ret;
            }
            ret = jedis.lpush(key, value);
        } catch (Exception e) {
        } finally {
        }
        return ret;
    }
    public String rPopString(String key) {
        Jedis jedis = null;
        String ret = null;
        try {
            jedis = jedisPool.getResource();
            if (jedis == null) {
                return ret;
            }
            ret = jedis.rpop(key);
        } catch (Exception e) {
        } finally {
        }
        return ret;
    }
    /**
     * 判断key是否存在
     * */
//    public <T> boolean exists(KeyPrefix prefix, String key) {
//        Jedis jedis = null;
//        try {
//            jedis =  jedisPool.getResource();
//            //生成真正的key
//            String realKey  = prefix.getPrefix() + key;
//            return  jedis.exists(realKey);
//        }finally {
//            returnToPool(jedis);
//        }
//    }

    /**
     * 删除
     * */
//    public boolean delete(KeyPrefix prefix, String key) {
//        Jedis jedis = null;
//        try {
//            jedis =  jedisPool.getResource();
//            //生成真正的key
//            String realKey  = prefix.getPrefix() + key;
//            long ret =  jedis.del(realKey);
//            return ret > 0;
//        }finally {
//            returnToPool(jedis);
//        }
//    }

    /**
     * 增加值
     * */
    public <T> Long incr(int id, String key) {
        Jedis jedis = null;
        try {
            jedis =  jedisPool.getResource();
            //生成真正的key
            String realKey  = id + key;
            return  jedis.incr(realKey);
        }finally {
            returnToPool(jedis);
        }
    }

    /**
     * 减少值
     * */
    public <T> Long decr(int id, String key) {
        Jedis jedis = null;
        try {
            jedis =  jedisPool.getResource();
            //生成真正的key
            String realKey  = id + key;
            return  jedis.decr(realKey);
        }finally {
            returnToPool(jedis);
        }
    }

    public  Long del(String key){
        Jedis jedis = null;
        Long result = null;
        try {
            jedis =  jedisPool.getResource();
            result = jedis.del(key);
        } catch (Exception e) {
            jedisPool.returnBrokenResource(jedis);
            return result;
        }
        jedisPool.returnResource(jedis);
        return result;
    }


//    public boolean delete(KeyPrefix prefix) {
//        if(prefix == null) {
//            return false;
//        }
//        List<String> keys = scanKeys(prefix.getPrefix());
//        if(keys==null || keys.size() <= 0) {
//            return true;
//        }
//        Jedis jedis = null;
//        try {
//            jedis = jedisPool.getResource();
//            jedis.del(keys.toArray(new String[0]));
//            return true;
//        } catch (final Exception e) {
//            e.printStackTrace();
//            return false;
//        } finally {
//            if(jedis != null) {
//                jedis.close();
//            }
//        }
//    }

//    public List<String> scanKeys(String key) {
//        Jedis jedis = null;
//        try {
//            jedis = jedisPool.getResource();
//            List<String> keys = new ArrayList<String>();
//            String cursor = "0";
//            ScanParams sp = new ScanParams();
//            sp.match("*"+key+"*");
//            sp.count(100);
//            do{
//                ScanResult<String> ret = jedis.scan(cursor, sp);
//                List<String> result = ret.getResult();
//                if(result!=null && result.size() > 0){
//                    keys.addAll(result);
//                }
//                //再处理cursor
//                cursor = ret.getStringCursor();
//            }while(!cursor.equals("0"));
//            return keys;
//        } finally {
//            if (jedis != null) {
//                jedis.close();
//            }
//        }
//    }

//    public static <T> String beanToString(T value) {
//        if(value == null) {
//            return null;
//        }
//        Class<?> clazz = value.getClass();
//        if(clazz == int.class || clazz == Integer.class) {
//            return ""+value;
//        }else if(clazz == String.class) {
//            return (String)value;
//        }else if(clazz == long.class || clazz == Long.class) {
//            return ""+value;
//        }else {
//            return JSON.toJSONString(value);
//        }
//    }
//
//    @SuppressWarnings("unchecked")
//    public static <T> T stringToBean(String str, Class<T> clazz) {
//        if(str == null || str.length() <= 0 || clazz == null) {
//            return null;
//        }
//        if(clazz == int.class || clazz == Integer.class) {
//            return (T)Integer.valueOf(str);
//        }else if(clazz == String.class) {
//            return (T)str;
//        }else if(clazz == long.class || clazz == Long.class) {
//            return  (T)Long.valueOf(str);
//        }else {
//            return JSON.toJavaObject(JSON.parseObject(str), clazz);
//        }
//    }

    private void returnToPool(Jedis jedis) {
        if(jedis != null) {
            jedis.close();
        }
    }

}

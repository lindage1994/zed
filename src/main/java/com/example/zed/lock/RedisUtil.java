package com.example.zed.lock;

import com.alibaba.fastjson.JSONObject;
import com.example.zed.util.SpringContextUtils;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.core.types.Expiration;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author Louis
 * Redis工具类
 */
@SuppressWarnings("unchecked")
public class RedisUtil {
    /**
     * 解锁LUA脚本
     */
    private static final String UNLOCK_LUA;
    /**
     * 延长锁的时间LUA脚本
     */
    private static final String EXPAND_LUA;

    /**
     * 不设置过期时长
     */
    public static final long NOT_EXPIRE = -1;

    /**
     * 同步锁默认超时时间(单位:秒)
     */
    public static final int DEFAULT_LOCK_TIMEOUT = 2;

    /**
     * 尝试获取同步锁的间隔时间(单位:毫秒)
     */
    public static final long TRY_LOCK_INTERVAL = 100L;

    /**
     * 默认过期时长,单位:秒 (24小时)
     */
    public static final long DEFAULT_EXPIRE = 60 * 60 * 24;

    /**
     * Redis客户端操作模板
     */
    private static RedisTemplate<String, Object> redisTemplate;

    /**
     * 不可重复列表类型操作类
     */
    private static SetOperations<String, Object> setOperations;

    /**
     * 带权重的不可重复列表类型操作类
     */
    private static ZSetOperations<String, Object> zSetOperations;

    /**
     * 可重复列表类型操作类
     */
    private static ListOperations<String, Object> listOperations;

    /**
     * 通用对象类型操作类
     */
    private static ValueOperations<String, Object> valueOperations;

    /**
     * Hash表结构类型操作类
     */
    private static HashOperations<String, Object, Object> hashOperations;

    /**
     * 缓存解锁脚本
     */
    static {
        UNLOCK_LUA = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        EXPAND_LUA = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('expire', KEYS[1],ARGV[2]) else return '0' end";

        redisTemplate = (RedisTemplate<String, Object>) SpringContextUtils.getBeanById("redisTemplate");
        setOperations = redisTemplate.opsForSet();
        listOperations = redisTemplate.opsForList();
        hashOperations = redisTemplate.opsForHash();
        zSetOperations = redisTemplate.opsForZSet();
        valueOperations = redisTemplate.opsForValue();
    }

    /**
     * 查找匹配的键
     *
     * @param pattern 字串表达式
     * @return 键集
     */
    public static Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    /**
     * 判断指定键是否存在
     *
     * @param key 键
     * @return 是否存在指定的键
     */
    public static boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 判断指定域是否存在
     *
     * @param key   键
     * @param field 域
     * @return 是否存在指定的域
     */
    public static boolean hasField(String key, String field) {
        return hashOperations.hasKey(key, field);
    }

    /**
     * 为指定键的映射设置过期时长(单位:秒)
     *
     * @param key    键
     * @param expire 过期时间(单位:秒)
     * @return 是否存在指定的键
     */
    public static Long expire(String key, int expire) {
        redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        if (redisTemplate.hasKey(key)) return 1L;
        return 0L;
    }

    /**
     * 按默认过期时长设置键值对
     *
     * @param key   键
     * @param value 值
     */
    public static void set(String key, Object value) {
        set(key, value, DEFAULT_EXPIRE);
    }

    /**
     * 按给定的过期时长设置键值对
     *
     * @param key    键
     * @param value  值
     * @param expire 过期时间(单位:秒)
     */
    public static void set(String key, Object value, long expire) {
        valueOperations.set(key, value);
        if (expire != NOT_EXPIRE) redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    /**
     * 根据指定键获取值对象(不设置过期时间)
     *
     * @param key        键
     * @param returnType 返回类型
     * @return 泛化值类型
     */
    public static <T> T get(String key, Class<T> returnType) {
        return get(key, returnType, NOT_EXPIRE);
    }

    /**
     * 根据指定键获取值对象(设置指定过期时间)
     *
     * @param key        键
     * @param returnType 返回类型
     * @param expire     过期时间(单位:秒)
     * @return 泛化值类型
     */
    public static <T> T get(String key, Class<T> returnType, long expire) {
        Object value = valueOperations.get(key);
        if (expire != NOT_EXPIRE) redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        return null == value ? null : JSONObject.parseObject(value.toString(), returnType);
    }

    /**
     * 根据指定键获取字串值(不设置过期时间)
     *
     * @param key 键
     * @return 值对象
     */
    public static String get(String key) {
        return get(key, NOT_EXPIRE);
    }

    /**
     * 根据指定键获取字串值(设置指定过期时间)
     *
     * @param key    键
     * @param expire 过期时间(单位:秒)
     * @return 值对象
     */
    public static String get(String key, long expire) {
        Object value = valueOperations.get(key);
        if (expire != NOT_EXPIRE) redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        return null == value ? null : value.toString();
    }

    /**
     * 删除指定键的映射
     *
     * @param key 键
     * @return 是否删除成功
     */
    public static boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 删除所有模糊匹配到的键
     *
     * @param pattern 键字串表达式
     * @return 被删除键的数量
     */
    public static Long delAll(String pattern) {
        Set<String> keys = redisTemplate.keys(pattern);
        return redisTemplate.delete(keys);
    }

    /**
     * 从管道左端移除参数键映射集合中的元素
     *
     * @param key 键
     * @return 被移除的元素
     */
    public static Object lpop(String key) {
        return listOperations.leftPop(key);
    }

    /**
     * 为键的映射值增长一个指定的整数值
     *
     * @param key       键
     * @param increment 增长量(可以为负值)
     * @return Value增长后的值
     */
    public static Long incrBy(String key, long increment) {
        return valueOperations.increment(key, increment);
    }

    /**
     * 向键映射的哈希表中添加一个键值对
     *
     * @param key   键
     * @param field 域
     */
    public static void hset(String key, String field, String value) {
        hashOperations.put(key, field, value);
    }

    /**
     * 按默认过期时间设置一个键到哈希表的映射
     *
     * @param key 键
     * @param map 字典
     * @Description put一个map
     */
    public static void hset(String key, Map<String, Object> map) {
        hset(key, map, DEFAULT_EXPIRE);
    }

    /**
     * 按指定过期时间设置一个键到哈希表的映射
     *
     * @param key    键
     * @param map    字典
     * @param expire 过期时间(单位:秒)
     */
    public static void hset(String key, Map<String, Object> map, long expire) {
        hashOperations.putAll(key, map);
        if (expire != NOT_EXPIRE) redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    /**
     * 按默认过期时间设置一个键到哈希表的映射
     *
     * @param key 键
     * @param map 字典
     */
    public static void hmset(String key, Map<String, String> map) {
        hmset(key, map, DEFAULT_EXPIRE);
    }

    /**
     * 按指定过期时间设置一个键到哈希表的映射
     *
     * @param key    键
     * @param map    字典
     * @param expire 过期时间(单位:秒)
     */
    public static void hmset(String key, Map<String, String> map, long expire) {
        hashOperations.putAll(key, map);
        if (expire != NOT_EXPIRE) redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    /**
     * 获取键映射的哈希表
     *
     * @param key 键
     * @return 字典对象
     */
    public static Map<String, Object> hgetAll(String key) {
        Map<String, Object> returnTab = new HashMap<String, Object>();
        hashOperations.entries(key).forEach((k, v) -> returnTab.put(null == k ? null : k.toString(), v));
        return returnTab;
    }

    /**
     * 获取键映射的哈希表
     *
     * @param key   键
     * @return 字典对象
     */
    public static Map<String, Object> hgetAll(String key, long expire) {
        if (expire != NOT_EXPIRE) redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        Map<String, Object> returnTab = new HashMap<String, Object>();
        hashOperations.entries(key).forEach((k, v) -> returnTab.put(null == k ? null : k.toString(), v));
        return returnTab;
    }

    /**
     * 根据键/域组合参数获取哈希表中的字串值
     *
     * @param key   键
     * @param field 域
     * @return 字串值
     */
    public static String hget(String key, String field) {
        Object value = hashOperations.get(key, field);
        return null == value ? null : value.toString();
    }

    /**
     * 根据键/域组合参数获取哈希表中的字串值
     *
     * @param key    键
     * @param field  域
     * @param expire 过期时间(单位:秒)
     * @return 字串值
     */
    public static String hget(String key, String field, long expire) {
        if (expire != NOT_EXPIRE) redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        Object value = hashOperations.get(key, field);
        return null == value ? null : value.toString();
    }

    /**
     * 按默认过期时间向键映射的排重集合中添加值
     *
     * @param key   键
     * @param value 值
     * @return 被加入到Set集合中的元素数量
     */
    public static Long sadd(String key, String value) {
        return sadd(key, value, DEFAULT_EXPIRE);
    }

    /**
     * 按给定过期时间向键映射的排重集合中添加值
     *
     * @param key    键
     * @param value  值
     * @param expire 过期时间(单位:秒)
     * @return 被加入到Set集合中的元素数量
     */
    public static Long sadd(String key, String value, long expire) {
        Long count = setOperations.add(key, value);
        if (expire != NOT_EXPIRE) redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        return count;
    }

    /**
     * 判断给定的键映射的排重集合中是否存在指定的对象
     *
     * @param key   键
     * @param value 值
     * @return 集合中是否存在指定的参数value
     */
    public static boolean isMember(String key, Object value) {
        return setOperations.isMember(key, value);
    }

    /**
     * 移除键映射的排重集合中的值
     *
     * @param key   键
     * @param value 值
     * @return 被移除的元素数量
     */
    public static Long srem(String key, String value) {
        return setOperations.remove(key, value);
    }

    /**
     * 获取键映射的排重集合
     *
     * @param key 键
     * @return Set集合对象
     */
    public static Set<Object> smembers(String key) {
        return setOperations.members(key);
    }

    /**
     * 设置分布式锁
     * 在指定键的映射不存在时按指定的过期时长设置键到值的映射
     *
     * @param key    锁key
     * @param value  唯一标识符
     * @param expire 过期时间(单位:秒)
     * @return true 本方法调用前锁已存在,本次加锁失败;false 本方法调用前锁不存在,本次加锁成功
     */
    public static boolean getLock(String key, String value, int expire) {
        try {
            RedisCallback<Boolean> callback = (connection) -> connection.set(key.getBytes(Charset.forName("UTF-8")),
                    value.getBytes(Charset.forName("UTF-8")),
                    Expiration.seconds(TimeUnit.SECONDS.toSeconds(expire)),
                    RedisStringCommands.SetOption.SET_IF_ABSENT);
            return redisTemplate.execute(callback);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 释放分布式锁
     * 如果指定键的映射值与给定参数值值相等则从缓存中移除该键的映射
     *
     * @param key   键
     * @param value 值
     * @return false 解锁失败,true 解锁成功
     */
    public static boolean releaseLock(String key, String value) {
        RedisCallback<Boolean> callback = connection -> connection.eval(
                UNLOCK_LUA.getBytes(),
                ReturnType.BOOLEAN,
                1,
                key.getBytes(Charset.forName("UTF-8")),
                value.getBytes(Charset.forName("UTF-8")));
        return redisTemplate.execute(callback);
    }

    public static boolean expandLockTime(String key, String value, int lockTime) {
        RedisCallback<Boolean> callback = connection -> connection.eval(
                EXPAND_LUA.getBytes(),
                ReturnType.BOOLEAN,
                1,
                key.getBytes(Charset.forName("UTF-8")),
                value.getBytes(Charset.forName("UTF-8")),
                String.valueOf(lockTime).getBytes());
        return redisTemplate.execute(callback);
    }


}

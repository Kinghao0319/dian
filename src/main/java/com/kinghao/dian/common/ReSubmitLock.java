package com.kinghao.dian.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author kinghao
 * @version 2020/10/26 15:00
 */
@Slf4j
public final class ReSubmitLock {


    private static final ConcurrentHashMap LOCK_CACHE = new ConcurrentHashMap<>(200);
    private static final ScheduledThreadPoolExecutor EXECUTOR = new ScheduledThreadPoolExecutor(5, new ThreadPoolExecutor.DiscardPolicy());


   // private static final CacheCACHES = CacheBuilder.newBuilder()
            // 最大缓存 100 个
   // .maximumSize(1000)
            // 设置写缓存后 5 秒钟过期
   // .expireAfterWrite(5, TimeUnit.SECONDS)
   // .build();


    private ReSubmitLock() {
    }

    /**
     * 静态内部类 单例模式
     *
     */
    private static class SingletonInstance {
        private static final ReSubmitLock INSTANCE = new ReSubmitLock();
    }

    public static ReSubmitLock getInstance() {
        return SingletonInstance.INSTANCE;
    }


    public static String handleKey(String param) {
        return DigestUtils.md5Hex(param == null ? "" : param);
    }

    /**
     * 加锁 putIfAbsent 是原子操作保证线程安全
     *
     * @param key 对应的key
     * @param value
     * @return
     */
    public boolean lock(final String key, Object value) {
        return Objects.isNull(LOCK_CACHE.putIfAbsent(key, value));
    }

    /**
     * 延时释放锁 用以控制短时间内的重复提交
     *
     * @param lock 是否需要解锁
     * @param key 对应的key
     * @param delayMilliseconds 延时时间
     */
    public void unLock(final boolean lock, final String key, final long delayMilliseconds) {
        if (lock) {
            EXECUTOR.schedule(() -> {
                LOCK_CACHE.remove(key);
            }, delayMilliseconds, TimeUnit.MILLISECONDS);
        }
    }
}
package com.hmdp.utils;

public interface ILock {

    /**
     * @Description: 尝试获取锁
     * @Param: timeoutSec 锁持有的超时时间，过期后自动释放
     * @return: boolean true代表获取锁成功，false代表获取锁失败
     * @Author: stone
     * @Date: 2023-3-27 21:59:25
     */
    boolean tryLock(long timeoutSec);

    /**
     * @Description: 释放锁
     * @Author: stone
     * @Date: 2023-3-27 22:0:26
     */
    void unlock();
}

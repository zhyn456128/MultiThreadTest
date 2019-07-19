package com.alibaba.LockTest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest implements Runnable{
    private Lock lock = new ReentrantLock();

    public void run() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "获得了锁");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println(Thread.currentThread().getName() + "释放了锁");
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        LockTest lockTest = new LockTest();

        Thread t1 = new Thread(lockTest,"t1");
        Thread t2 = new Thread(lockTest,"t2");

        t1.start();
        t2.start();
    }
}

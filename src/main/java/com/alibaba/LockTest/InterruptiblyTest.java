package com.alibaba.LockTest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class InterruptiblyTest implements Runnable{
    Lock lock = new ReentrantLock();

    public void run() {
        String tName = Thread.currentThread().getName();
        try {
            System.out.println(tName + "-开始获取锁..........");
            lock.lockInterruptibly();

            System.out.println(tName + "-获取到锁了！！！！");
            System.out.println(tName + "-睡觉了，睡个30秒！");
            Thread.sleep(30000);
            System.out.println(tName + "-睡醒了，干活！");
            for (int i = 0; i < 5; i++) {
                System.out.println(tName + "：" + i);
            }
        } catch (Exception e) {
            System.out.println(tName+"-我好像被中断了！");
            e.printStackTrace();
        }finally{
            lock.unlock();
            System.out.println(tName + "-释放了锁");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        InterruptiblyTest interruptiblyTest = new InterruptiblyTest();
        Thread t1 = new Thread(interruptiblyTest,"t1");
        Thread t2 = new Thread(interruptiblyTest,"t2");
        t1.start();
        Thread.sleep(5000);
        t2.start();
        Thread.sleep(3000);
        System.out.println("t2线程状态："+t2.getState());
        t2.interrupt();
        System.out.println("t2线程状态："+t2.getState());
    }
}

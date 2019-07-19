package com.alibaba;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 一、用于解决多线程安全问题的方式：
 * 1.同步代码块 synchronized 隐式锁
 * 2.同步方法 synchronized 隐式锁
 * 3.同步锁Lock (jdk1.5以后) 显示锁
 * 注意：显示锁，需要通过lock()方式上锁，必须通过unlock()方式进行释放锁
 */
public class Test {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        new Thread(ticket, "1号窗口").start();
        new Thread(ticket, "2号窗口").start();
        new Thread(ticket, "3号窗口").start();
    }
}

class Ticket implements Runnable {

    private int tick = 400;

    private Lock lock = new ReentrantLock();


    public void run() {
        while (tick > 0) {

            lock.lock();

            try {
                if (tick > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " 完成售票，余票为 " + --tick);
                }
            } finally {
                lock.unlock();
            }

        }
    }
}
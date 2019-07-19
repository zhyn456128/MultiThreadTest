package com.alibaba.ReadWriteLockTest;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteTest implements Runnable{

    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    /**
     * 读操作,用读锁来锁定
     */
    public void get() {
        readWriteLock.readLock().lock();
        try {
            long start = System.currentTimeMillis();

            while(System.currentTimeMillis() - start <= 1) {
//                System.out.println(thread.getName()+"正在进行读操作");
                System.out.println(Thread.currentThread().getName()+"正在进行读操作");
            }
//            System.out.println(thread.getName()+"读操作完毕");
            System.out.println(Thread.currentThread().getName()+"读操作完毕");

        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    /**
     * 写操作，用写锁来锁定
     */
    public void write() {
        readWriteLock.writeLock().lock();
        try {
            long start = System.currentTimeMillis();

            while(System.currentTimeMillis() - start <= 1) {
                System.out.println(Thread.currentThread().getName()+"正在进行写操作");
            }
            System.out.println(Thread.currentThread().getName()+"写操作完毕");
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void run() {
        get();
        write();
    }

    public static void main(String[] args) {
        ReentrantReadWriteTest reentrantReadWriteTest = new ReentrantReadWriteTest();
        Thread t1 = new Thread(reentrantReadWriteTest,"t1");
        Thread t2 = new Thread(reentrantReadWriteTest,"t2");
        t1.start();
        t2.start();
    }
}

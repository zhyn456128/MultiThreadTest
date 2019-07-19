package com.alibaba.LockTest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockTest implements Runnable{
    Lock lock = new ReentrantLock();

    public void run() {
        boolean trylock = lock.tryLock();
        System.out.println(Thread.currentThread().getName()+':'+trylock);
        if(trylock){
            try {
                System.out.println(Thread.currentThread().getName()+"得到了锁");
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName()+"释放了锁");
            }
        }else{
            System.out.println(Thread.currentThread().getName()+"没有拿到锁");
        }
    }
    public static void main(String[] args) {
        TryLockTest tryLockTest = new TryLockTest();
        Thread t1 = new Thread(tryLockTest,"t1");
        Thread t2 = new Thread(tryLockTest,"t2");
        Thread t3 = new Thread(tryLockTest,"t3");
        t3.start();
        t1.start();
        t2.start();
    }
}

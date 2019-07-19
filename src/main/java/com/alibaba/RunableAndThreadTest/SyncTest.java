package com.alibaba.RunableAndThreadTest;

public class SyncTest implements Runnable {
    //共享资源变量
    int count = 0;
    private byte[] mBytes = new byte[0];

    public  void run() {
        increaseCount();
    }

    private void increaseCount() {
        //假设省略了其他操作的代码。
        //同步在代码块上，对对象起作用
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + ":" + count++);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SyncTest syncTest1 = new SyncTest();
        SyncTest syncTest2 = new SyncTest();
        Thread thread1 = new Thread(syncTest1, "thread1");
        Thread thread2 = new Thread(syncTest1, "thread2");
        thread1.start();
        thread2.start();
    }
}
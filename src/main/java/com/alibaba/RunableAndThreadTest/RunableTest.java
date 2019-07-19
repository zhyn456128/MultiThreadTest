package com.alibaba.RunableAndThreadTest;

public class RunableTest implements Runnable{
    int num = 0;
    public void run(){
        synchronized (RunableTest.class){
            for(int i=0;i<5;i++){
                System.out.println(Thread.currentThread().getName() + ":" + num++);
                try {
                    Thread.sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        RunableTest runableTest1 = new RunableTest();
        RunableTest runableTest2 = new RunableTest();
        RunableTest runableTest3 = new RunableTest();
        Thread t1 = new Thread(runableTest1,"一号");
        Thread t2 = new Thread(runableTest2,"二号");
        Thread t3 = new Thread(runableTest3,"三号");
        t1.start();
        t2.start();
        t3.start();
    }
}

package com.alibaba.RunableAndThreadTest;


public class ThreadTest extends Thread{
    private int num = 10;
    private String name;
    public ThreadTest(String name){
        this.name = name;
    }
    @Override
    public void run() {
        for(int i=0;i<=100;i++){
            if(num>0){
                num--;
                System.out.println(this.name+" :"+ num);
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e){

                }
            }
        }
    }

    public static void main(String[] args) {
        ThreadTest threadTest1 = new ThreadTest("一号");
        ThreadTest threadTest2 = new ThreadTest("二号");
        threadTest1.start();
        threadTest2.start();

    }
}

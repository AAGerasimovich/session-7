package ru.sbt.jschool.session7;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Problem1 {



    static volatile Integer count = 50;
    static Collection<Runnable> taskList = new ArrayList<>();
    public static void main(String[] args) {

        Thread thread = threadProducer();
        taskList.add(thread);
        thread.start();

    }
    public static Thread threadProducer(){

        return new Thread(new Runnable() {

            @Override
            public void run() {

                int n = Integer.parseInt(Thread.currentThread().getName()
                        .substring(7, Thread.currentThread().getName().length()));
                if (n==51){
                    return;
                }
                Thread thread = threadProducer();
                taskList.add(thread);
                thread.start();

                synchronized (taskList) {
                    while (n != count) {
                        try {
                            taskList.wait();
                        }catch (Exception e){}
                    }
                    if (n == count) {
                        System.out.println("Hello from Thread-" + n);
                        count--;
                        taskList.notifyAll();
                        return;
                    }
                }
            }
        });
    }
}

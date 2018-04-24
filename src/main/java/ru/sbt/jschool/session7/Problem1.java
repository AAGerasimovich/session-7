package ru.sbt.jschool.session7;

public class Problem1 {

    public static void main(String[] args) {


        Thread thread = threadProducer();


        thread.start();

    }
    public static Thread threadProducer(){

        return new Thread(new Runnable() {

            @Override
            public void run() {
                int n = 50 - Integer.parseInt(Thread.currentThread().getName()
                        .substring(7, Thread.currentThread().getName().length()));
                System.out.println("Hello from Thread-" + n);
                if (n==0){
                    return;
                }
                threadProducer().start();
            }

        });



    }
}

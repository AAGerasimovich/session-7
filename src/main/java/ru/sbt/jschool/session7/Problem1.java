package ru.sbt.jschool.session7;

public class Problem1 {



    static volatile int count = 50;
    public static void main(String[] args) {


        Thread thread = threadProducer();


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

                threadProducer().start();

                while (n!=count){

                }
                if (n==count) {
                        System.out.println("Hello from Thread-" + n);
                        count--;
                        return;
                }




            }

        });



    }
}

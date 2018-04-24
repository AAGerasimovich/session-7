package ru.sbt.jschool.session7;

public class Problem2 {

    public static void main(String[] args) throws Exception{
        Thread[] threads = new Thread[2];
        for (int i = 0; i < threads.length; i++) {


            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (threads) {

                        while (true) {
                            threads.notify();
                            System.out.println(Thread.currentThread().getName());

                            try {
                                Thread.sleep(500);
                                threads.wait();
                            } catch (Exception e){

                            }
                        }
                    }
                }
            });
        }


        for (int i = 0; i < threads.length; i++) {
                threads[i].start();
        }
    }

}



package ru.sbt.jschool.session7;

public class Problem4 {

    public static final int COUNT_THREADS = 10;
    public static int counter = 0;

    public static void main(String[] args) throws Exception{
        Thread[] threads = new Thread[COUNT_THREADS];
        for (int i = 0; i < threads.length; i++) {


            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (threads) {
                        int selfCnt = 10;
                        int i = counter;

                        while (selfCnt>0) {

                            if (counter%10==i){
                                counter++;
                                selfCnt--;
                                threads.notifyAll();
                                if (counter==COUNT_THREADS*10){
                                    return;
                                }
                            }
                            try {
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

        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }
        System.out.println(counter);

    }

}



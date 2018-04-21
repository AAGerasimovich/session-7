package ru.sbt.jschool.session7;

public class Problem3 {

    public static final int COUNT_THREADS = 6;
    public static int counter = 0;

    public static void main(String[] args) throws Exception{
        Thread[] threads = new Thread[COUNT_THREADS];
        for (int i = 0; i < threads.length; i++) {


            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (threads) {
                        int selfCnt = counter;

                        while (true) {

                            if (counter==selfCnt){
                                System.out.println("Поток " + (counter +1) ) ;
                                counter++;
                                if (counter==COUNT_THREADS){

                                    counter = 0;
                                }
                                threads.notifyAll();

                            }
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



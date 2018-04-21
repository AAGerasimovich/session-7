package ru.sbt.jschool.session7;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
public class Problem5 {



    public static void main(String[] args) throws Exception{
        Queue<List<String>> files = new ArrayDeque<>();
        Thread[] threads = new Thread[2];
        Thread reader = new Thread(new Runnable() {
            @Override
            public void run() {
                read();
            }
            private void read(){
                synchronized (files){

                    if (files.isEmpty()) {
                        try {
                            files.wait();
                        } catch (InterruptedException e) {}
                    }

                    do {
                        int count = files.poll().stream().collect(Collectors.joining(" ")).split("\\s+").length;
                        System.out.println(count);
                        if (files.isEmpty()) {
                            try {
                                files.wait();
                            } catch (InterruptedException e) {}
                        }
                     }while (!files.isEmpty());
                }
            }
        });
        for (int i = 0; i < threads.length; i++) {

            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {

                    File dir = new File("./");
                    Queue<String> filesLocal = Arrays.stream(Objects.requireNonNull(dir.list((folder1, name) ->
                            name.endsWith(".txt"))))
                            .collect(Collectors.toCollection(ArrayDeque::new));

                    synchronized (files) {

                        while (!filesLocal.isEmpty()) {
                            try {
                                files.notifyAll();
                                files.add(Files.lines(Paths.get(filesLocal.poll()), StandardCharsets.UTF_8).collect(Collectors.toList()));

                                } catch (Exception e){}
                        }
                    }
                }
            });
        }


        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
        reader.start();

        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }

        reader.join();






    }

}



package com.clouway;

import java.util.Scanner;

public class Main {

    public static class Counter implements Runnable{

        private int count = 0;
        private int limit;
        private final String name;

        public Counter(int limit, String name){
            this.name = name;
            this.limit = limit;
        }

        @Override
        public void run() {

            while (count < limit) {
                try {
                    Thread.sleep(100);
                    count++;
                    System.out.println(name + " is at : " + count);
                } catch (InterruptedException e) {
                    System.out.println(name + " was interrupted at " + count);
                }
            }
            System.out.println(name + " finished counting to " + limit);
        }
    }



    public static void main(String[] args) {

        Counter counter1 = new Counter(30, "First thread");
        Counter counter2 = new Counter(15, "Second thread");

        Thread thread1 = new Thread(counter1);
        Thread thread2 = new Thread(counter2);

        thread1.start();
        thread2.start();
    }

}

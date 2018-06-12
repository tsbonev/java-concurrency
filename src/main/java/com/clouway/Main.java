package com.clouway;

import java.util.Currency;

public class Main {

    static boolean isFinished = false;

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

                if(isFinished){
                    Thread.currentThread().interrupt();
                }

                try {
                    Thread.sleep(100);
                    count++;
                    System.out.println(name + " is at : " + count);
                } catch (InterruptedException e) {
                    System.out.println(name + " was interrupted at " + count);
                    return;
                }
            }

                isFinished = true;
                System.out.println(name + " finished counting to " + limit);

        }
    }



    public static void main(String[] args) {

        Counter counter1 = new Counter(3, "First thread");
        Counter counter2 = new Counter(5, "Second thread");

        Thread thread1 = new Thread(counter1);
        thread1.start();
        Thread thread2 = new Thread(counter2);
        thread2.start();
        //Does this count as two thread classes?
    }

}

package com.clouway;

import java.util.Scanner;

public class Main {

    public static class Counter implements Runnable{

        private int count = 0;
        private int limit;

        public Counter(int limit){
            this.limit = limit;
        }

        @Override
        public void run() {
            while (count <= limit) {
                try {
                    Thread.sleep(50);
                    count++;
                } catch (InterruptedException e) {
                    System.out.println("I was interrupetd at " + count);
                }
            }
            System.out.println("I finished counting to " + limit);
        }
    }



    public static void main(String[] args) {

        Counter counter = new Counter(30);

        Thread thread = new Thread(counter);
        thread.start();

        Scanner scanner = new Scanner(System.in);

        if (scanner.next() != null) {
            thread.interrupt();
        }

    }

}

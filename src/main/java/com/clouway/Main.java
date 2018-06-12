package com.clouway;

import java.util.Scanner;

public class Main {


    private static int count = 0;

    public static class Counter implements Runnable{

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(100);
                    count++;
                } catch (InterruptedException e) {
                }

            }
        }
    }



    public static void main(String[] args) {

        Thread thread = new Thread(new Counter());

        thread.start();

        Scanner scanner = new Scanner(System.in);

        if (scanner.next() != null) {
            thread.interrupt();
            System.out.println(count);
        }

    }

}

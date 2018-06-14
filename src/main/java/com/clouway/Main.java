package com.clouway;

public class Main {


    public static void main(String[] args) throws InterruptedException {

        TimeoutHashtable<Integer, Integer> hashtable = new TimeoutHashtable(7000);

        hashtable.put(1, 123);
        hashtable.put(2, 321);

        System.out.println("Waiting for 6 seconds");
        Thread.currentThread().sleep(6000);
        System.out.println(hashtable.get(2));

        System.out.println("Waiting for another 6 seconds");
        Thread.currentThread().sleep(6000);

        System.out.println(hashtable.get(2));
        System.out.println(hashtable.get(1));
        System.out.println(hashtable.remove(1));

        System.out.println("Waiting for 8 seconds");
        Thread.currentThread().sleep(8000);
        System.out.println(hashtable.get(2));
        System.out.println(hashtable.remove(2));
    }

}

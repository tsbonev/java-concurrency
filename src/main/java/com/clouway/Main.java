package com.clouway;

public class Main implements Runnable {


    @Override
    public void run() {
        System.out.println("Hello from a thread!");
    }

    public static void main(String[] args){

        (new Thread(new Main())).start();

    }

}

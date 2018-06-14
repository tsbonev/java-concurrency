package com.clouway;

import com.clouway.ExceptionList.ExceptionList;

import java.util.ArrayList;
import java.util.List;


public class Main {


    public static class Adder implements Runnable{

        private ExceptionList list;

        private int elemToAdd = 1;

        public void setList(ExceptionList list) {
            this.list = list;
        }

        @Override
        public void run() {

            while(true){

                this.list.add(elemToAdd);
                try {
                    Thread.currentThread().sleep((long)(Math.random() * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static class Remover implements Runnable{

        private ExceptionList list;

        public void setList(ExceptionList list) {
            this.list = list;
        }

        @Override
        public void run(){

            while (true){

                this.list.remove();
                try {
                    Thread.currentThread().sleep((long)(Math.random() * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

    }


    public static void main(String[] args) {

        ExceptionList list = new ExceptionList();


        List<Thread> threads = new ArrayList<>();


        for(int i = 0; i < 2; i++){
            Adder adder = new Adder();
            Remover remover = new Remover();
            adder.setList(list);
            remover.setList(list);
            threads.add(new Thread(adder));
            threads.add(new Thread(remover));
        }

        for (Thread t : threads) {
            t.start();
        }

    }

}

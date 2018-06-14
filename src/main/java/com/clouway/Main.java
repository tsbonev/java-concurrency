package com.clouway;

import com.clouway.ExceptionList.ExceptionList;


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

        Adder adder1 = new Adder();
        Adder adder2 = new Adder();
        adder1.setList(list);
        adder2.setList(list);

        Remover remover1 = new Remover();
        Remover remover2 = new Remover();
        remover1.setList(list);
        remover2.setList(list);

        Thread thread1 = new Thread(adder1);
        Thread thread2 = new Thread(adder2);
        Thread thread3 = new Thread(remover1);
        Thread thread4 = new Thread(remover2);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

    }

}

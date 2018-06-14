package com.clouway;

public class Main {

    static boolean isFinished = false;

    public static synchronized boolean getIsFinished() {
        return isFinished;
    }

    public static synchronized void setIsFinished(boolean signal) {
        isFinished = signal;
    }

    public static synchronized void count(int count){


        try{
            if (getIsFinished()) {
                throw new InterruptedException();
            }
            count++;
            System.out.println(Thread.currentThread().getName() + " is at : " + count);

        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " was interrupted at " + count);
            return;
        }


    }


    public static class Counter implements Runnable {

        private int count = 0;
        private int limit;
        private boolean hasRun = true;
        private Counter next;

        public Counter(int limit) {
            this.limit = limit;
        }

        public void setNext(Counter next) {
            this.next = next;
        }

        public void setHasRun(boolean hasRun) {
            this.hasRun = hasRun;
        }

        @Override
        public synchronized void run() {

            try{
                while (count < limit) {

                    next.setHasRun(false);

                    if(getIsFinished()){
                       System.out.println(Thread.currentThread().getName() + " was interrupted at " + count);
                        return;
                    }

                    if(!hasRun){
                        count(count);
                        count++;
                        if(count == limit){
                            throw new InterruptedException();
                        }
                        hasRun = true;
                    }

                }
            }catch (InterruptedException e){
                setIsFinished(true);
                System.out.println(Thread.currentThread().
                        getName() + " finished counting to " + limit);
            }

        }

    }


    public static void main(String[] args) {

        Counter counter1 = new Counter(15);
        Counter counter2 = new Counter(7);

        counter1.setNext(counter2);
        counter2.setNext(counter1);

        Thread thread1 = new Thread(counter1);
        Thread thread2 = new Thread(counter2);

        thread1.start();
        thread2.start();


    }

}

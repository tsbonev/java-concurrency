package com.clouway;

public class Main {

    static boolean isFinished = false;

    public static synchronized boolean getIsFinished() {
        return isFinished;
    }

    public static synchronized void setIsFinished(boolean signal) {
        isFinished = signal;
    }


    public static class Counter implements Runnable {

        private int count = 0;
        private int limit;

        public Counter(int limit) {
            this.limit = limit;
        }

        @Override
        public synchronized void run() {

            while (count < limit) {

                try {
                    if (getIsFinished()) {
                        throw new InterruptedException();
                    }

                    Thread.currentThread().sleep((long) (Math.random() * 100));

                    count++;
                    System.out.println(Thread.currentThread().getName() + " is at : " + count);

                    if(count == limit){
                        break;
                    }

                    notifyAll();
                    wait(1000);

                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " was interrupted at " + count);
                    return;
                }
            }

            setIsFinished(true);
            System.out.println(Thread.currentThread().
                    getName() + " finished counting to " + limit);

        }

    }


    public static void main(String[] args) throws InterruptedException {

        Counter counter1 = new Counter(15);
        Counter counter2 = new Counter(7);

        Thread thread1 = new Thread(counter1);
        Thread thread2 = new Thread(counter2);

        thread1.start();
        Thread.currentThread().sleep(200);
        thread2.start();


    }

}

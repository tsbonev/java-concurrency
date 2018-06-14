package com.clouway.ExceptionList;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class ExceptionList {

    ArrayList<Object> attributes;
    static final int SIZE = 1;

    private synchronized int getSize(){
        return attributes.size();
    }

    public ExceptionList() {
        this.attributes = new ArrayList<>(SIZE);
    }

    public synchronized void add(Object obj) {

        try {
            while (getSize() + 1 > SIZE) {
                System.out.println(Thread.currentThread().getName() + " Waiting for elem to be removed");
                wait();
            }
            attributes.add(obj);
            System.out.println(Thread.currentThread().getName() + " Successfully added " + obj);

            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public synchronized void remove() {

        try {
            while (getSize() == 0) {
                System.out.println(Thread.currentThread().getName() + " Waiting for elem to be added");
                wait();
            }
            System.out.println(Thread.currentThread().getName() + " Successfully removed " + attributes.get(attributes.size() - 1));

            notifyAll();
            attributes.remove(attributes.get(attributes.size() - 1));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public synchronized void printAllElements() {
        for (Object obj : attributes
                ) {
            System.out.println(obj);
        }
    }


}

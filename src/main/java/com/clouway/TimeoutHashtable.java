package com.clouway;

import java.util.Hashtable;
import java.util.Map;

public class TimeoutHashtable<K, T> {

    private class TimeoutTimer implements Runnable {

        private K key;

        public TimeoutTimer(K key){
            this.key = key;
        }

        @Override
        public synchronized void run() {
            try {
                wait(timeoutTime);
                hashtable.remove(key);
            } catch (InterruptedException e) {
            }
        }
    }

    private long timeoutTime;

    private Map<K, Thread> timeout = new Hashtable<>();

    private Map<K, T> hashtable = new Hashtable<>();

    public TimeoutHashtable(long timeoutTime){
        this.timeoutTime = timeoutTime;
    }

    public void put(K key, T value){

        if(hashtable.containsKey(key)) {
            refreshTimeout(key);
        }else {
            timeout.put(key, getTimeoutThread(key));
        }

        hashtable.put(key, value);

    }

    public T get (K key){

        T value = hashtable.get(key);

        if(value != null) refreshTimeout(key);

        return value;
    }

    public T remove(K key){

        timeout.get(key).interrupt();
        timeout.remove(key);

        return hashtable.remove(key);
    }

    private Thread getTimeoutThread(K key){
        Thread thread = new Thread(new TimeoutTimer(key));
        thread.start();
        return thread;
    }


    private void refreshTimeout(K key){
        timeout.get(key).interrupt();
        timeout.put(key, getTimeoutThread(key));
    }


}

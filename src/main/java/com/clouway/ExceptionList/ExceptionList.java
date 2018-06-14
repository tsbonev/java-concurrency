package com.clouway.ExceptionList;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class ExceptionList {

    ArrayList<Object> attributes;
    static final int SIZE = 1;

    public ExceptionList(){
        this.attributes = new ArrayList<Object>(SIZE);
    }

    public void Add(Object obj){
        try{
            if(attributes.size() + 1 > SIZE){
                throw new IndexOutOfBoundsException();
            }
            attributes.add(obj);
            System.out.println("Successfully added " + obj);
        }
        catch (IndexOutOfBoundsException e){
            System.out.println("Caught exception: " + e);
        }
    }

    public void Remove(){
        try{
            if(attributes.size() == 0){
                throw new EmptyStackException();
            }
            System.out.println("Successfully removed " + attributes.get(attributes.size() - 1));
            attributes.remove(attributes.get(attributes.size() - 1));
        }
        catch (EmptyStackException e){
            System.out.println("Caught exception: " + e);
        }
    }

    public void printAllElements(){
        for (Object obj : attributes
             ) {
            System.out.println(obj);
        }
    }



}

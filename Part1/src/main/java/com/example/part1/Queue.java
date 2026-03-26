package com.example.part1;


import java.nio.BufferUnderflowException;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class Queue<AnyType> {
    private AnyType[] theArray;
    private int currentSize;
    private int front;
    private int back;
    private static final int DEFAULT_CAPACITY = 10;

    public Queue(){
        theArray = (AnyType[]) new Object[DEFAULT_CAPACITY];
        makeEmpty();
    }

    public Queue(int size){
        theArray = (AnyType[]) new Object[size];
        makeEmpty();
    }

    public void makeEmpty(){
        currentSize = 0;
        front = 0;
        back = -1;
    }

    public boolean isEmpty(){
        return currentSize == 0;
    }

    private int increment(int x){
        if(++x == theArray.length){
            x = 0;
        }
        return x;
    }

    private void doubleQueue(){
        AnyType[] newArray = (AnyType[]) new Object[theArray.length * 2];
        for(int i = 0; i< currentSize; i++, front = increment(front)){
            newArray[i] = theArray[front];
        }
        theArray = newArray;
        front = 0;
        back = currentSize - 1;
    }

    public AnyType dequeue(){
        if(isEmpty()){
            throw new BufferUnderflowException();
        }
        AnyType frontElement = theArray[front];
        front = increment(front);
        currentSize--;
        return frontElement;
    }

    public void enqueue(AnyType x){
        if(currentSize+1 == theArray.length){
            doubleQueue();
        }
        back = increment(back);
        theArray[back] = x;
        currentSize++;
    }

    public AnyType getFront(){
        if(isEmpty()){
            throw new BufferUnderflowException();
        }
        return theArray[front];
    }

    public void showElements(){
        int frontCopy = front;
        int currentCopy = currentSize;
        while(currentCopy > 0){
            System.out.print(theArray[frontCopy] + " ");
            frontCopy = increment(frontCopy);
            currentCopy--;

        }
    }

    public void showEl(){
        if(isEmpty()){
            throw new BufferUnderflowException();
        }
        int temp = front;
        for(int i = 0; i < currentSize; i++, temp = increment(temp)){
            System.out.println(theArray[temp]);
        }
    }




    public void showInverse(){
        int backCopy = back;
        int currentCopy = currentSize;

        while(currentCopy > 0){
            System.out.print(theArray[backCopy] + " ");
            currentCopy--;
            backCopy = decrement(backCopy);
        }

    }


    private int decrement(int x){
        if(--x == -1){
            x = theArray.length - 1;
        }
        return x;
    }

    public Queue<AnyType> clone(){
        Queue<AnyType> clone = new Queue<AnyType>();
        int currentCopy = currentSize;
        int frontCopy = front;
        while(currentCopy > 0){
            clone.enqueue(theArray[frontCopy]);
            frontCopy = increment(frontCopy);
            currentCopy --;

        }
        return clone;
    }

    public Queue<AnyType> clone2(){
        Queue<AnyType> clone = new Queue<AnyType>();
        clone.theArray = theArray;
        clone.front = front;
        clone.back = back;
        clone.currentSize = currentSize;
        return clone;
    }


    public AnyType findMinimum(Comparator<AnyType> cmp) {
        if (currentSize == 0) {
            throw new NoSuchElementException("Queue is empty.");
        }

        int currentCopy = currentSize;
        int frontCopy = front;
        AnyType min = theArray[frontCopy];

        while (currentCopy > 0) {
            frontCopy = increment(frontCopy);

            if (theArray[frontCopy] != null && cmp.compare(theArray[frontCopy], min) < 0) {
                min = theArray[frontCopy];
            }

            currentCopy--;
        }
        return min;
    }

    public AnyType findMaximum(Comparator<AnyType> cmp) {
        if (currentSize == 0) {
            throw new NoSuchElementException("Queue is empty.");
        }

        int currentCopy = currentSize;
        int frontCopy = front;
        AnyType max = theArray[frontCopy];

        while (currentCopy > 0) {
            frontCopy = increment(frontCopy);

            if (theArray[frontCopy] != null && cmp.compare(theArray[frontCopy], max) > 0) {
                max = theArray[frontCopy];
            }

            currentCopy--;
        }
        return max;
    }

    public Queue<AnyType> invert(){
        Queue<AnyType> invert = new Queue<AnyType>();

        int sizeCopy = currentSize;

        int backCopy = back;
        while(sizeCopy > 0){
            invert.enqueue(theArray[backCopy]);
            backCopy = decrement(backCopy);
            sizeCopy--;
        }

        return invert;
    }


}

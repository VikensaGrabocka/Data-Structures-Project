package com.example.part2;
import com.example.part2.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ConcurrentModificationException;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class LinkedList<Contact> {
    private Node<com.example.part2.Contact> beginMarker;
    private Node<com.example.part2.Contact> endMarker;
    private Node<com.example.part2.Contact> NOT_FOUND = null;
    private int size;
    private int modCount = 0;
    private Node<com.example.part2.Contact> pointerForwards;
    private Node<com.example.part2.Contact> pointerBackWards;
    private Node<com.example.part2.Contact> currentContact;
    private Node<com.example.part2.Contact> currentContactFwd;
    private Node<com.example.part2.Contact> currentContactBcw;

    public LinkedList() {
        beginMarker = new Node<>(null, null, null);
        endMarker = new Node<>(null,  beginMarker, null);
        beginMarker.next = endMarker;
        size = 0;
        modCount ++;
        currentContactFwd = beginMarker;
        currentContactBcw = endMarker;
    }

    public int size( )
    {
        return size;
    }



    private static  LinkedList<com.example.part2.Contact> instance = new LinkedList<com.example.part2.Contact>();

    public static LinkedList<com.example.part2.Contact> getInstance()  {

        return instance;
    }

   //Playlist Management Operations
    public void addFirst (com.example.part2.Contact x){
        add(0,x);
    }

    public void addLast(com.example.part2.Contact x){
        add(size(), x);
    }

    public void add (int idx, com.example.part2.Contact x){
        Node<com.example.part2.Contact> p = getNode(idx,0,size);

        Node<com.example.part2.Contact> newNode = new Node<>(x, p.prev, p);
       newNode.prev.next = newNode;

        p.prev = newNode;
        size++;
        modCount++;
    }

    public void remove(int idx){
        remove(getNode(idx));
    }

    public void removeName(String name){
        Node<com.example.part2.Contact> current = beginMarker;
        while(current != endMarker){
            if(current.data!= null && current.data.getFirstName().equals(name)){
                remove(current);
            }
            current = current.next;
        }

    }

    public void removeSurname(String surname){
        Node<com.example.part2.Contact> current = beginMarker;
        while(current != endMarker){
            if(current.data != null && current.data.getLastName().equals(surname)){
                remove(current);

            }
            current = current.next;
        }
    }

    public void removeNameSurname(String name, String surname){
        Node<com.example.part2.Contact> current = beginMarker;
        while(current != endMarker){
            if(current.data!=null && current.data.getFirstName().equals(name) && current.data.getLastName().equals(surname)){
                remove(current);

            }
            current = current.next;
        }
    }

    public void traverseForward(ObservableList<com.example.part2.Contact> obsList){
        Node<com.example.part2.Contact> current = beginMarker;
        while(current != endMarker){
            if(current.data != null){
                obsList.add(current.data);
            }
          current = current.next;
        }
    }

    public void traverseBackward(ObservableList<com.example.part2.Contact> obsList){
        Node<com.example.part2.Contact> current = endMarker;
        while(current != beginMarker){
            if(current.data != null){
                obsList.add(current.data);
            }
            current = current.prev;
        }
    }

    public com.example.part2.Contact callNext(){
        if(size() != 0){
            if(currentContact == null){
                pointerForwards = currentContactFwd.next;
            }
            else{
                pointerForwards = currentContact.next;
            }

            if(pointerForwards != endMarker && pointerForwards.data != null){
                currentContact = pointerForwards;
                currentContactFwd = pointerForwards;
                return pointerForwards.data;
            }
        }

        return null;
    }

    public com.example.part2.Contact callPrevious(){
        if(currentContact == null){
            pointerBackWards = currentContactBcw.prev;
        }
        else{
            pointerBackWards = currentContact.prev;
        }
        if(size() != 0){
            pointerBackWards = currentContactBcw.prev;
            if(pointerBackWards != beginMarker && pointerBackWards.data != null){
                currentContact = pointerBackWards;
                currentContactBcw = pointerBackWards;
                return pointerBackWards.data;
            }
        }

        return null;
    }

    public com.example.part2.Contact callAgain(){
        if(currentContact != null){
            return currentContact.data;
        }
        return null;

    }

    public void shuffleContacts(ObservableList<com.example.part2.Contact> obsList){
        com.example.part2.Contact[] contacts = new com.example.part2.Contact[size()];
        if(size() != 0){
            Node<com.example.part2.Contact> current = beginMarker.next;
            for(int i = 0; i < size(); i++){
                if(current.data != null){
                    contacts[i] = current.data;
                }
                current = current.next;
            }

            SecureRandom random = new SecureRandom();
            for(int i = 0; i < size(); i++){
                int randomIndex = random.nextInt(size());
                com.example.part2.Contact temp = contacts[randomIndex];
                contacts[randomIndex] = contacts[i];
                contacts[i] = temp;
            }

            for(int i = 0; i < size(); i++){
                obsList.add(contacts[i]);
            }

        }

    }

    public boolean exists(com.example.part2.Contact x){
        if(beginMarker.next == endMarker){
            return false;
        }
        Node<com.example.part2.Contact> current = beginMarker.next;
        while(current != endMarker && beginMarker.next != endMarker){
            if(current.data.getFirstName().equals(x.getFirstName()) && current.data.getLastName().equals(x.getLastName())){
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean exists(String name, String surname){
        if(beginMarker.next == endMarker){
            return false;
        }
        Node<com.example.part2.Contact> current = beginMarker.next;
        while(current != endMarker ){
            if(current.data.getFirstName().equals(name) && current.data.getLastName().equals(surname)){
                return true;
            }
            current = current.next;
        }
        return false;
    }
    public boolean existsName(String name){
        if(beginMarker.next == endMarker){
            return false;
        }
        Node<com.example.part2.Contact> current = beginMarker.next;
        while(current != endMarker ){
            if(current.data.getFirstName().equals(name)){
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean existsSurname(String surname){
        if(beginMarker.next == endMarker){
            return false;
        }
        Node<com.example.part2.Contact> current = beginMarker.next;
        while(current != endMarker){
            if(current.data.getLastName().equals(surname)){
                return true;
            }
            current = current.next;
        }
        return false;
    }



    private com.example.part2.Contact remove(Node<com.example.part2.Contact> p )
    {
        p.next.prev = p.prev;
        p.prev.next = p.next;
        size--;
        modCount++;

        return p.data;
    }

    private Node<com.example.part2.Contact> getNode(int idx )
    {
    return getNode( idx, 0, size( ) - 1 );
    }


    private Node<com.example.part2.Contact> getNode(int idx, int lower, int upper){
        Node<com.example.part2.Contact> p;
        if(idx < lower || idx > upper){
            throw new IndexOutOfBoundsException();
        }
        if(idx<size()/2){
            p = beginMarker.next;
            for(int i = 0; i < idx; i++){
                p = p.next;
            }
            }else{
                p = endMarker;
                for(int i = size(); i > idx; i--){
                    p = p.prev;
                }
            }
            return p;
        }


    public void writeIntoFile() throws IOException {
        ObservableList<com.example.part2.Contact> obsList = FXCollections.observableArrayList();
        traverseForward(obsList);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("contacts.txt"))) {
            for (com.example.part2.Contact contact : obsList) {
                writer.write(contact.toString());
                writer.newLine();
            }
        }
    }



    private class Node<Contact> {
        private Node<com.example.part2.Contact> next;
        private com.example.part2.Contact data;
        private Node<com.example.part2.Contact> prev;

        public Node(com.example.part2.Contact d, Node<com.example.part2.Contact> p, Node<com.example.part2.Contact> n )
        {
            data = d; prev = p; next = n;
        }
    }

    }



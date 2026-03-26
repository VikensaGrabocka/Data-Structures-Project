package com.example.part1;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;


public class BinarySearchTree<Doctor>  {
      private static final String path = "doctors.txt";

      private BinaryNode<com.example.part1.Doctor> root;


    private static  BinarySearchTree<com.example.part1.Doctor> instance = new BinarySearchTree<com.example.part1.Doctor>();

    public static BinarySearchTree<com.example.part1.Doctor> getInstance()  {
        return instance;
    }



    public BinarySearchTree(){
        root = null;
    }


    public void insert(com.example.part1.Doctor doctor){
        root = insert(doctor, root);
    }

    public void delete(com.example.part1.Doctor d){
        root = remove(d,root);

    }

    public com.example.part1.Doctor find(int id )
      { return elementAt( find( id, root ) ); }


    public void displaySortedRecords(ObservableList<com.example.part1.Doctor> obsList){
        if(root != null){
            root.printInOrder(obsList);
        }

    }

    public void printPostOrder(ObservableList<com.example.part1.Doctor> obsList){
        if(root != null){
            root.printPostOrder(obsList);
        }
    }

    public void printLevelOrder(ObservableList<com.example.part1.Doctor> obsList){
        Queue<BinaryNode<com.example.part1.Doctor>> queue = new Queue<BinaryNode<com.example.part1.Doctor>>();
        if(root != null){
            queue.enqueue(root);
        }

        while(!queue.isEmpty()){
            BinaryNode<com.example.part1.Doctor> current = queue.dequeue();
            obsList.add(current.doctor);
            if(current.left != null){
                queue.enqueue(current.left);
            }
            if(current.right != null){
                queue.enqueue(current.right);
            }

        }
    }

    public void printPreOrder(ObservableList<com.example.part1.Doctor> obsList){
        if(root != null){
            root.printPreOrder(obsList);
        }
    }



    private com.example.part1.Doctor elementAt(BinaryNode<com.example.part1.Doctor> t )
    {
               return t == null ? null : t.doctor;
    }


    private BinaryNode<com.example.part1.Doctor> find(int x, BinaryNode<com.example.part1.Doctor> t ) {
        while (t != null) {
            if (x < t.doctor.getDoctor_id())
                t = t.left;
            else if (x > t.doctor.getDoctor_id())
                t = t.right;
            else
                return t;
        }
        return null;
    }




        private BinaryNode<com.example.part1.Doctor> insert(com.example.part1.Doctor d, BinaryNode<com.example.part1.Doctor> node){
        if(node == null){
            node = new BinaryNode<>(d);
        }else if(d.compareTo(node.doctor) < 0 ){
            node.left = insert(d, node.left);
        }else if(d.compareTo(node.doctor) > 0){
            node.right = insert(d,node.right);
        }else{
            throw new RuntimeException("This id value is already inserted!");
        }
        return node;

    }




    private BinaryNode<com.example.part1.Doctor> removeMin(BinaryNode<com.example.part1.Doctor> node)
    {
        if( node == null )
            throw new RuntimeException("Doctor not found!");
        else if( node.left != null )
        {
            node.left = removeMin( node.left );
            return node;
        }

        else{
            return node.right;
        }

    }

    private BinaryNode<com.example.part1.Doctor> findMin(BinaryNode<com.example.part1.Doctor> node){
        if(node != null){
            while(node != null){
                node = node.left;
            }
        }
        return node;

    }



    protected BinaryNode<com.example.part1.Doctor> remove(com.example.part1.Doctor d, BinaryNode<com.example.part1.Doctor> node )
     {
               if( node == null )
                     throw new RuntimeException( "Not found!");
                if( d.compareTo( node.doctor) < 0 )
                        node.left = remove( d, node.left );
                else if( d.compareTo( node.doctor ) > 0 )
                        node.right = remove( d, node.right );
                else if( node.left != null && node.right != null )
                   {
                       node.doctor= findMin( node.right ).doctor;
                       node.right = removeMin( node.right);
                    }
               else
                    node = ( node.left != null ) ? node.left : node.right;
                return node;
     }



    public static BinarySearchTree<com.example.part1.Doctor> readFromFile() throws IOException, ClassNotFoundException {
        BinarySearchTree<com.example.part1.Doctor> doctors = new BinarySearchTree<com.example.part1.Doctor>();
        try(BufferedReader reader = new BufferedReader(new FileReader("path"))) {
            String line;
            while((line = reader.readLine()) != null){
                String[] tokens = line.split(" ");
                int id = Integer.parseInt(tokens[0]);
                String name = tokens[1];
                String surname = tokens[2];
                String position = tokens[3];
                String department = tokens[4];
                String email = tokens[5];
                com.example.part1.Doctor doctor = new com.example.part1.Doctor(id,name, surname, position,department, email);
                doctors.insert(doctor);
            }
        }
        return doctors;
    }

    public void writeIntoFile() throws IOException{
        ObservableList<com.example.part1.Doctor> obsList = FXCollections.observableArrayList();
        printLevelOrder(obsList);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (com.example.part1.Doctor doctor : obsList) {
                String doctorString = doctor.getDoctor_id() + " "+doctor.getName()+" "+doctor.getSurname()+" "+
                                      doctor.getPosition() + " "+ doctor.getDepartment()+" "+doctor.getEmail();

                writer.write(doctorString);
                writer.newLine();
            }
        }
    }


    private class BinaryNode<Doctor>{
    private BinaryNode<com.example.part1.Doctor> left;
    private BinaryNode<com.example.part1.Doctor> right;
    private com.example.part1.Doctor doctor;


    public BinaryNode(com.example.part1.Doctor doctor){
        this.doctor = doctor;
        left = right = null;
    }


    public void printPreOrder(ObservableList<com.example.part1.Doctor> obsList) {
        obsList.add(doctor);
        if (left != null) {
            left.printPreOrder(obsList);
        }
        if(right != null) {
            right.printPreOrder(obsList);
        }
        }

    public void printPostOrder(ObservableList<com.example.part1.Doctor> obsList) {
        if (left != null) {
            left.printPostOrder(obsList);
        }
        if (right != null) {
            right.printPostOrder(obsList);
        }
        obsList.add(doctor);
        }

    public void printInOrder(ObservableList<com.example.part1.Doctor> obsList) {
        if (left != null) {
            left.printInOrder(obsList);
        }
        obsList.add(doctor);
        if(right != null) {
            right.printInOrder(obsList);
        }
        }

    }


}

package datastructureswithjava.linkedList;

class Node<T>{
    T value;
    Node<T> nextNode;
    Node(T value){
        this.nextNode = null;
        this.value = value;
    }
}

public class SinglyLinkedList <T>{
    Node<T> root = null;
    void addFirst(T value){
        if (root == null){
            root = new Node<T>(value);
        }
        else{
            Node<T> newNode = new Node<T>(value);
            newNode.nextNode = root;
            root = newNode;
        }
    }
    void addLast(T value){
        if (root == null){
            root = new Node<T>(value);
        } else{
            Node<T> current = root;
            Node<T> newNode = new Node<T>(value);
            while (current.nextNode != null){
                current = current.nextNode;
            }
            current.nextNode = newNode;
        }
    }
    void insertAt(T value,int index){
        if(size() == index){
            addLast(value);
        }
        else if (0 == index){
            addFirst(value);
        }
        else if (index > size()){
            System.out.println("Index Out Of Range");
        }
        else{
            Node <T> current = root;
            Node<T> previous = root;
            int currentIndex = 0;
            Node<T> newNode = new Node<T>(value);
            while (current != null){
                previous = current;
                current = current.nextNode;
                currentIndex++;
                if (currentIndex == index){
                    previous.nextNode = newNode;
                    newNode.nextNode = current;
                    break;
                }
            }
        }
    }
    void removeFirst(){
        if (root == null){
            System.out.println("Empty List");
        }
        else{
            System.out.println("Item Removed"+root.value);
            root = root.nextNode;
        }
    }
    void removeLast(){
        if (root == null){
            System.out.println("Empty List");
        }
        else{
            if (root.nextNode == null){
                removeFirst();
            }
            else {
                Node<T> current = root;
                Node<T> previous = root;
                while (current.nextNode != null) {
                    previous = current;
                    current = current.nextNode;
                }
                System.out.println("Item Removed" + current.value);
                previous.nextNode = null;
            }
        }
    }
    void removeAt(int index){
        if(size() <= index){
            System.out.println("Index Out Of Range");
        }
        else if(size()-1 == index){
            removeLast();
        }
        else if (index == 0){
            removeFirst();
        }
        else{
            Node<T> current = root;
            Node<T> previous = root;
            int currentIndex = 0;
            while (current != null){
                previous = current;
                current = current.nextNode;
                currentIndex++;
                if (currentIndex == index){
                    previous.nextNode = current.nextNode;
                    System.out.println("Item Removed"+current.value);
                    break;
                }
            }
        }
    }
    int size(){
        int size =0;
        Node<T> current = root;
        while (current != null){
            current = current.nextNode;
            size++;
        }
        return size;
    }
    void display(){
        if (root==null){
            System.out.println("List Is Empty");
        }else{
            Node<T> current = root;
            while (current != null){
                System.out.print(current.value+"->");
                current = current.nextNode;
            }
            System.out.println();
        }
    }
}

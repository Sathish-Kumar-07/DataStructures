package datastructureswithjava.linkedList;

class DNode<T>{
    T value;
    DNode<T> nextNode;
    DNode<T> previousNode;
    DNode(T value){
        this.nextNode = null;
        this.previousNode = null;
        this.value = value;
    }
}


public class DoublyLinkedList <T>{
    DNode<T> root = null;
    void addFirst(T value){
        if (root == null){
            root = new DNode<T>(value);
        }
        else{
            DNode<T> newNode = new DNode<T>(value);
            newNode.nextNode = root;
            root.previousNode = newNode;
            root = newNode;
        }
    }
    void addLast(T value){
        if (root == null){
            root = new DNode<T>(value);
        } else{
            DNode<T> current = root;
            DNode<T> newNode = new DNode<T>(value);
            while (current.nextNode != null){
                current = current.nextNode;
            }
            newNode.previousNode = current;
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
            DNode <T> current = root;
            DNode<T> previous = root;
            int currentIndex = 0;
            DNode<T> newNode = new DNode<T>(value);
            while (current != null){
                previous = current;
                current = current.nextNode;
                currentIndex++;
                if (currentIndex == index){
                    previous.nextNode = newNode;
                    newNode.previousNode = previous;
                    newNode.nextNode = current;
                    current.previousNode = newNode;
                    break;
                }
            }
        }
    }
    void removeFirst(){
        if (root == null){
            System.out.println("Empty List");
        }
        else if (root.nextNode == null){
            System.out.println("Item Removed"+root.value);
            root = null;
        }
        else{
            System.out.println("Item Removed"+root.value);
            root = root.nextNode;
            root.previousNode = null;
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
                DNode<T> current = root;
                DNode<T> previous = root;
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
            DNode<T> current = root;
            DNode<T> previous = root;
            int currentIndex = 0;
            while (current != null){
                previous = current;
                current = current.nextNode;
                currentIndex++;
                if (currentIndex == index){
                    previous.nextNode = current.nextNode;
                    current.nextNode.previousNode = previous;
                    System.out.println("Item Removed"+current.value);
                    break;
                }
            }
        }
    }
    int size(){
        int size =0;
        DNode<T> current = root;
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
            DNode<T> current = root;
            while (current != null){
                System.out.print(current.value+"->");
                current = current.nextNode;
            }
            System.out.println();
        }
    }
    void displayReverse(){
        if (root==null){
            System.out.println("List Is Empty");
        }else{
            DNode<T> current = root;
            while (current.nextNode != null){
                current = current.nextNode;
            }
            while (current != null){
                System.out.print(current.value+"->");
                current = current.previousNode;
            }
            System.out.println();
        }
    }
}

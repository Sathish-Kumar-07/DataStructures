package datastructureswithjava.linkedList;

class CNode<T>{
    T value;
    CNode<T> nextNode;
    CNode(T value){
        this.nextNode = null;
        this.value = value;
    }
}

public class SinglyCircularLinkedList<T> {
    CNode<T> root = null;
    void addFirst(T value){
        if (root == null){
            root = new CNode<T>(value);
            root.nextNode = root;
        }
        else{
            CNode<T> newNode = new CNode<T>(value);
            newNode.nextNode = root;
            CNode<T> current = root;
            while (current.nextNode != root){
                current = current.nextNode;
            }
            root = newNode;
            current.nextNode = root;
        }
    }
    void addLast(T value){
        if (root == null){
            root = new CNode<T>(value);
            root.nextNode = root;
        } else{
            CNode<T> current = root;
            CNode<T> newNode = new CNode<T>(value);
            while (current.nextNode != root){
                current = current.nextNode;
            }
            current.nextNode = newNode;
            newNode.nextNode = root;
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
            CNode <T> current = root;
            CNode<T> previous = root;
            int currentIndex = 0;
            CNode<T> newNode = new CNode<T>(value);
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
        else if (root.nextNode == root){
            System.out.println("Item Removed"+root.value);
            root = null;
        }
        else{
            System.out.println("Item Removed"+root.value);
            CNode<T> current = root;
            while (current.nextNode != root){
                current = current.nextNode;
            }
            root = root.nextNode;
            current.nextNode = root;
        }
    }
    void removeLast(){
        if (root == null){
            System.out.println("Empty List");
        }
        else{
            if (root.nextNode == root){
                removeFirst();
            }
            else {
                CNode<T> current = root;
                CNode<T> previous = root;
                while (current.nextNode != root) {
                    previous = current;
                    current = current.nextNode;
                }
                System.out.println("Item Removed" + current.value);
                previous.nextNode = root;
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
            CNode<T> current = root;
            CNode<T> previous = root;
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
        if (root != null) {
            CNode<T> current = root;
            while (current.nextNode != root) {
                current = current.nextNode;
                size++;
            }
            size++;
        }
        return size;
    }
    void display(){
        if (root==null){
            System.out.println("List Is Empty");
        }else{
            CNode<T> current = root;
            while (current.nextNode != root){
                System.out.print(current.value+"->");
                current = current.nextNode;
            }
            System.out.print(current.value+"->");
            System.out.println();
        }
    }
}

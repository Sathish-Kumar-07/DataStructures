package datastructureswithjava.trees;

import java.util.LinkedList;
import java.util.Queue;

class SplayNode<T extends Comparable<T>>{
    T value;
    SplayNode<T> leftChild= null, rightChild=null;
    SplayNode(T value){
        this.value = value;
    }
}

public class SplayTree <T extends Comparable<T>> {
    SplayNode<T> root = null;

    void levelOrder(){
        if (root!=null){
            Queue<SplayNode<T>> queue = new LinkedList<SplayNode<T>>();
            queue.add(root);
            while (queue.size()!=0){
                SplayNode<T> current = queue.peek();
                queue.remove();
                System.out.print(current.value + " ");
                if (current.leftChild != null) queue.add(current.leftChild);
                if (current.rightChild != null) queue.add(current.rightChild);
            }
            System.out.println();
        }
    }

    SplayNode<T> rightRotate(SplayNode<T> node){
        SplayNode<T> pivot = node.leftChild;
        node.leftChild = pivot.rightChild;
        pivot.rightChild = node;
        return pivot;
    }

    SplayNode<T> leftRotate(SplayNode<T> node){
        SplayNode<T> pivot = node.rightChild;
        node.rightChild = pivot.leftChild;
        pivot.leftChild = node;
        return pivot;
    }

    SplayNode<T> splay(SplayNode<T> node, T value){
        if (node == null || node.value == value){
            return node;
        }
        if (node.value.compareTo(value) > 0){
            if (node.leftChild == null){
                return node;
            }else if(node.leftChild.value.compareTo(value) > 0){
                if(node.leftChild.leftChild!=null) {
                    node.leftChild.leftChild = splay(node.leftChild.leftChild,value);
                    node = rightRotate(node);
                }
            }else if(node.leftChild.value.compareTo(value) < 0){
                if (node.leftChild.rightChild!=null) {
                    node.leftChild.rightChild = splay(node.leftChild.rightChild, value);
                    if (node.leftChild.rightChild!=null) {
                        node.leftChild = leftRotate(node.leftChild);
                    }
                }
            }
            if (node.leftChild!=null){
                return rightRotate(node);
            }else{
                return node;
            }
        }else {
            if (node.rightChild == null){
                return node;
            }else if (node.rightChild.value.compareTo(value) > 0){
                   if(node.rightChild.leftChild!=null){
                        node.rightChild.leftChild = splay(node.rightChild.leftChild,value);
                        if (node.rightChild.leftChild!=null){
                            node.rightChild = rightRotate(node.rightChild);
                        }
                    }
            }else if (node.rightChild.value.compareTo(value) < 0){
                if(node.rightChild.rightChild != null){
                    node.rightChild.rightChild = splay(node.rightChild.rightChild,value);
                    node = leftRotate(node);
                }
            }
            if (node.rightChild !=null){
                return leftRotate(node);
            }else{
                return node;
            }
        }
    }

    void insert(T value){
        if (root==null){
            root = new SplayNode(value);
        }else{
            root = splay(root,value);
            int compare = root.value.compareTo(value);
            if (compare > 0){
                SplayNode<T> newNode = new SplayNode<>(value);
                newNode.leftChild = root.leftChild;
                newNode.rightChild= root;
                root.leftChild = null;
                root = newNode;
            }else if (compare<0){
                SplayNode<T> newNode = new SplayNode<>(value);
                newNode.rightChild = root.rightChild;
                newNode.leftChild = root;
                root.rightChild = null;
                root = newNode;
            }else{
                root.value = value;
            }
        }
    }
    void remove(T value){
        if(root != null){
            root = splay(root,value);
            if (value.compareTo(root.value) == 0){
                if (root.leftChild == null){
                    root = root.rightChild;
                }else{
                    SplayNode<T> pivot = root.rightChild;
                    root = splay(root.leftChild,value);
                    root.rightChild = pivot;
                }
            }else{
                System.out.println("Item Not Found");
            }
        }else{
            System.out.println("Empty List");
        }
    }
    boolean search(T value){
        if(root.value != value){
            root = splay(root,value);
            if (root.value == value) return true;
            else return false;
        }else return true;

    }
}

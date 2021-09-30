package datastructureswithjava.trees;

import java.util.LinkedList;
import java.util.Queue;

class BSTNode<T> {
    T value;
    BSTNode<T> leftChild = null,rightChild=null;
    BSTNode(T value){
        this.value = value;
    }
}

public class BinarySearchTree <T extends Comparable<T>>{

    private BSTNode<T> root = null;

    void preOrder(){
        preOrder(root);
        System.out.println();
    }
    void inOrder(){
        inOrder(root);
        System.out.println();
    }
    void postOrder(){
        postOrder(root);
        System.out.println();
    }
    private void preOrder(BSTNode<T> node){
        if (node!=null) {
            System.out.print(node.value + " ");
            preOrder(node.leftChild);
            preOrder(node.rightChild);
        }
    }
    private void inOrder(BSTNode<T> node){
        if (node!=null) {
            inOrder(node.leftChild);
            System.out.print(node.value + " ");
            inOrder(node.rightChild);
        }
    }
    private void postOrder(BSTNode<T> node){
        if (node!=null) {
            postOrder(node.leftChild);
            postOrder(node.rightChild);
            System.out.print(node.value + " ");
        }
    }
    void levelOrder(){
        if (root!=null){
            Queue<BSTNode<T>> queue = new LinkedList<BSTNode<T>>();
            queue.add(root);
            while (queue.size()!=0){
                BSTNode<T> current = queue.peek();
                queue.remove();
                System.out.print(current.value + " ");
                if (current.leftChild != null) queue.add(current.leftChild);
                if (current.rightChild != null) queue.add(current.rightChild);
            }
            System.out.println();
        }
    }
    void insert(T value){
        root = insert(root,value);
    }
    private BSTNode<T> insert(BSTNode<T> node,T value){
        if (node==null)
            return new BSTNode<>(value);
        if (node.value == value)
            return node;
        else if(node.value.compareTo(value)>0)
            node.leftChild = insert(node.leftChild,value);
        else
            node.rightChild = insert(node.rightChild,value);
        return node;
    }
    int height(){
        return height(root);
    }
    private int height(BSTNode<T> node){
        if(node == null){
            return 0;
        }
        else {
            return 1 + Math.max(height(node.leftChild),height(node.rightChild));
        }
    }

    T min(){
        return min(root);
    }
    private T min(BSTNode<T> node){
        if(node.leftChild!=null) return min(node.leftChild);
        return node.value;
    }
    T max(){
        return max(root);
    }
    private T max(BSTNode<T> node){
        if(node.rightChild!=null) return min(node.rightChild);
        return node.value;
    }
    boolean search(T value){
        return search(root,value);
    }
    private boolean search(BSTNode<T> node,T value){
        if (node!=null){
            if(node.value == value)
                return true;
            return search(node.leftChild,value) || search(node.rightChild,value);
        }
        return false;
    }
    void delete(T value){
        if (search(value))
            delete(root,value);
        else
            System.out.println("Item Not Found");
    }
    private BSTNode<T> delete(BSTNode<T> node,T value){
        if(node!=null){
            if(node.value.compareTo(value)==0){
                if(node.rightChild == null && node.leftChild == null){
                    return null;
                }
                else if(node.leftChild != null && node.rightChild == null){
                    return node.leftChild;
                }
                else if(node.rightChild != null && node.leftChild == null){
                    return node.rightChild;
                }else{
                    T minValue = min(node.rightChild);
                    node.value = minValue;
                    node.rightChild = delete(node.rightChild,minValue);
                }
            }
            else if(node.value.compareTo(value) > 0)
                node.leftChild = delete(node.leftChild,value);
            else
                node.rightChild = delete(node.rightChild,value);
        }
        return node;
    }
}
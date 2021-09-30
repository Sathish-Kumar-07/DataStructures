package datastructureswithjava.trees;

import java.util.*;

class BTNode<T> {
    T value;
    BTNode<T> leftChild = null,rightChild=null;
    BTNode(T value){
        this.value = value;
    }
}

public class BinaryTree<T extends Number & Comparable<? super T>> {
    private BTNode<T> root = null;

    int height(){
        return height(root);
    }
    
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
    
    boolean search(T key){
        return search(key,root);
    }

    private int height(BTNode<T> node){
        if(node == null){
            return 0;
        }
        else {
            return 1 + Math.max(height(node.leftChild),height(node.rightChild));
        }
    }
    
    private boolean search(T key,BTNode<T> node){
        if (node == null) return false;
        if (key == node.value) return true;
        return search(key,node.leftChild) || search(key, node.rightChild);
    }
    
    private void preOrder(BTNode<T> node){
        if (node!=null) {
            System.out.print(node.value + " ");
            preOrder(node.leftChild);
            preOrder(node.rightChild);
        }
    }
    
    private void inOrder(BTNode<T> node){
        if (node!=null) {
            inOrder(node.leftChild);
            System.out.print(node.value + " ");
            inOrder(node.rightChild);
        }
    }
    
    private void postOrder(BTNode<T> node){
        if (node!=null) {
            postOrder(node.leftChild);
            postOrder(node.rightChild);
            System.out.print(node.value + " ");
        }
    }
    
    void levelOrder(){
        if (root!=null){
            Queue<BTNode<T>> queue = new LinkedList<BTNode<T>>();
            queue.add(root);
            while (queue.size()!=0){
                BTNode<T> current = queue.peek();
                queue.remove();
                System.out.print(current.value + " ");
                if (current.leftChild != null) queue.add(current.leftChild);
                if (current.rightChild != null) queue.add(current.rightChild);
            }
            System.out.println();
        }
    }
    
    void insert(T value){
        if (root == null){
            root = new BTNode<>(value);
        }else{
            Queue<BTNode<T>> queue = new LinkedList<BTNode<T>>();
            queue.add(root);
            while (queue.size() != 0){
                BTNode<T> current = queue.peek();
                queue.remove();
                if (current.leftChild == null){
                    current.leftChild = new BTNode<>(value);
                    break;
                }else{
                    queue.add(current.leftChild);
                }
                if (current.rightChild == null){
                    current.rightChild = new BTNode<>(value);
                    break;
                }else{
                    queue.add(current.rightChild);
                }
            }
        }
    }
    
    T min(){
        return min(root);
    }
    
    private T min(BTNode<T> node){
        if (node!=null){
            Queue<BTNode<T>> queue = new LinkedList<>();
            ArrayList<T> list = new ArrayList<>();
            queue.add(node);
            while (queue.size() != 0){
                BTNode<T> current = queue.peek();
                list.add(queue.remove().value);
                if (current.leftChild != null) queue.add(current.leftChild);
                if (current.rightChild != null) queue.add(current.rightChild);
            }
            return Collections.min(list);
        }
        return null;
    }
    
    int allNodeCount(){
        return allNodeCount(root);
    }
    
    private int allNodeCount(BTNode<T> node){
        if (node != null){
            return 1 + allNodeCount(node.leftChild) + allNodeCount(node.rightChild);
        }else return 0;
    }
    
    boolean isCompleteBinaryTree(){
        return isCompleteBinaryTree(root,0,allNodeCount());
    }
    
    boolean isCompleteBinaryTree(BTNode<T> node,int index,int allNodeCount){
        if (node == null){
            return true;
        }
        if (index>allNodeCount){
            return false;
        }
        return isCompleteBinaryTree(node.leftChild,2*index+1,allNodeCount)
                &&
                isCompleteBinaryTree(node.rightChild,2*index+2,allNodeCount);
    }
    
    boolean isPerfectBinaryTree(){
        return Math.pow(height(),2)-1 == allNodeCount();
    }
    
    boolean isFullBinaryTree(){
        return isFullBinaryTree(root);
    }
    
    boolean isFullBinaryTree(BTNode<T> node){
        if(node == null){
            return false;
        }
        if (node.leftChild==null && node.rightChild==null) return true;
        return node.leftChild!=null && node.rightChild!=null
                && isFullBinaryTree(node.leftChild)
                && isFullBinaryTree(node.rightChild);
    }
}

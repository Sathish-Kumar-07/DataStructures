package datastructureswithjava.trees;

import java.util.LinkedList;
import java.util.Queue;

class AVLNode<T> {
    T value;
    AVLNode<T> leftChild = null,rightChild=null;
    AVLNode(T value){
        this.value = value;
    }
    int height = 0;
    int leftHeight(){
        if(leftChild!=null)
            return leftChild.height;
        else return -1;
    }
    int rightHeight(){
        if (rightChild!=null)
            return rightChild.height;
        else
            return -1;
    }
    int balanceFactor(){
        return leftHeight() - rightHeight();
    }
}

public class AVLTree <T extends Comparable<T>>{

    private AVLNode<T> root = null;

    void levelOrder(){
        if (root!=null){
            Queue<AVLNode<T>> queue = new LinkedList<AVLNode<T>>();
            queue.add(root);
            while (queue.size()!=0){
                AVLNode<T> current = queue.peek();
                queue.remove();
                System.out.print(current.value + " ");
                if (current.leftChild != null) queue.add(current.leftChild);
                if (current.rightChild != null) queue.add(current.rightChild);
            }
            System.out.println();
        }
    }

    AVLNode<T> leftRotate(AVLNode<T> node){
        AVLNode<T> pivot = node.rightChild;
        node.rightChild = pivot.leftChild;
        pivot.leftChild = node;
        node.height = Math.max(node.leftHeight(),node.rightHeight())+1;
        pivot.height = Math.max(pivot.leftHeight(),pivot.rightHeight())+1;
        return pivot;
    }

    AVLNode<T> rightRotate(AVLNode<T> node){
        AVLNode<T> pivot = node.leftChild;
        node.leftChild = pivot.rightChild;
        pivot.rightChild = node;
        node.height = Math.max(node.leftHeight(),node.rightHeight())+1;
        pivot.height = Math.max(pivot.leftHeight(),pivot.rightHeight())+1;
        return pivot;
    }

    AVLNode<T> rightLeftRotate(AVLNode<T> node){
        AVLNode<T> rightChild;
        if (node.rightChild!=null)
            rightChild = node.rightChild;
        else
            return node;
        node.rightChild = rightRotate(rightChild);
        return leftRotate(node);
    }

    AVLNode<T> leftRightRotate(AVLNode<T> node){
        AVLNode<T> leftChild;
        if (node.leftChild!=null)
            leftChild = node.rightChild;
        else
            return node;
        node.leftChild = leftRotate(leftChild);
        return rightRotate(node);
    }
    AVLNode<T> balanced(AVLNode<T> node){
        switch (node.balanceFactor()){
            case 2 : {
                if (node.leftChild!=null){
                    if (node.leftChild.balanceFactor() ==-1) {
                        return leftRightRotate(node);
                    }else{
                        return rightRotate(node);
                    }
                }
            }
            case -2:{
                if (node.rightChild!=null){
                    if (node.rightChild.balanceFactor() == 1) {
                        return rightLeftRotate(node);
                    }else{
                        return leftRotate(node);
                    }
                }
            }
            default: return node;
        }

    }
    void insert(T value){
        root = insert(root,value);
    }
    private AVLNode<T> insert(AVLNode<T> node,T value){
        if (node==null)
            return new AVLNode<>(value);
        if(node.value == value)
            return node;
        else if(node.value.compareTo(value)>0)
            node.leftChild = insert(node.leftChild,value);
        else
            node.rightChild = insert(node.rightChild,value);
        AVLNode<T> balancedNode = balanced(node);
        balancedNode.height = Math.max(balancedNode.leftHeight(),balancedNode.rightHeight())+1;
        return balancedNode;
    }
    int height(){
        return height(root);
    }
    private int height(AVLNode<T> node){
        if(node == null){
            return 0;
        }
        else {
            return 1 + Math.max(height(node.leftChild),height(node.rightChild));
        }
    }

    boolean search(T value){
        return search(root,value);
    }
    private boolean search(AVLNode<T> node,T value){
        if (node!=null){
            if(node.value == value)
                return true;
            return search(node.leftChild,value) || search(node.rightChild,value);
        }
        return false;
    }

    T min(){
        return min(root);
    }
    private T min(AVLNode<T> node){
        if(node.leftChild!=null) return min(node.leftChild);
        return node.value;
    }
    T max(){
        return max(root);
    }
    private T max(AVLNode<T> node){
        if(node.rightChild!=null) return min(node.rightChild);
        return node.value;
    }

    void delete(T value){
        if (search(value))
            delete(root,value);
        else
            System.out.println("Item Not Found");
    }

    private AVLNode<T> delete(AVLNode<T> node,T value){
        if(node==null){
            return null;
        }
        else{
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
        AVLNode<T> balancedNode = balanced(node);
        balancedNode.height = Math.max(balancedNode.leftHeight(),balancedNode.rightHeight())+1;
        return balancedNode;
    }
}

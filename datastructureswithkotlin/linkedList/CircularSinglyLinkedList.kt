package datastructureswithkotlin.linkedList

data class CNode<T>(var value:T,var nextNode: CNode<T>?=null)

class CircularSinglyLinkedList <T>{
    private var root:CNode<T>? = null

    override fun toString(): String {
        return root.toString()
    }

    fun addFirst(value:T){
        val newNode:CNode<T> = CNode(value)
        root?.let {
            newNode.nextNode = root
            var current = root
            while (current!!.nextNode!=root){
                current = current.nextNode
            }
            current.nextNode = newNode
            root = newNode

        }?: run {
            root = newNode
            root!!.nextNode = root
        }
    }
    fun addLast(value:T){
        val newNode:CNode<T> = CNode(value)
        root?.let {
            var current:CNode<T>? = root
            while (current!!.nextNode != root){
                current = current.nextNode
            }
            newNode.nextNode = root
            current.nextNode = newNode
        }?:run {
            root = newNode
            root!!.nextNode = root
        }
    }
    fun size():Int{
        return root?.let {
            var size:Int = 0
            var current:CNode<T>? = root
            while (current!!.nextNode != root){
                current = current.nextNode
                size += 1
            }
            ++size
        }?:0
    }
    fun addAt(value:T,index:Int){
        if (size() < index) println("Index Out Of Range")
        else if (size() == index) addLast(value)
        else if (index == 0) addFirst(value)
        else {
            var current:CNode<T>? = root
            var previous:CNode<T>? = root
            var currentIndex = 0
            while(current!!.nextNode != root){
                previous = current
                current = current.nextNode
                currentIndex++
                if (currentIndex == index){
                    val newNode:CNode<T> = CNode(value)
                    previous.nextNode = newNode
                    newNode.nextNode = current
                    break
                }
            }
        }
    }
    fun removeFirst(){
        root?.let {
            println("Item Removed ${it.value}")
            if (it.nextNode == null){
                root = null
            }else{
                var current:CNode<T>?= root
                while (current!!.nextNode != root){
                    current = current.nextNode
                }
                root = it.nextNode
                current.nextNode = root
            }
        }?:run {
            println("Empty List")
        }
    }
    fun removeLast(){
        root?.let {
            if (root!!.nextNode == null){
                println("Item Removed ${root!!.value}")
                root = null
            }else{
                var current : CNode<T>? = root
                var previous : CNode<T>? = root
                while (current!!.nextNode != root){
                    previous = current
                    current = current.nextNode
                }
                println("Item Removed ${current.value}")
                previous!!.nextNode = root
            }
        }?:run{
            println("Empty List")
        }
    }
    fun removeAt(index:Int){
        root?.let {
            if (0 == index) removeFirst()
            else if (index == size()-1) removeLast()
            else if (index > size()) println("Index Out Of Range")
            else {
                var current:CNode<T>? = root
                var previous:CNode<T>? = root
                var currentIndex:Int = 0
                while (current!!.nextNode!=root){
                    previous = current
                    current = current.nextNode
                    currentIndex++
                    if (currentIndex == index){
                        previous.nextNode = current!!.nextNode
                        println("Item Removed ${current.value}")
                        break
                    }
                }
            }
        }?:run{
            println("Empty List")
        }
    }
    fun display(){
        root?.let {
            var current = root
            while (current!!.nextNode != root){
                print("${current.value}-->")
                current = current.nextNode
            }
            println("${current.value}")
        }
    }
}
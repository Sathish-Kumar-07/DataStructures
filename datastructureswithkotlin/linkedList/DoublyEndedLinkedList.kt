package datastructureswithkotlin.linkedList

data class DNode<T>(var value:T,var nextNode: DNode<T>? = null,var previousNode:DNode<T>? = null){
    override fun toString(): String {
        return if (nextNode==null) "$value"
        else "$value -> ${nextNode.toString()}"
    }
}

class DoublyEndedLinkedList<T> {
    private var root:DNode<T>? = null
    override fun toString(): String {
        return root.toString()
    }
    fun displayreverse(){
        root?.let {
            var current:DNode<T>? = it
            while (current!!.nextNode != null){
                current = current.nextNode
            }
            while (current!!.previousNode != null){
                print("${current.value} -> ")
                current =current.previousNode
            }
            println("${current.value}")
        }?: println("Empty List")
    }
    fun size(): Int {
        return root?.let {
            var current:DNode<T>? = it
            var size:Int = 0
            while (current!!.nextNode!=null){
                current = current.nextNode
                size++
            }
            ++size
        } ?: 0
    }
    fun addFirst(value:T){
        val newNode:DNode<T> = DNode(value)
        root?.let {
            newNode.nextNode = root
            root!!.previousNode = newNode
            root = newNode
        }?:run{
            root = newNode
        }
    }
    fun addLast(value:T){
        val newNode:DNode<T> = DNode(value)
        root?.let {
            var current:DNode<T>? = it
            while (current!!.nextNode != null){
                current = current.nextNode
            }
            newNode.previousNode = current
            current.nextNode = newNode
        }?: run {
            root = newNode
        }
    }
    fun addAt(value:T,index:Int){
        if (index > size()) println("Index Out Of Range")
        else if (index == size()) addLast(value)
        else if (index == 0) addFirst(value)
        else{
            val newNode:DNode<T> = DNode(value)
            root?.let {
                var current:DNode<T>? = it
                var previous:DNode<T>? = it
                var currentIndex:Int = 0
                while (current!!.nextNode!=null){
                    previous = current
                    current = current.nextNode
                    currentIndex++
                    if (currentIndex==index){
                        newNode.nextNode = current
                        newNode.previousNode = previous
                        current!!.previousNode = newNode
                        previous.nextNode = newNode
                        break
                    }
                }
            }
        }
    }
    fun removeFirst(){
        root?.let {
            println("Item Removed ${root!!.value}")
            if (root!!.nextNode == null ){
                root = null
            }else{
                root = root!!.nextNode
                root!!.previousNode = null
            }
        }?: println("List Is Empty")
    }
    fun removeLast(){
        root?.let {
            if (root!!.nextNode == null ){
                println("Item Removed ${root!!.value}")
                root = null
            }else{
                var current:DNode<T>? = it
                var previous:DNode<T>? = it
                while (current!!.nextNode != null){
                    previous = current
                    current = current.nextNode
                }
                previous!!.nextNode =null
                println("Item Removed ${current.value}")
            }
        }?: println("List Is Empty")
    }
    fun removeAt(index:Int){
        if (index > size()) println("Index Out Of Range")
        else if (index == size()-1) removeLast()
        else if (index == 0) removeFirst()
        else{
            root?.let {
                var current:DNode<T>? = it
                var previous:DNode<T>? = it
                var currentIndex:Int = 0
                while (current!!.nextNode!=null){
                    previous = current
                    current = current.nextNode
                    currentIndex++
                    if (currentIndex==index){
                        println("Item Removed ${current!!.value}")
                        previous.nextNode = current.nextNode
                        current.nextNode!!.previousNode = previous
                        break
                    }
                }
            }
        }
    }
}
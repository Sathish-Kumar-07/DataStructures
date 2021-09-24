package datastructureswithkotlin.linkedList

class SinglyLinedList<T>{

    private var root:Node<T>? = null

    override fun toString(): String {
        return root.toString()
    }

    fun addFirst(value:T){
        val newNode:Node<T> = Node(value)
        root?.let {
            newNode.nextNode = root
            root = newNode
        }?: run {
            root = newNode
        }
    }
    fun addLast(value:T){
        val newNode:Node<T> = Node(value)
        root?.let {
            var current:Node<T>? = root
            while (current!!.nextNode != null){
                current = current.nextNode
            }
            current.nextNode = newNode
        }?:run {
            root = newNode
        }
    }
    fun size():Int{
        return root?.let {
            var size:Int = 0
            var current:Node<T>? = root
            while (current != null){
                current = current.nextNode
                size += 1
            }
            size
        }?:0
    }
    fun addAt(value:T,index:Int){
        if (size() < index) println("Index Out Of Range")
        else if (size() == index) addLast(value)
        else if (index == 0) addFirst(value)
        else {
            var current:Node<T>? = root
            var previous:Node<T>? = root
            var currentIndex = 0
            while(current!!.nextNode != null){
                previous = current
                current = current.nextNode
                currentIndex++
                if (currentIndex == index){
                    val newNode:Node<T> = Node(value)
                    previous.nextNode = newNode
                    newNode.nextNode = current
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
                root = it.nextNode
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
                var current : Node<T>? = root
                var previous : Node<T>? = root
                while (current!!.nextNode != null){
                    previous = current
                    current = current.nextNode
                }
                println("Item Removed ${current.value}")
                previous!!.nextNode = null
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
                var current:Node<T>? = root
                var previous:Node<T>? = root
                var currentIndex:Int = 0
                while (current!!.nextNode!=null){
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
    fun search(value:Int):Boolean{
        var current :Node<T>? = root
        while (current!=null){
            if (current.value == value) return true
            current = current.nextNode
        }
        return false
    }
}

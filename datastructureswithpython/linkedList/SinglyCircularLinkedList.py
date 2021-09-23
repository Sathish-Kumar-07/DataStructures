# Create A Class Containing Two Parameters
# 1. To Store The Value Of The Node
# 2. To Store The Next Node
class Node:
    def __init__(self, value, nextNode=None):
        self.value = value
        self.nextNode = nextNode


# Create A Singly Circular Linked List Class With Some Set Of Methods As Given Below To Add Remove Display
class SinglyCircularLinkedList:
    def __init__(self):
        self.root = None  # Create A Root Node With Initial Value To None

    def addFirst(self, value):
        newNode = Node(value)  # Create A New Node
        if self.root is None:  # If Root Node Is None Then Assign New Node To Root Node
            self.root = newNode
            self.root.nextNode = self.root  # Assign Root Node's Next Node To Root Node
        else:
            """
            If Root Node Is Not None Then Find Last Node And Connect Last Node's Next Node To New Node 
            And New Node's Next Node To Root Node, At Last Consider New Node As Root Node 
            """
            lastNode = self.root
            while lastNode.nextNode is not self.root:
                lastNode = lastNode.nextNode
            lastNode.nextNode = newNode
            newNode.nextNode = self.root
            self.root = newNode

    def addLast(self, value):
        newNode = Node(value)
        if self.root is None:
            self.root = newNode
            self.root.nextNode = self.root
        else:
            lastNode = self.root
            while lastNode.nextNode is not self.root:
                lastNode = lastNode.nextNode
            lastNode.nextNode = newNode
            newNode.nextNode = self.root

    def displayList(self):
        if self.root is not None:
            current = self.root  # Take A Temporary Variable And Assign Root Node
            while current.nextNode is not self.root:  # Iterate Until The Node Is None
                print(current.value, end=" -> ")  # Print The Value Of The Node
                current = current.nextNode  # Assign Temp Node To Its Next Node Each Time Condition Is True
            print(current.value)
        else:
            print("List Is Empty")  # If Root Is Empty, Print It As Empty List

    def removeFirst(self):
        if self.root is None:
            print("Empty Linked List")
        elif self.root.nextNode is self.root:
            print("Item Removed", self.root.value)
            self.root = None
        else:
            print("Item Removed", self.root.value)
            current = self.root
            while current.nextNode is not self.root:
                current = current.nextNode
            current.nextNode = self.root.nextNode
            self.root = self.root.nextNode

    def removeLast(self):
        if self.root is None:
            print("Empty Linked List")
        elif self.root.nextNode is self.root:
            print("Item Removed", self.root.value)
            self.root = None
        else:
            current = self.root
            previous = self.root
            while current.nextNode is not self.root:
                previous = current
                current = current.nextNode
            previous.nextNode = self.root
            print("Item Removed", current.value)

    """
    Iterate Through Each Node And Increment Count By One To Find Size 
    """
    def size(self):
        size = 0
        current = self.root
        if current is not None:
            while current.nextNode is not self.root:
                current = current.nextNode
                size += 1
            size += 1
        return size

    def insertAt(self, value, index):
        if index == 0:
            self.addFirst(value)
        elif index == self.size():
            self.addLast(value)
        elif self.size() < index:
            print("Invalid Index")
        else:
            current = self.root
            previous = self.root
            currentIndex = 0
            while current.nextNode is not self.root:
                previous = current
                current = current.nextNode
                currentIndex += 1
                if currentIndex == index:
                    newNode = Node(value)
                    previous.nextNode = newNode
                    newNode.nextNode = current
                    break

    def removeAt(self, index):
        if index == 0:
            self.removeFirst()
        elif index == self.size() - 1:
            self.removeLast()
        elif self.size() < index:
            print("Invalid Index")
        else:
            current = self.root
            previous = self.root
            currentIndex = 0
            while current.nextNode is not self.root:
                previous = current
                current = current.nextNode
                currentIndex += 1
                if currentIndex == index:
                    print("Item Removed",current.value)
                    previous.nextNode = current.nextNode
                    break



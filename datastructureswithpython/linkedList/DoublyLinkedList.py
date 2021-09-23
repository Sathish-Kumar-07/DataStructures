class Node:
    def __init__(self, value):
        self.value = value
        self.nextNode = None
        self.previousNode = None


class DoublyLinkedList:
    def __init__(self):
        self.root = None

    def display(self):
        if self.root is None:
            print("List Is Empty")
        else:
            current = self.root
            while current is not None:
                print(current.value, end="->")
                current = current.nextNode
            print()

    def displayReverse(self):
        if self.root is None:
            print("List Is Empty")
        else:
            current = self.root
            while current.nextNode is not None:
                current = current.nextNode
            while current is not None:
                print(current.value, end="->")
                current = current.previousNode
            print()

    def size(self):
        size = 0
        current = self.root
        while current is not None:
            size += 1
            current = current.nextNode
        return size

    def addFirst(self, value):
        newNode = Node(value)
        if self.root is None:
            self.root = newNode
        else:
            newNode.nextNode = self.root
            self.root.previousNode = newNode
            self.root = newNode

    def addLast(self, value):
        newNode = Node(value)
        if self.root is None:
            self.root = newNode
        else:
            current = self.root
            while current.nextNode is not None:
                current = current.nextNode
            current.nextNode = newNode
            newNode.previousNode = current

    def insertAt(self, value, index):
        if self.size() < index:
            print("Invalid Index")
        elif self.size() == index:
            self.addLast(value)
        elif index == 0:
            self.addFirst(value)
        else:
            current = self.root
            previous = self.root
            currentIndex = 0
            while current.nextNode is not None:
                previous = current
                current = current.nextNode
                currentIndex += 1
                if currentIndex == index:
                    newNode = Node(value)
                    previous.nextNode = newNode
                    newNode.previousNode = previous
                    newNode.nextNode = current
                    current.previousNode = newNode
                    break

    def removeFirst(self):
        if self.root is None:
            print("List Is Empty")
        else:
            print("Item Removed", self.root.value)
            self.root = self.root.nextNode
            self.root.previousNode = None

    def removeLast(self):
        if self.root is None:
            print("List Is Empty")
        else:
            if self.root.nextNode is None:
                print("Removed Item", self.root.value)
                self.root = None
            else:
                current = self.root
                previous = self.root
                while current.nextNode is not None:
                    previous = current
                    current = current.nextNode
                previous.nextNode = None
                print("Item Removed", current.value)

    def removeAt(self, index):
        if self.root is None:
            print("List Is Empty")
        elif self.size() <= index:
            print("Invalid Index")
        elif self.size() - 1 == index:
            self.removeLast()
        elif 0 == index:
            self.removeFirst()
        else:
            current = self.root
            previous = self.root
            currentIndex = 0
            while current is not None:
                previous = current
                current = current.nextNode
                currentIndex += 1
                if currentIndex == index:
                    previous.nextNode = current.nextNode
                    current.nextNode.previousNode = previous
                    print("Item Removed",current.value)
                    break

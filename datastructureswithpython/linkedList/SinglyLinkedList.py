# Create A Class Containing Two Parameters
# 1. To Store The Value Of The Node
# 2. To Store The Next Node
class Node:
    def __init__(self, value, nextNode=None):
        self.value = value
        self.nextNode = nextNode


# Create A Singly Linked List Class With Some Set Of Methods As Given Below To Add Remove Display
class SinglyLinkedList:
    def __init__(self):
        self.root = None  # Create A Root Node With Initial Value To None

    def addLast(self, value):
        if self.root is None:  # Check Whether Node Is Empty Or Not,
            self.root = Node(value)  # If Empty, Then Create New Node Object And Assign It To Root
        else:
            temp = self.root  # Create A Temporary Node And Assign Root Node To It
            while temp.nextNode is not None:  # Run The Loop Until The Temp Node Contains Next Node As None
                temp = temp.nextNode  # Assign Temp Node To Its Next Node Each Time Condition Is True
            temp.nextNode = Node(value)  # The Last Node Is Found, Just Create Assign New Node Object To The Last Node

    def addFirst(self, value):
        new = Node(value)  # Create New Node Object
        new.nextNode = self.root  # Connect New Node To The Root Node
        self.root = new  # Then Assign Root Node As New Node

    def displayList(self):
        if self.root is not None:
            temp = self.root  # Take A Temporary Variable And Assign Root Node
            while temp is not None:  # Iterate Until The Node Is None
                print(temp.value,end=" -> ")  # Print The Value Of The Node
                temp = temp.nextNode  # Assign Temp Node To Its Next Node Each Time Condition Is True
                print()
        else:
            print("List Is Empty")  # If Root Is Empty, Print It As Empty List

    def removeFirst(self):
        if self.root is not None:  # Check Whether The Root Node Is Empty Or Not
            print("Item Removed", self.root.value)  # If Not Empty Then Print The Root Node Value
            self.root = self.root.nextNode  # Now Assign Root Node With Root Node's Next Node
        else:
            print("List Is Empty")  # If Root Node Is None Then That Means List Is Empty

    def removeLast(self):
        if self.root is not None:  # Check Whether The Root Node Is Empty Or Not
            if self.root.nextNode is None:
                print("Item Removed", self.root.value)
                self.root = None
                return
            current = self.root  # Create A Variable To Point Current Node Initial Value To Root Node
            previous = self.root  # Create A Variable To Previous Current Node Initial Value To Root Node
            while current.nextNode is not None:
                previous = current  # Assign Current Node To Previous Node
                current = current.nextNode  # Assign Current Next Node To Current Node
            print("Item Removed", current.value)  # Print The Current Node Value Which Stores The Last Node
            previous.nextNode = None
        else:
            print("List Is Empty")  # If Root Node Is None Then That Means List Is Empty

    def size(self):
        size = 0  # Create A Variable Size And Initialize A Value 0
        temp = self.root  # Create A Temporary Variable And Initialize Root Node
        while temp is not None:  # Iterate Until The Node Is None
            size += 1  # Add + 1 To The Size When It Enter The Loop
            temp = temp.nextNode  # Assign Temp Next Node To Temp
        return size  # Return The Size Which Is Total Number Of Nodes In The Linked List

    def insertAt(self, value, index):
        if index == self.size():  # If Index Is Equal to The Size Of The List Then Use addLast Method To Add
            self.addLast(value)
        elif index == 0:  # If Index Is Equal to The 0 Of The List Then Use addFirst Method To Add
            self.addFirst(value)
        elif index > self.size():  # If Index Is Greater Than Size Display Invalid List
            print("Invalid Index")
        else:
            current = self.root  # Create A Variable To Point Current Node Initial Value To Root Node
            previous = self.root  # Create A Variable To Previous Current Node Initial Value To Root Node
            currentIndex = 0  # Create A Variable To Point Current Index
            while current.nextNode is not None:
                currentIndex += 1  # Increment By 1 Only When It Enters While Loop
                previous = current  # Assign Current Node To Previous Node
                current = current.nextNode  # Assign Current Next Node To Current Node
                if currentIndex == index:   # If The CurrentIndex Matches With Given Index
                    newNode = Node(value)  # Create New Node
                    previous.nextNode = newNode  # Assign New Node To The Previous Node's Next Node
                    newNode.nextNode = current  # Assign Current Node To The New Node's Next Node
                    break  # Break Down The Loop

    def removeAt(self, index):
        if index == 0:  # If Index Is Equal to The Size Of The List Then Use removeFirst Method To Remove
            self.removeFirst()
        elif index == self.size() - 1:  # If Index Is Equal to The Size Of The List Then Use removeLast Method To Remove
            self.removeLast()
        elif index >= self.size():  # If Index Is Greater Than Size Display Invalid List
            print("Invalid Index")
        else:
            current = self.root  # Create A Variable To Point Current Node Initial Value To Root Node
            previous = self.root  # Create A Variable To Previous Current Node Initial Value To Root Node
            currentIndex = 0  # Create A Variable To Point Current Index
            while current.nextNode is not None:
                currentIndex += 1  # Increment By 1 Only When It Enters While Loop
                previous = current  # Assign Current Node To Previous Node
                current = current.nextNode  # Assign Current Next Node To Current Node
                if currentIndex == index:   # If The CurrentIndex Matches With Given Index
                    print("Item Removed", current.value)  # Display The Current Item
                    previous.nextNode = current.nextNode  # Remove Current Node By Assigning Current Next B+NOde To Previous Next Node
                    break  # Break Down The Loop

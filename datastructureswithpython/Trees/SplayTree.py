class SplayNode:
    def __init__(self, value):
        self.value = value
        self.leftChild = None
        self.rightChild = None


class SplayTree:
    def __init__(self):
        self.root = None

    def levelOrder(self):
        if self.root:
            Queue = [self.root]
            while len(Queue) > 0:
                current = Queue.pop(0)
                print(current.value, end=" ")
                if current.leftChild:
                    Queue.append(current.leftChild)
                if current.rightChild:
                    Queue.append(current.rightChild)
        else:
            print("Empty Tree")
        print()

    def rightRotate(self, node):
        pivot = node.leftChild
        node.leftChild = pivot.rightChild
        pivot.rightChild = node
        return pivot

    def leftRotate(self, node):
        pivot = node.rightChild
        node.rightChild = pivot.leftChild
        pivot.leftChild = node
        return pivot

    def splay(self, node, value):
        if node is None or node.value == value:
            return node
        if node.value > value:
            if node.leftChild is None:
                return node
            elif node.leftChild.value > value:
                if node.leftChild.leftChild is not None:
                    node.leftChild.leftChild = self.splay(node.leftChild.leftChild, value)
                    node = self.rightRotate(node)
            else:
                if node.leftChild.rightChild is not None:
                    node.leftChild.rightChild = self.splay(node.leftChild.rightChild, value)
                    node.leftChild = self.leftRotate(node.leftChild)
            if node.leftChild is not None:
                return self.rightRotate(node)
            else:
                return node
        else:
            if node.rightChild is None:
                return node
            if node.rightChild.value > value:
                if node.rightChild.leftChild is not None:
                    node.rightChild.leftChild = self.splay(node.rightChild.leftChild, value)
                    node.rightChild = self.rightRotate(node.rightChild)
            else:
                if node.rightChild.rightChild is not None:
                    node.rightChild.rightChild = self.splay(node.rightChild.rightChild, value)
                    node = self.leftRotate(node)
            if node.rightChild is not None:
                return self.leftRotate(node)
            else:
                return node

    def insert(self, value):
        if self.root is not None:
            self.root = self.splay(self.root, value)
            if self.root.value > value:
                newNode = SplayNode(value)
                newNode.leftChild = self.root.leftChild
                newNode.rightChild = self.root
                self.root.leftChild = None
                self.root = newNode
            elif self.root.value < value:
                newNode = SplayNode(value)
                newNode.rightChild = self.root.rightChild
                newNode.leftChild = self.root
                self.root.rightChild = None
                self.root = newNode
            else:
                self.root.value = value
        else:
            self.root = SplayNode(value)

    def remove(self,value):
        if self.root is not None:
            self.root = self.splay(self.root,value)
            if self.root.value is value:
                if self.root.leftChild is None:
                    self.root = self.root.rightChild
                else:
                    pivot = self.root.rightChild
                    self.root = self.splay(self.root.leftChild,value)
                    self.root.rightChild = pivot
            else:
                print("Item Doesn't Exist")
        else:
            print("Empty Tree")
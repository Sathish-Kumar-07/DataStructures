class BSTNode:
    def __init__(self, value):
        self.value = value
        self.rightChild = None
        self.leftChild = None


class BinarySearchTree:
    def __init__(self):
        self.root = None

    def insert(self, value):
        self.root = self.insertNode(self.root, value)

    def remove(self, value):
        self.root = self.removeNode(self.root, value)

    def minvalue(self):
        minNode = self.minValueNode(self.root)
        return minNode.value

    def inorder(self):
        self.inOrder(self.root)

    def postorder(self):
        self.postOrder(self.root)

    def preorder(self):
        self.preOrder(self.root)

    def height(self):
        return self.heightCount(self.root)

    def search(self,value):
        return self.searchNode(self.root,value)

    def insertNode(self, node, value):
        if node is None:
            return BSTNode(value)
        elif node.value > value:
            node.leftChild = self.insertNode(node.leftChild, value)
        elif node.value < value:
            node.rightChild = self.insertNode(node.rightChild, value)
        elif node.value == value:
            return node
        return node

    def minValueNode(self, node):
        while node.leftChild is not None:
            node = node.leftChild
        return node

    def removeNode(self, node, value):
        if node is None:
            return node
        if node.value == value:
            if node.leftChild is None:
                return node.rightChild
            elif node.rightChild is None:
                return node.leftChild
            else:
                minNode = self.minValueNode(node.rightChild)
                node.value = minNode.value
                node.rightChild = self.removeNode(node.rightChild, minNode.value)
        elif node.value > value:
            node.leftChild = self.removeNode(node.leftChild, value)
        elif node.value < value:
            node.rightChild = self.removeNode(node.rightChild, value)
        return node

    def inOrder(self, node):
        if node is not None:
            self.inOrder(node.leftChild)
            print(node.value)
            self.inOrder(node.rightChild)

    def postOrder(self, node):
        if node is not None:
            self.postOrder(node.leftChild)
            self.postOrder(node.rightChild)
            print(node.value)

    def preOrder(self, node):
        if node is not None:
            print(node.value)
            self.preOrder(node.leftChild)
            self.preOrder(node.rightChild)

    def heightCount(self, node):
        if node is not None:
            return 1 + max(self.heightCount(node.leftChild), self.heightCount(node.rightChild))
        else:
            return 0

    def searchNode(self,node,value):
        if node is not None:
            if node.value == value:
                return True
            elif node.value > value:
                return self.searchNode(node.leftChild,value)
            else:
                return self.searchNode(node.rightChild,value)
        return False


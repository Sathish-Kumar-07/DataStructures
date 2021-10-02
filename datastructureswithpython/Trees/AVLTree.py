class AVLNode:
    def __init__(self, value):
        self.value = value
        self.rightChild = None
        self.leftChild = None
        self.height = 0

    def getLeftChildHeight(self):
        if self.leftChild:
            return self.leftChild.height
        else:
            return -1

    def getRightChildHeight(self):
        if self.rightChild:
            return self.rightChild.height
        else:
            return -1

    def balanceFactor(self):
        return self.getLeftChildHeight() - self.getRightChildHeight()


class AVLTree:
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

    def leftRotate(self, node):
        pivot = node.rightChild
        node.rightChild = pivot.leftChild
        pivot.leftChild = node
        pivot.height = max(pivot.getLeftChildHeight(), pivot.getRightChildHeight()) + 1
        node.height = max(node.getLeftChildHeight(), node.getRightChildHeight()) + 1
        return pivot

    def rightRotate(self, node):
        pivot = node.leftChild
        node.leftChild = pivot.rightChild
        pivot.rightChild = node
        pivot.height = max(pivot.getLeftChildHeight(), pivot.getRightChildHeight()) + 1
        node.height = max(node.getLeftChildHeight(), node.getRightChildHeight()) + 1
        return pivot

    def rightLeftRotate(self, node):
        if node.rightChild:
            rightChild = self.rightRotate(node.rightChild)
        else:
            return node
        node.rightChild = rightChild
        return self.leftRotate(node)

    def leftRightRotate(self, node):
        if node.leftChild:
            leftChild = self.leftRotate(node.leftChild)
        else:
            return node
        node.leftChild = leftChild
        return self.rightRotate(node)

    def balance(self, node):
        if node.balanceFactor() == 2:
            if node.leftChild:
                if node.leftChild.balanceFactor() == -1:
                    return self.leftRightRotate(node)
                else:
                    return self.rightRotate(node)
        if node.balanceFactor() == -2:
            if node.rightChild:
                if node.rightChild.balanceFactor() == 1:
                    return self.rightLeftRotate(node)
                else:
                    return self.leftRotate(node)
        return node

    def insert(self, value):
        self.root = self.insertNode(self.root, value)

    def insertNode(self, node, value):
        if node is None:
            return AVLNode(value)
        else:
            if node.value == value:
                return node
            elif value > node.value:
                node.rightChild = self.insertNode(node.rightChild, value)
            else:
                node.leftChild = self.insertNode(node.leftChild, value)
            balanceNode = self.balance(node)
            balanceNode.height = max(balanceNode.getLeftChildHeight(), balanceNode.getRightChildHeight()) + 1
        return balanceNode

    def search(self, node, value):
        if node is None:
            return False
        elif node.value == value:
            return True
        elif node.value > value:
            return self.search(node.leftChild, value)
        else:
            return self.search(node.rightChild, value)

    def min(self, node):
        if node is not None:
            if node.leftChild is not None:
                return min(node.leftChild)
            else:
                return node.value
        else:
            return node

    def delete(self, value):
        if self.search(self.root, value):
            self.root = self.deleteNode(self.root, value)
        else:
            print("Item Doesn't Exist")

    def deleteNode(self, node, value):
        if node.value == value:
            return None
        else:
            if node.value > value:
                node.leftChild = self.deleteNode(node.leftChild, value)
            elif node.value < value:
                node.rightChild = self.deleteNode(node.rightChild, value)
            else:
                minValue = min(node.rightChild)
                node.value = minValue
                node.rightChild = self.deleteNode(node.rightChild, minValue)
            balanceNode = self.balance(node)
            balanceNode.height = max(balanceNode.getLeftChildHeight(), balanceNode.getRightChildHeight()) + 1
            return balanceNode

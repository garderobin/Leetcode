class Node(object):
    def __init__(self, key, value):
        self.key, self.value = key, value
        self.prev, self.nxt = None, None
        return


class DoubleLinkedList(object):
    def __init__(self):
        self.head_sentinel, self.tail_sentinel, self.count = Node(None, None), Node(None, None), 0
        self.head_sentinel.nxt, self.tail_sentinel.prev = self.tail_sentinel, self.head_sentinel
        self.count = 0

    def insert(self, x, node):
        if not node:
            raise KeyError
        temp = x.nxt
        x.nxt, node.prev = node, x
        node.nxt, temp.prev = temp, node
        self.count += 1

    def appendLeft(self, node):
        if not node:
            raise KeyError
        self.insert(self.head_sentinel, node)

    def append(self, node):
        if not node:
            raise KeyError
        self.insert(self.get_tail(), node)

    def remove(self, node):
        if not node:
            raise KeyError
        prev_node = node.prev
        prev_node.nxt, node.nxt.prev = node.nxt, prev_node
        self.count -= 1

    def pop(self):
        if self.size() < 1:
            raise KeyError
        self.remove(self.get_tail())

    def popleft(self):
        if self.size() < 1:
            raise KeyError
        self.remove(self.get_head())

    def size(self):
        return self.count

    def get_head(self):
        return self.head_sentinel.nxt if self.count > 0 else None

    def get_tail(self):
        return self.tail_sentinel.prev if self.count > 0 else None

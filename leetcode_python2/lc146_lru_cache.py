class LRUCache:
    """
    Least Recently Used Cache
    https://leetcode.com/problems/lru-cache/discuss/45926/Python-Dict-+-Double-LinkedList
    Time: O(1) for both get and set.
    """
    def __init__(self, capacity):
        self.capacity = capacity
        self.cache = dict()
        self.head = Node(0, 0)
        self.tail = Node(0, 0)
        self.head.nxt = self.tail
        self.tail.prev = self.head

    def get(self, key):
        if key in self.cache:
            node = self.cache[key]
            self._remove(node)
            self._add(node)
            return node.val
        return -1

    def set(self, key, value):
        if key in self.cache:
            self._remove(self.cache[key])
        n = Node(key, value)
        self._add(n)
        self.cache[key] = n
        if len(self.cache) > self.capacity:
            n = self.head.nxt
            self._remove(n)
            del self.cache[n.key]

    def _remove(self, node):
        p = node.prev
        n = node.nxt
        p.nxt = n
        n.prev = p

    def _add(self, node):
        p = self.tail.prev
        p.nxt = node
        self.tail.prev = node
        node.prev = p
        node.nxt = self.tail


class Node:
    def __init__(self, k, v):
        self.key = k
        self.val = v
        self.prev = None
        self.nxt = None

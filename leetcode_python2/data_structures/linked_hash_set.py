from data_structures.double_linked_list import DoubleLinkedList, Node


class LinkedHashSet(object):
    def __init__(self):
        self.node_map, self.dll = {}, DoubleLinkedList()

    def size(self):
        return len(self.node_map)

    def contains(self, key):
        return key in self.node_map

    def search(self, key):
        if not self.contains(key):
            raise KeyError
        return self.node_map[key].value

    def appendLeft(self, key, value):
        if not self.contains(key):
            node = Node(key, value)
            self.dll.appendLeft(node)
            self.node_map[key] = node
        else:
            self.node_map[key].value = value
            self.moveLeft(key)

    def append(self, key, value):
        if not self.contains(key):
            node = Node(key, value)
            self.dll.append(node)
            self.node_map[key] = node
        else:
            self.node_map[key].value = value
            self.moveRight(key)

    def moveLeft(self, key):
        if not self.contains(key):
            raise KeyError
        node = self.node_map[key]
        self.dll.remove(node)
        self.dll.appendLeft(node)

    def moveRight(self, key):
        if not self.contains(key):
            raise KeyError
        node = self.node_map[key]
        self.dll.remove(node)
        self.dll.append(node)

    def remove(self, key):
        if not self.contains(key):
            raise KeyError
        node = self.node_map[key]
        self.dll.remove(node)
        self.node_map.pop(key)

    def popleft(self):
        key = self.dll.get_head().key
        self.remove(key)
        return key

    def pop(self):
        key = self.dll.get_tail().key
        self.remove(key)
        return key

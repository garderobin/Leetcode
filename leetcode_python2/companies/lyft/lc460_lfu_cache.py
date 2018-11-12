from collections import defaultdict


class LFUCache(object):
    """
    Least Frequently Used Cache
    https://leetcode.com/problems/lfu-cache/discuss/94610/Python-solution-with-detailed-explanation
    Time: O(1) for both get(key) and put(key, value)
    """
    def __init__(self, capacity):
        """
        :type capacity: int
        """
        self.cap = capacity
        self.min_f = -1
        self.cache = {}  # key:(value, frequency)
        self.freq_map = defaultdict(LinkedHashSet)  # frequency:LinkedHashSet

    def get(self, key):
        """
        :type key: int
        :rtype: int
        """
        if key in self.cache:
            # Update new frequency
            # Update freq_map
            # Move key to front in its linkedHashSet
            # Update new minimum frequency
            v, f = self.cache[key][0], self.cache[key][1]
            self.cache[key][1] += 1

            f_count_zero = False
            self.freq_map[f].remove(key)
            if self.freq_map[f].size() == 0:
                f_count_zero = True
                self.freq_map.pop(f)
            self.freq_map[f + 1].appendLeft(key, v)

            if f == self.min_f and f_count_zero:
                self.min_f += 1
            return v
        return -1

    def put(self, key, value):
        """
        :type key: int
        :type value: int
        :rtype: void
        """
        if self.cap == 0:
            return None
        if key in self.cache:
            self.cache[key][0] = value
            self.get(key)
        else:
            curr_size = len(self.cache)
            if curr_size == self.cap:
                min_list = self.freq_map[self.min_f]
                x = min_list.pop()
                self.cache.pop(x)

            self.cache[key] = [value, 1]
            self.freq_map[1].appendLeft(key, value)
            self.min_f = 1
            return

# Your LFUCache object will be instantiated and called as such:
# obj = LFUCache(capacity)
# param_1 = obj.get(key)
# obj.set(key,value)


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



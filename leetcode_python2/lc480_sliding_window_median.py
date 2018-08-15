# coding=utf-8
from abc import ABCMeta, abstractmethod
from bisect import bisect_left, insort_left


class SlidingWindowMedian:
    __metaclass__ = ABCMeta

    @abstractmethod
    def median_sliding_window(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: List[float]
        """


class SlidingWindowMedianImplBisect(SlidingWindowMedian):
    """
    Bisect: http://kuanghy.github.io/2016/06/14/python-bisect
    Time: O((n-k + logk)k)
    Space: O(K)
    但是胜在bisect模块本身性能卓越
    """
    def median_sliding_window(self, nums, k):
        win = nums[:k]
        win.sort()
        res = [(win[k // 2] + win[(k - 1) // 2]) / 2.0]
        for i in range(k, len(nums)):
            index = bisect_left(win, nums[i - k])   # O(logK)
            win.pop(index)                          # O(K)
            insort_left(win, nums[i])               # O(K)
            res.append((win[k // 2] + win[(k - 1) // 2]) / 2.0)
        return res


class SlidingWindowMedianImplHeap(SlidingWindowMedian):
    """
    https://www.jiuzhang.com/solution/sliding-window-median/#tag-highlight-lang-python
    Time: O(Nlogk)，N是元素个数，k 是 window 的大小
    Space: O(N)
    """

    def __init__(self):
        self.max_heap = HashHeap(desc=True)
        self.min_heap = HashHeap()

    def median_sliding_window(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: List[float]
        """
        if not nums:
            return []

        self.max_heap, self.min_heap = HashHeap(desc=True), HashHeap()
        # right heap (min) should always smaller or equal to the size of left (max) heap.
        medians = []
        for index, num in enumerate(nums):
            self.add((float(num), index))

            remove_index = index - k
            if remove_index >= 0:
                remove_element = nums[remove_index]
                self.remove((remove_element, remove_index))

            if remove_index > -2:  # 这一点容易错！当index = k - 1时就需要有median了！
                medians.append(self.median)
        return medians

    def add(self, float_index_pair):
        if float_index_pair[1] > 0 and float_index_pair[0] > self.median:
            self.min_heap.push(float_index_pair)
        else:
            self.max_heap.push(float_index_pair)

        self._balance()

    def remove(self, item):
        self.max_heap.remove(item)
        self.min_heap.remove(item)

        self._balance()

    def _balance(self):
        delta = self.max_heap.size - self.min_heap.size
        if delta == 2:
            self.min_heap.push(self.max_heap.pop())
        elif delta < 0:
            self.max_heap.push(self.min_heap.pop())

    @property
    def median(self):
        if self.max_heap.size == self.min_heap.size:
            return (self.max_heap.top()[0] + self.min_heap.top()[0]) / 2
        else:
            return self.max_heap.top()[0]


class HashHeap:
    """
    使用九章算法强化班中讲到的 HashHeap。即一个 Hash + Heap。
    Hash 的 key 是 Heap 里的每个元素，值是这个元素在 Heap 中的下标。

    要做这个题首先需要先做一下 Data Stream Median。这个题是只在一个集合中增加数，不删除数，然后不断的求中点。
    Sliding Window Median，就是不断的增加数，删除数，然后求中点。比 Data Stream Median 难的地方就在于如何支持删除数。

    因为 Data Stream Median 的方法是用 两个 Heap，一个 max heap，一个min heap。所以删除的话，就需要让 heap 也支持删除操作。
    由于 Python 的 heapq 并不支持 logn 时间内的删除操作，因此只能自己实现一个 hash + heap 的方法。

    总体时间复杂度 O(nlogk)，n是元素个数，k 是 window 的大小。

    TODO: 需要我自己再手打一遍：实现HashHeap这种数据结构
    """

    def __init__(self, desc=False):
        self.hash = dict()
        self.heap = []
        self.desc = desc

    @property
    def size(self):
        return len(self.heap)

    def push(self, item):
        self.heap.append(item)
        self.hash[item] = self.size - 1
        self._sift_up(self.size - 1)

    def pop(self):
        item = self.heap[0]
        self.remove(item)
        return item

    def top(self):
        return self.heap[0]

    def remove(self, item):
        if item not in self.hash:
            return

        index = self.hash[item]
        self._swap(index, self.size - 1)

        del self.hash[item]
        self.heap.pop()

        # in case of the removed item is the last item
        if index < self.size:
            self._sift_up(index)
            self._sift_down(index)

    # 下划线开头是private method
    def _smaller(self, left, right):
        return right < left if self.desc else left < right

    def _sift_up(self, index):
        while index != 0:
            parent = index // 2
            if self._smaller(self.heap[parent], self.heap[index]):
                break
            self._swap(parent, index)
            index = parent

    def _sift_down(self, index):
        if index is None:
            return
        while index * 2 < self.size:
            smallest = index
            left = index * 2
            right = index * 2 + 1

            if self._smaller(self.heap[left], self.heap[smallest]):
                smallest = left

            if right < self.size and self._smaller(self.heap[right], self.heap[smallest]):
                smallest = right

            if smallest == index:
                break

            self._swap(index, smallest)
            index = smallest

    def _swap(self, i, j):
        elem1 = self.heap[i]
        elem2 = self.heap[j]
        self.heap[i] = elem2
        self.heap[j] = elem1
        self.hash[elem1] = j
        self.hash[elem2] = i

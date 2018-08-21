# coding=utf-8
from abc import ABCMeta, abstractmethod


class SkylineProblem(object):
    __metaclass__ = ABCMeta

    @abstractmethod
    def get_skyline(self, buildings):
        """
        :type buildings: List[List[int]]
        :rtype: List[List[int]]
        """


class SkylineProblemImplHashHeap(SkylineProblem):
    """
    SweepLine + HashHeap
    难题上手之前要先枚举清楚test case 否则很多case过不了
    """
    @staticmethod
    def get_sweep_line(buildings):
        sweep_line = []
        for bi, (li, ri, hi) in enumerate(buildings):
            sweep_line.append((li, 0, hi, bi))  # 事关排序，这四个变量的先后顺序很重要！同x的情况下一定要把end往start的前面排
            sweep_line.append((ri, 1, hi, bi))
        return sorted(sweep_line)

    @staticmethod
    def get_current_top_height(max_heap):
        return max_heap.top()[0] if max_heap and max_heap.size > 0 else 0

    def get_skyline(self, buildings):
        """
        :type buildings: List[List[int]]
        :rtype: List[List[int]]
        """
        skyline = []
        max_heap = HashHeap(desc=True)  # a max heap storing (height, building_index)

        cur_top_height = 0
        for x_pos, is_end, height, building_index in self.get_sweep_line(buildings):
            if is_end:
                max_heap.remove((height, building_index))
                cur_top_height = self.get_current_top_height(max_heap)
                if cur_top_height < height:
                    skyline.append((x_pos, cur_top_height))
            else:
                if height > cur_top_height:
                    if skyline and skyline[-1][0] == x_pos:     # 这个检查很重要，会有不同大楼左起点完全相同的情况
                        skyline[-1] = (x_pos, height)
                    else:
                        skyline.append((x_pos, height))
                    cur_top_height = height
                max_heap.push((height, building_index))
        return skyline


class HashHeap:
    def __init__(self, desc=False):
        self.hash = dict()
        self.heap = []
        self.desc = desc

    def __str__(self):
        return str(self.heap)

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

# coding=utf-8
from abc import ABCMeta, abstractmethod

from data_structures.hashheap import HashHeap


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

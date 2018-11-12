# coding=utf-8
import heapq
import math
from abc import ABCMeta, abstractmethod


class MinimizeMaxDistanceToGasStation:
    __metaclass__ = ABCMeta

    @abstractmethod
    def min_max_gas_dist(self, stations, K):
        """
        :type stations: List[int]
        :type K: int
        :rtype: float
        """


class MinimizeMaxDistanceToGasStationImplHeap(MinimizeMaxDistanceToGasStation):
    """
    Time: O(NlogN)
    其实我想到了，但是为什么没有做出来呢， Q_Q
    当想到要维护heap但是又想不通的时候，
    需要明白通过答案来二分是另一种捷径
    TODO: 这种做法是抄的答案，需要自己完全理解并做一遍
    """
    def min_max_gas_dist(self, stations, K):
        max_gap = float(stations[-1] - stations[0]) / K
        gaps = []
        for i in range(len(stations) - 1):
            g = float(stations[i] - stations[i + 1])
            n = max(1, int(-g / max_gap))
            K -= (n - 1)
            gaps.append((g / n, n))

        heapq.heapify(gaps)
        for i in range(K):
            g, n = gaps[0]
            heapq.heapreplace(gaps, (g * n / (n + 1), n + 1))
        return -gaps[0][0]


class MinimizeMaxDistanceToGasStationImplBinarySearch2(MinimizeMaxDistanceToGasStation):
    """
    Time: O(NlogN)
    Space: O(1)
    我比较喜欢的一种做法
    """
    def min_max_gas_dist(self, stations, K):
        gaps = sorted([stations[i] - stations[i - 1] for i in xrange(1, len(stations))], reverse=True)

        def can_allow_smaller_max_gap(max_allow_gap):
            min_required_breaks = 0
            for gap in gaps:
                min_required_breaks += math.ceil(gap / max_allow_gap) - 1
                if min_required_breaks > K:
                    return False
            return True

        start, end = 0.0, float(stations[-1] - stations[0]) / K
        while start + 1e-6 < end:
            mid = (start + end) / 2
            if can_allow_smaller_max_gap(mid):  # 等于的时候一定要考虑，这题等于的时候是区块还可以变小
                end = mid
            else:
                start = mid
        return start


class MinimizeMaxDistanceToGasStationImplBinarySearch(MinimizeMaxDistanceToGasStation):
    """
    Time: O(NlogN)
    Space: O(1)
    """
    def min_max_gas_dist(self, stations, K):

        def get_min_required_breaks(max_allow_gap):
            return sum(math.ceil((stations[i] - stations[i - 1]) / max_allow_gap) - 1 for i in xrange(1, len(stations)))

        start, end = 0.0, float(stations[-1] - stations[0]) / K
        while start + 1e-6 < end:   # TODO: 背下来比较小数的这种办法
            mid = (start + end) / 2
            if get_min_required_breaks(mid) <= K:   # 等于的时候一定要考虑，这题等于的时候是区块还可以变小
                end = mid
            else:
                start = mid
        return start if get_min_required_breaks(start) == K else end

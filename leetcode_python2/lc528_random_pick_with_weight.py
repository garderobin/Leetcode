import random
from abc import ABCMeta, abstractmethod


class RandomPickWithWeight:
    __metaclass__ = ABCMeta

    @abstractmethod
    def pickIndex(self):
        """
        :rtype: int
        """


class RandomPickWithWeightImplBinarySearch(RandomPickWithWeight):
    def __init__(self, w):
        """
        :type w: List[int]
        """
        self.w = w
        self.n = len(w)
        self.s = sum(self.w)
        for i in range(1, self.n):
            w[i] += w[i - 1]

    def pickIndex(self):
        seed = random.randint(1, self.s)
        start, end = 0, self.n - 1
        while start < end:
            mid = (start + end) // 2
            if seed <= self.w[mid]:
                end = mid
            else:
                start = mid + 1
        return start

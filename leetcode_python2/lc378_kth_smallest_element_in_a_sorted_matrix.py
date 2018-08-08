from abc import ABCMeta, abstractmethod
from collections import deque
import heapq


class KthSmallestElementInASortedMatrix:
    __metaclass__ = ABCMeta

    @abstractmethod
    def kth_smallest(self, matrix, k):
        """
        :type matrix: List[List[int]]
        :type k: int
        :rtype: int
        """


class KthSmallestElementInASortedMatrixImplJiuzhang(KthSmallestElementInASortedMatrix):
    def kth_smallest(self, matrix, k):
        n = len(matrix)
        if n == 0:
            return None

        m = len(matrix[0])
        if m == 0:
            return None

        candidates = [(matrix[0][0], 0, 0)]
        visited = {0}
        num = None
        for _ in range(k):
            num, x, y = heapq.heappop(candidates)
            if x + 1 < n and (x + 1) * m + y not in visited:
                heapq.heappush(candidates, (matrix[x + 1][y], x + 1, y))
                visited.add((x + 1) * m + y)
            if y + 1 < m and x * m + y + 1 not in visited:
                heapq.heappush(candidates, (matrix[x][y + 1], x, y + 1))
                visited.add(x * m + y + 1)

        return num

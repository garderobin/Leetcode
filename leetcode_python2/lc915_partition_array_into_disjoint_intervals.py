class Solution(object):
    def partition_disjoint(self, A):
        """
        :type A: List[int]
        :rtype: int
        """
        n = len(A)
        right_mins = [1000001] * (n + 1)
        for i in xrange(n - 1, -1, -1):
            right_mins[i] = min(right_mins[i + 1], A[i])

        left_max = -1
        for i in xrange(n - 1):
            left_max = max(left_max, A[i])
            if left_max <= right_mins[i + 1]:
                return i + 1

        return -1

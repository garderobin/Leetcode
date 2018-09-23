class Solution(object):
    def smallestRangeII(self, A, K):
        """
        :type A: List[int]
        :type K: int
        :rtype: int
        """
        if len(A) == 1:
            return 0

        A.sort(reverse=True)
        result = A[0] - A[-1]
        for i in xrange(1, len(A)):
            result = min(result, max(A[0] - K, A[i] + K) - min(A[i - 1] - K, A[-1] + K))
        return result

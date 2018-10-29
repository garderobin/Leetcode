class Solution(object):
    def minFallingPathSum(self, A):
        """
        :type A: List[List[int]]
        :rtype: int
        """
        rows, cols = len(A), len(A[0])
        f = [[10000 for _ in xrange(cols)] for _ in xrange(rows + 1)]
        f[0] = A[0][:]

        for i in xrange(1, rows):
            for j, a in enumerate(A[i]):
                f[i][j] = a + min(f[i - 1][up_col] for up_col in xrange(max(0, j - 1), min(cols, j + 2)))

        return min(f[rows - 1])

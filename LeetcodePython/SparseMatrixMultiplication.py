class SparseMatrixMultiplication(object):

    # A sparse matrix can be represented as a sequence of rows,
    # each of which is a sequence of (column-number, value) pairs of the nonzero values in the row.
    def multiplyDiscussion2(self, A, B):
        row, col, mid = len(A), len(B[0]), len(A[0])
        rst = [[0 for cols in range(col)] for rows in range(row)]
        cva = [[] for rows in range(row)]

        # for A's non-zero elements of its columns, record their (row, value)
        for i in range(row):
            for k in range(mid):
                if A[i][k] != 0:
                    cva[i].append((k, A[i][k]))

        # multiplication
        for i in range(row):
            for t in cva[i]:
                k, v = t[0], t[1]
                for j in range(col):
                    if B[k][j] != 0:
                        rst[i][j] += v * B[k][j]
        return rst

    # small optimization, by exchanging loop order to filter most zero cases
    def multiplyDiscuss1(self, A, B):
        row, col, mid = len(A), len(B[0]), len(A[0])
        rst = [[0 for cols in range(col)] for rows in range(row)]
        for k in range(mid):
            for i in range(row):
                if A[i][k] != 0:
                    for j in range(col):
                        if B[k][j] != 0:
                            rst[i][j] += A[i][k] * B[k][j]
        return rst

    # brute-force, TLE solution
    def multiply(self, A, B):
        """
        :type A: List[List[int]]
        :type B: List[List[int]]
        :rtype: List[List[int]]
        """
        row, col, mid = len(A), len(B[0]), len(A[0])
        rst = [[0 for cols in range(col)] for rows in range(row)]
        for i in range(row):
            for j in range(col):
                for k in range(mid):
                    rst[i][j] += A[i][k] * B[k][j]
        return rst

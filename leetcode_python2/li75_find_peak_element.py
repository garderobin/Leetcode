class Solution(object):
    """
    @param: A: An integers array.
    @return: return any of peek positions.
    """

    def find_peak(self, A):
        return self.binary_search_peak(A, 1, len(A) - 2)

    def binary_search_peak(self, A, start, end):
        if start == end or A[start] > A[start + 1]:
            return start
        elif A[end] > A[end - 1]:
            return end
        else:
            mid = (start + end) // 2
            if A[mid] > A[mid - 1] and A[mid] > A[mid + 1]:
                return mid
            elif A[mid] < A[mid - 1]:
                return self.binary_search_peak(A, start + 1, mid - 1)
            else:
                return self.binary_search_peak(A, mid + 1, end - 1)

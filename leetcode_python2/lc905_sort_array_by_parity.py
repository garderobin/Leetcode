from abc import ABCMeta, abstractmethod


class SortArrayByParity:
    __metaclass__ = ABCMeta

    @abstractmethod
    def sort_array_by_parity(self, A):
        """
        :type A: List[int]
        :rtype: List[int]
        """


class SortArrayByParityOnePass(SortArrayByParity):

    def sort_array_by_parity(self, A):
        if not A:
            return []

        left, right = 0, len(A) - 1
        while left < right:
            while left < right and A[left] % 2 == 0:
                left += 1
            while left < right and A[right] % 2 == 1:
                right -= 1

            if left < right:
                A[left], A[right] = A[right], A[left]
        return A


class SortArrayByParityImplPivot(SortArrayByParity):
    def sort_array_by_parity(self, A):
        if not A:
            return []
        n = len(A)
        pivot, left, right = len(filter(lambda a: a % 2 == 0, A)), 0, n - 1
        for i in xrange(pivot):
            if A[i] % 2 == 1:
                while right >= pivot and A[right] % 2 == 1:
                    right -= 1
                A[i], A[right] = A[right], A[i]
        return A
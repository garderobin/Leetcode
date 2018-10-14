from abc import ABCMeta, abstractmethod


class SortArrayByParity2:
    __metaclass__ = ABCMeta

    @abstractmethod
    def sort_array_by_parity_2(self, A):
        """
        :type A: List[int]
        :rtype: List[int]
        """


class SortArrayByParity2ImplStack(SortArrayByParity2):
    def sort_array_by_parity_2(self, A):
        result = []
        partitions = [[], []]
        for a in A:
            partitions[a % 2].append(a)

        for i in xrange(len(A)):
            result.append(partitions[i % 2].pop())

        return result

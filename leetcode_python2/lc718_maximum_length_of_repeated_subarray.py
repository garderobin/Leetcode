from abc import ABCMeta, abstractmethod


class MaximumLengthOfRepeatedSubarray:
    __metaclass__ = ABCMeta

    @abstractmethod
    def find_length(self, A, B):
        """
        :type A: List[int]
        :type B: List[int]
        :rtype: int
        """

from abc import ABCMeta, abstractmethod


class DegreeOfAnArray:
    __metaclass__ = ABCMeta

    @abstractmethod
    def find_shortest_subarray(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """


class DegreeOfAnArrayImplCountDict(DegreeOfAnArray):
    def find_shortest_subarray(self, nums):
        degree_dict = dict()
        for index, num in enumerate(nums):
            if num in degree_dict:
                nega_count, shortest_array_length, start_index = degree_dict[num]
                degree_dict[num] = [nega_count - 1, index - start_index + 1, start_index]
            else:
                degree_dict[num] = [-1, 1, index]
        return sorted(degree_dict.values())[0][1]

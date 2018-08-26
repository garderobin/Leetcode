from abc import ABCMeta, abstractmethod


class FindAllElementsDisappearedInAnArray:
    __metaclass__ = ABCMeta

    @abstractmethod
    def find_disappeared_numbers_in_array(self, nums):
        """
        https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/description/
        :type nums: List[int]
        :rtype: List[int]
        """


class FindAllElementsDisappearedInAnArrayImplementation(FindAllElementsDisappearedInAnArray):

    def find_disappeared_numbers_in_array(self, nums):
        num_set = set(nums)
        disappeared_nums = []
        n = len(nums)
        for expect_value in range(1, n+1):
            if expect_value not in num_set:
                disappeared_nums.append(expect_value)
        return disappeared_nums


class FindAllElementsDisappearedInAnArrayImplementation2(FindAllElementsDisappearedInAnArray):

    def find_disappeared_numbers_in_array(self, nums):
        disappeared_nums = []
        n = len(nums)

        num_appeared = [True] + [False] * n
        for num in nums:
            num_appeared[num] = True

        for expect_num in xrange(1, n+1):
            if not num_appeared[expect_num]:
                disappeared_nums.append(expect_num)

        return disappeared_nums
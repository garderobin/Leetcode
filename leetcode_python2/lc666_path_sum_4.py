# coding=utf-8
from abc import ABCMeta, abstractmethod


class PathSum4:
    __metaclass__ = ABCMeta

    @abstractmethod
    def path_sum(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """


class PathSum4ImplDFS2(PathSum4):
    """
    这题就是最基本的DFS， 每个元素只会被访问一遍，所以记忆化搜索纯属无必要
    Time: O(N)
    Space: O(N)
    """
    def __init__(self):
        self.value_dict = {}
        self.total_path_sum = 0

    def path_sum(self, nums):
        if not nums:
            return 0

        self.value_dict = {}
        for num in nums:
            self.value_dict[num // 10] = num % 10

        self.total_path_sum = 0
        self.count_leaves(nums[0] // 10)
        return self.total_path_sum

    def count_leaves(self, key):
        position = key % 10
        left_key, right_key = key + position + 9, key + position + 10

        if left_key not in self.value_dict and right_key not in self.value_dict:
            self.total_path_sum += self.value_dict[key]
            return 1
        else:
            cur_count = 0
            if left_key in self.value_dict:
                cur_count += self.count_leaves(left_key)
            if right_key in self.value_dict:
                cur_count += self.count_leaves(right_key)
            self.total_path_sum += cur_count * self.value_dict[key]
            return cur_count


class PathSum4ImplDFS(PathSum4):
    def __init__(self):
        self.value_dict = {}
        self.count_dict = {}

    def path_sum(self, nums):
        if not nums:
            return 0

        self.value_dict, self.count_dict = {}, {}
        for num in nums:
            self.value_dict[num / 10] = num % 10

        self.count_leaves(nums[0] / 10, nums[0] % 10)
        return sum(self.count_dict[num / 10] * (num % 10) for num in nums)

    def count_leaves(self, key, value):
        if key in self.count_dict:
            return self.count_dict[key]

        position = key % 10
        left_key, right_key = key + position + 9, key + position + 10

        if left_key not in self.value_dict and right_key not in self.value_dict:
            self.count_dict[key] = 1
            return 1
        else:
            cur_count = 0
            if left_key in self.value_dict:
                cur_count += self.count_leaves(left_key, self.value_dict[left_key])
            if right_key in self.value_dict:
                cur_count += self.count_leaves(right_key, self.value_dict[right_key])
            self.count_dict[key] = cur_count
            return cur_count

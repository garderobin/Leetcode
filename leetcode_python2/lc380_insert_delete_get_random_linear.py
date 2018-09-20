# coding=utf-8
import random


class RandomizedSet(object):
    """
    高频题讲过的load balancer
    """
    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.nums = []
        self.index_dict = {}

    def insert(self, val):
        """
        Inserts a value to the set. Returns true if the set did not already contain the specified element.
        :type val: int
        :rtype: bool
        """
        if val not in self.index_dict:
            self.index_dict[val] = len(self.nums)
            self.nums.append(val)
            return True
        else:
            return False

    def remove(self, val):
        """
        Removes a value from the set. Returns true if the set contained the specified element.
        :type val: int
        :rtype: bool
        """
        if val in self.index_dict:
            cur_index = self.index_dict[val]
            last_element = self.nums.pop()
            if cur_index < len(self.nums):
                self.nums[cur_index] = last_element
                self.index_dict[last_element] = cur_index
            del self.index_dict[val]
            return True
        else:
            return False

    def getRandom(self):
        """
        Get a random element from the set.
        :rtype: int
        """
        return self.nums[random.randint(0, len(self.nums) - 1)]


# Your RandomizedSet object will be instantiated and called as such:
# obj = RandomizedSet()
# param_1 = obj.insert(val)
# param_2 = obj.remove(val)
# param_3 = obj.getRandom()
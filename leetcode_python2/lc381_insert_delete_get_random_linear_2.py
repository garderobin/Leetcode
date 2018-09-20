# coding=utf-8
import random
from abc import ABCMeta, abstractmethod
from collections import defaultdict


class RandomizedCollection:
    __metaclass__ = ABCMeta

    @abstractmethod
    def insert(self, val):
        """
        Inserts a value to the collection. Returns true if the collection did not already contain the specified element.
        :type val: int
        :rtype: bool
        """

    @abstractmethod
    def remove(self, val):
        """
        Removes a value from the collection. Returns true if the collection contained the specified element.
        :type val: int
        :rtype: bool
        """

    @abstractmethod
    def getRandom(self):
        """
        Get a random element from the collection.
        :rtype: int
        """


class RandomizedCollectionImpl2(RandomizedCollection):
    """
    defaultdict性能不够卓越，但是用起来非常方便，不用担心忘记删除变成0的entry
    """
    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.nums = []
        self.index_dict = defaultdict(set)

    def insert(self, val):
        """
        Inserts a value to the collection. Returns true if the collection did not already contain the specified element.
        :type val: int
        :rtype: bool
        """
        self.nums.append(val)
        if not self.index_dict[val]:
            self.index_dict[val] = {len(self.nums) - 1}
            return True
        else:
            self.index_dict[val].add(len(self.nums) - 1)
            return False

    def remove(self, val):
        """
        Removes a value from the collection. Returns true if the collection contained the specified element.
        :type val: int
        :rtype: bool
        """
        if not self.index_dict[val]:    # 正确使用defaultdict
            last_element = self.nums.pop()
            self.index_dict[last_element].discard(len(self.nums))   # set的一些非常见操作也要熟悉

            if val != last_element:
                remove_val_index = self.index_dict[val].pop()   # set的pop不是随机的，但是对这里的use case来说无所谓
                self.nums[remove_val_index] = last_element    # 双数组操作，每一次交换都要考察是不是每个辅助数组都被update了
                self.index_dict[last_element].add(remove_val_index)

            return True
        else:
            return False

    def getRandom(self):
        """
        Get a random element from the collection.
        :rtype: int
        """
        return random.choice(self.nums)     # 熟背random用法


class RandomizedCollectionImpl(RandomizedCollection):

    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.nums = []
        self.index_dict = {}

    def insert(self, val):
        """
        Inserts a value to the collection. Returns true if the collection did not already contain the specified element.
        :type val: int
        :rtype: bool
        """
        self.nums.append(val)
        if val not in self.index_dict:
            self.index_dict[val] = {len(self.nums) - 1}
            return True
        else:
            self.index_dict[val].add(len(self.nums) - 1)
            return False

    def remove(self, val):
        """
        Removes a value from the collection. Returns true if the collection contained the specified element.
        :type val: int
        :rtype: bool
        """
        if val in self.index_dict:
            last_element = self.nums.pop()
            self.index_dict[last_element].discard(len(self.nums))   # set的一些非常见操作也要熟悉

            if val != last_element:
                remove_val_index = self.index_dict[val].pop()   # set的pop不是随机的，但是对这里的use case来说无所谓
                self.nums[remove_val_index] = last_element    # 双数组操作，每一次交换都要考察是不是每个辅助数组都被update了
                self.index_dict[last_element].add(remove_val_index)

            if len(self.index_dict[val]) == 0:      # 非常容易忘，用dict做动态计数器的时候，总要有隐忧, 是不是计数器归零需要清理item
                del self.index_dict[val]

            return True
        else:
            return False

    def getRandom(self):
        """
        Get a random element from the collection.
        :rtype: int
        """
        return random.choice(self.nums)     # 熟背random用法

# Your RandomizedCollection object will be instantiated and called as such:
# obj = RandomizedCollection()
# param_1 = obj.insert(val)
# param_2 = obj.remove(val)
# param_3 = obj.getRandom()

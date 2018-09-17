# coding=utf-8
from abc import ABCMeta, abstractmethod


class FruitIntoBasket:
    __metaclass__ = ABCMeta

    @abstractmethod
    def total_fruit(self, tree):
        """
        :type tree: List[int]
        :rtype: int
        """


class FruitIntoBasketImpl(FruitIntoBasket):
    """
    TODO： 背下这题作为模板：同向双指针使用辅助counter dict结构，如何推右，如何退左
    """
    def total_fruit(self, tree):
        if not tree:
            return 0
        collection_count_by_types = {}
        j = 0
        n = len(tree)
        max_collection = 0
        for i, fruit_type in enumerate(tree):
            while j < n:    # 之所以这里不直接用j < n and len(collection_count_by_types) < 3是因为允许越界以后还需要回退count
                if tree[j] in collection_count_by_types:
                    collection_count_by_types[tree[j]] += 1
                elif len(collection_count_by_types) < 2:
                    collection_count_by_types[tree[j]] = 1
                else:   # 使用counter类型来做two pointer的时候越界检查的回退非常不容易做，最好直接不要允许越界
                    break
                j += 1

            if j - i > max_collection:
                max_collection = j - i

            collection_count_by_types[fruit_type] -= 1
            if collection_count_by_types[fruit_type] == 0:
                del collection_count_by_types[fruit_type]
        return max_collection

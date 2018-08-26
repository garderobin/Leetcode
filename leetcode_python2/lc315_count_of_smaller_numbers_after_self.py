from abc import ABCMeta, abstractmethod


class CountOfSmallerNumbersAfterSelf:
    __metaclass__ = ABCMeta

    @abstractmethod
    def count_smaller(self, nums):
        """
        :type nums: List[int]
        :rtype: List[int]
        """


class CountOfSmallerNumbersAfterSelfImplMergeSort(CountOfSmallerNumbersAfterSelf):
    """
    Java: https://leetcode.com/problems/count-of-smaller-numbers-after-self/discuss/76583/11ms-JAVA-solution-using-merge-sort-with-explanation
    Python:
    yield usage: https://www.ibm.com/developerworks/cn/opensource/os-cn-python-yield/index.html
    https://www.jianshu.com/p/d09778f4e055
    """
    def __init__(self):
        self.smaller_count = []

    def count_smaller(self, nums):
        def sort(enum):
            half = len(enum) / 2
            if half:
                left, right = sort(enum[:half]), sort(enum[half:])
                for i in range(len(enum))[::-1]:
                    if not right or left and left[-1][1] > right[-1][1]:
                        smaller[left[-1][0]] += len(right)
                        enum[i] = left.pop()
                    else:
                        enum[i] = right.pop()
            return enum

        smaller = [0] * len(nums)
        sort(list(enumerate(nums)))
        return smaller


class CountOfSmallerNumbersAfterSelfImplBinaryIndexedTree(CountOfSmallerNumbersAfterSelf):
    def count_smaller(self, nums):
        """
        Binary Indexed Tree (Fenwick Tree):
        https://www.jianshu.com/p/5b209c029acd
        http://yutianx.info/2014/11/18/2014-11-18-segment-tree/
        完全没搞懂这个解法:
        https://leetcode.com/problems/count-of-smaller-numbers-after-self/discuss/76657/3-ways-(Segment-Tree-Binary-Indexed-Tree-Binary-Search-Tree)-clean-python-code
        """
        value_order = {v: i for i, v in enumerate(sorted(set(nums)))}

        tree, r = BinaryIndexedTree(len(value_order)), []
        for i in xrange(len(nums) - 1, -1, -1):
            r.append(tree.sum(value_order[nums[i]]))
            tree.update(value_order[nums[i]] + 1, 1)
        return r[::-1]


class BinaryIndexedTree(object):
    def __init__(self, n):
        self.sums = [0] * (n + 1)

    def update(self, i, val):
        while i < len(self.sums):
            self.sums[i] += 1
            i += i & -i

    def sum(self, i):
        r = 0
        while i > 0:
            r += self.sums[i]
            i -= i & -i
        return r


class CountOfSmallerNumbersAfterSelfImplSegmentTree(CountOfSmallerNumbersAfterSelf):
    """
    Segment Tree: https://blog.csdn.net/Yaokai_AssultMaster/article/details/79599809
    完全没搞懂这个解法:
    https://leetcode.com/problems/count-of-smaller-numbers-after-self/discuss/76657/3-ways-(Segment-Tree-Binary-Indexed-Tree-Binary-Search-Tree)-clean-python-code
    """
    def count_smaller(self, nums):
        value_order = {v: i for i, v in enumerate(sorted(set(nums)))}
        print value_order

        tree, r = SegmentTree(len(value_order)), []
        print tree
        for i in xrange(len(nums) - 1, -1, -1):
            r.append(tree.sum(0, value_order[nums[i]] - 1))
            tree.update(value_order[nums[i]], 1)
        return r[::-1]


class SegmentTreeNode(object):
    def __init__(self, val, start, end):
        self.val = val
        self.start = start
        self.end = end
        self.children = []


class SegmentTree(object):
    def __init__(self, n):
        self.root = self.build(0, n - 1)

    def build(self, start, end):
        if start > end:
            return

        root = SegmentTreeNode(0, start, end)
        if start == end:
            return root

        mid = start + end >> 1
        root.children = filter(None, [
            self.build(start, end)
            for start, end in ((start, mid), (mid + 1, end))])
        return root

    def update(self, i, val, root=None):
        root = root or self.root
        if i < root.start or i > root.end:
            return root.val

        if i == root.start == root.end:
            root.val += val
            return root.val

        root.val = sum([self.update(i, val, c) for c in root.children])
        return root.val

    def sum(self, start, end, root=None):
        root = root or self.root
        if end < root.start or start > root.end:
            return 0

        if start <= root.start and end >= root.end:
            return root.val

        return sum([self.sum(start, end, c) for c in root.children])


if __name__ == "__main__":
    sol = CountOfSmallerNumbersAfterSelfImplSegmentTree()
    test_case = [5, 2, 6, 1]
    print sol.count_smaller(test_case)

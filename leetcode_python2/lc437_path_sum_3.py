# coding=utf-8
from abc import ABCMeta, abstractmethod
from collections import deque


class PathSum3:
    __metaclass__ = ABCMeta

    @abstractmethod
    def pathSum(self, root, sum):
        """
        :type root: TreeNode
        :type sum: int
        :rtype: int
        """


class PathSum3ImplDFSPrefixSums2(PathSum3):
    """
    TODO: 复习经典dfs with dict as parameter 的场景
    只要有reset, 就不用传递copy, 这是这道题用dfs相对于bfs的优越之处
    Time: O(N)
    Space: O(h)
    """
    def pathSum(self, root, sum):
        def dfs(node, prefix_sum, sums_counter):
            if not node:
                return 0

            prefix_sum += node.val
            res = sums_counter.get(prefix_sum - sum, 0)

            sums_counter[prefix_sum] = 1 + res
            if node.left:
                res += dfs(node.left, prefix_sum, sums_counter)
            if node.right:
                res += dfs(node.right, prefix_sum, sums_counter)
            sums_counter[prefix_sum] -= 1
            return res

        return dfs(root, 0, {0: 1})


class PathSum3ImplDFSPrefixSums(PathSum3):
    def pathSum(self, root, sum):
        def dfs(node, prefix_sum, sums_counter, total_paths):
            if not node:
                return total_paths

            prefix_sum += node.val
            total_paths += sums_counter.get(prefix_sum - sum, 0)

            sums_counter[prefix_sum] = 1 + sums_counter.get(prefix_sum, 0)
            if node.left:
                total_paths = dfs(node.left, prefix_sum, sums_counter.copy(), total_paths)
            if node.right:
                total_paths = dfs(node.right, prefix_sum, sums_counter.copy(), total_paths)

            return total_paths

        return dfs(root, 0, {0: 1}, 0)


class PathSum3ImplBFS(PathSum3):
    """
    TODO 常见陷阱：
    1. 这里一定得用counter dict, 用set会少算，因为这题允许负数或者0
    如果这题全是positive integer， 可以用set
    审题的时候看到区间枚举，区间可以为零或者负数，尤其要着重推演有零有负的情况
    2. dict 和 set 放进queue里的都是引用，必须先copy才能进queue
    3. 一开始的时候一个元素也没有到时候也能是valid information这一点我很高兴，最近在写迭代到时候初始位置会double check了。
    """
    def pathSum(self, root, sum):
        if not root:
            return 0

        q = deque([(root, 0, {0: 1})])
        target, total_paths = sum, 0
        while q:
            node, prefix_sum, sums_counter = q.popleft()

            prefix_sum += node.val
            if prefix_sum - target in sums_counter:
                total_paths += sums_counter[prefix_sum - target]

            sums_counter[prefix_sum] = 1 + sums_counter.get(prefix_sum, 0)
            if node.left:
                q.append((node.left, prefix_sum, sums_counter.copy()))
            if node.right:
                q.append((node.right, prefix_sum, sums_counter.copy()))

        return total_paths


# class PathSum3ImplDFSBruteForce(PathSum3):
#     """
#     https://leetcode.com/problems/path-sum-iii/description/
#     Memory limit exceeded
#     TODO: 怎么分析递归的空间复杂度？
#     Time: O(NlogN) on average, O(N^2) on worst case, O(h * (2 ^ h)), h = logN, N = 整个树的所有节点数
#     Space: O(NlogN), O(h * (2 ^ h))
#     """
#     def pathSum(self, root, sum):
#         if not root:
#             return 0
#         return self.count_path(root, sum) + self.pathSum(root.left, sum) + self.pathSum(root.right, sum)
#
#     def count_path(self, node, target):
#         if not node:
#             return 0
#         count = int(node.val == target)
#         count += self.count_path(node.left, target - node.val)
#         count += self.count_path(node.right, target - node.val)
#         return count


# class PathSum3ImplMemoSearch(PathSum3):
#     def pathSum(self, root, sum):
#         if not root:
#             return 0
#         return self.count_path(root, sum) + self.pathSum(root.left, sum) + self.pathSum(root.right, sum)
#
#     def count_path(self, node, target):
#         if not node:
#             return 0
#         count = int(node.val == target)
#         count += self.count_path(node.left, target - node.val)
#         count += self.count_path(node.right, target - node.val)
#         return count


class PathSum3ImplDFSRecursion(PathSum3):
    """
    https://leetcode.com/problems/path-sum-iii/description/
    Memory limit exceeded
    TODO: 怎么分析递归的空间复杂度？
    Time: O(NlogN) on average, O(N^2) on worst case, O(h * (2 ^ h)), h = logN, N = 整个树的所有节点数
    Space: O(NlogN), O(h * (2 ^ h))
    """
    def pathSum(self, root, sum):
        return self.dfs(root, sum, 0, {0}, 0)

    def dfs(self, root, target, prefix_sum, sum_set, total_paths):
        if not root:
            return total_paths

        new_prefix_sum = prefix_sum + root.val
        if new_prefix_sum - target in sum_set:
            total_paths += 1

        new_sum_set = sum_set.copy()
        new_sum_set.add(new_prefix_sum)

        if root.left:
            total_paths = self.dfs(root, target, new_prefix_sum, new_sum_set, total_paths)
        if root.right:
            total_paths = self.dfs(root, target, new_prefix_sum, new_sum_set, total_paths)
        return total_paths

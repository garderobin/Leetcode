from abc import ABCMeta, abstractmethod


class PathSum3NArray:
    __metaclass__ = ABCMeta

    @abstractmethod
    def pathSum(self, root, target):
        """
        :type root: TreeNode
        :type target: int
        :rtype: int
        """


class PathSum3NArrayDFSPrefixSums(PathSum3NArray):
    def pathSum(self, root, target):
        def dfs(node, prefix_sum, sums_counter):
            if not node:
                return 0

            prefix_sum += node.val
            res = sums_counter.get(prefix_sum - sum, 0)

            sums_counter[prefix_sum] = 1 + sums_counter.get(prefix_sum, 0)
            res += sum(dfs(child, prefix_sum, sums_counter) for child in node.children)
            sums_counter[prefix_sum] -= 1
            return res
        return dfs(root, 0, {0: 1})

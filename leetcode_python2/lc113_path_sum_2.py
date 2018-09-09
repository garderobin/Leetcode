# coding=utf-8
from abc import ABCMeta, abstractmethod


class PathSum2:
    __metaclass__ = ABCMeta

    @abstractmethod
    def path_sum(self, root, sum):
        """
        :type root: TreeNode
        :type sum: int
        :rtype: List[List[int]]
        """


class PathSum2Impl(PathSum2):
    """
    难的是一遍bug free
    几个易错点：
    1. 条件检验型 / 极值型的遍历，left满足条件到话不需要再检查right, 但是枚举型遍历不可以，左右都要照顾到
    2. 递归调用穿进去的参数会被修改，所以调过left再去调right的时候一定注意传参有没有被之前的调用改过
    3. 巧用length, 尽可能减少list copy操作
    """
    def path_sum(self, root, sum):
        return self.dfs(root, sum, [], [])

    def dfs(self, root, target, path, results):
        if not root:
            return results

        path.append(root.val)
        height = len(path)

        if (not root.left) and (not root.right):
            if root.val == target:
                results.append(path)
            return results
        else:
            if root.left:
                results = self.dfs(root.left, target - root.val, path, results)
            if root.right:
                results = self.dfs(root.right, target - root.val, path[:height], results)   # path作为参数会被上面一次调用修改掉的
            return results

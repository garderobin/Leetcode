from abc import ABCMeta, abstractmethod


class CrackingTheCafe:
    __metaclass__ = ABCMeta

    @abstractmethod
    def crack_safe(self, n, k):
        """
        :type n: int
        :type k: int
        :rtype: str
        """


class CrackingTheCafeDFS(CrackingTheCafe):
    def __init__(self):
        self.n = 0
        self.k = 0

    def crack_safe(self, n, k):
        self.n, self.k = n, k
        pass

    def dfs(self, n, k, builder, visited):
        pass
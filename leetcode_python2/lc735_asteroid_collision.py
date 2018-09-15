# coding=utf-8
from abc import ABCMeta, abstractmethod


class AsteroidCollision:
    __metaclass__ = ABCMeta

    @abstractmethod
    def asteroid_collision(self, asteroids):
        """
        :type asteroids: List[int]
        :rtype: List[int]
        """


class AsteroidCollisionImplDP(AsteroidCollision):
    """
    终于one-pass了
    学会有限使用增量思维来dp化问题，不行了再区间化考虑
    一开始我想到哪个区间化到方案可以做，但是过于复杂
    """
    def asteroid_collision(self, asteroids):
        survivors = []
        for a in asteroids:
            if a == 0:
                continue
            elif a > 0 or (not survivors) or survivors[-1] < 0:
                survivors.append(a)
            else:
                while survivors and 0 < survivors[-1] < -a:
                    survivors.pop()
                if not survivors or survivors[-1] < 0:
                    survivors.append(a)
                elif survivors[-1] == -a:
                    survivors.pop()
                else:
                    continue
        return survivors

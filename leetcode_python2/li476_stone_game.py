from abc import ABCMeta, abstractmethod


class StoneGame(object):
    __metaclass__ = ABCMeta

    @abstractmethod
    def stone_game(self, piles):
        """
        :type piles: List[int]
        :rtype: bool
        """

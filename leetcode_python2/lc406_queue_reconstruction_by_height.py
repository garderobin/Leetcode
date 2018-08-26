from abc import ABCMeta, abstractmethod


class QueueReconstructionByHeight:
    """
    https://leetcode.com/problems/queue-reconstruction-by-height/description/
    """
    __metaclass__ = ABCMeta

    @abstractmethod
    def reconstruct_queue(self, people):
        """
        :type people: List[List[int]]
        :rtype: List[List[int]]
        """


class QueueReconstructionByHeightImpl(QueueReconstructionByHeight):
    """
    Time: O(N^2)
    Space: O(N)
    """
    def reconstruct_queue(self, people):
        people.sort(key=lambda(h, k): (-h, k))
        queue = []
        for person in people:
            queue.insert(person[1], person)
        return queue


class QueueReconstructionByHeightInPlaceImpl(QueueReconstructionByHeight):
    """
    Time: O(N^2)
    Space: O(N)
    """
    def reconstruct_queue(self, people):
        people.sort(key=lambda(h, k): (-h, k))
        queue = []
        for person in people:
            queue.insert(person[1], person)
        return queue

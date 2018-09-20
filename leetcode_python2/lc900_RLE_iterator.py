from _bisect import bisect_left


class RLEIterator(object):

    def __init__(self, A):
        """
        :type A: List[int]
        """
        self.num_ends = {}
        self.milestones = []
        if A:
            size = 0
            for i in xrange(0, len(A), 2):
                if A[i] > 0:
                    size += A[i]
                    self.num_ends[size] = A[i + 1]
                    self.milestones.append(size)
        self.start = 0
        self.milestone = 0

    def next(self, n):
        """
        :type n: int
        :rtype: int
        """
        self.start += n
        if self.start > self.milestones[-1]:
            self.milestone = len(self.milestones)
            return -1
        else:
            self.milestone = bisect_left(self.milestones, self.start, self.milestone)
            return self.num_ends[self.milestones[self.milestone]]

# Your RLEIterator object will be instantiated and called as such:
# obj = RLEIterator(A)
# param_1 = obj.next(n)

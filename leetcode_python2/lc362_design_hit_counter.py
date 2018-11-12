from abc import ABCMeta, abstractmethod
from collections import deque, OrderedDict


class HitCounter:
    __metaclass__ = ABCMeta

    @abstractmethod
    def hit(self, timestamp):
        """
        Record a hit.
        @param timestamp - The current timestamp (in seconds granularity).
        :type timestamp: int
        :rtype: void
        """

    @abstractmethod
    def getHits(self, timestamp):
        """
        Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity).
        :type timestamp: int
        :rtype: int
        """


class HitCounterImplRotateArray(HitCounter):
    def __init__(self):
        self.times = [0] * 300
        self.hits = [0] * 300

    def hit(self, timestamp):
        idx = timestamp % 300
        if self.times[idx] != timestamp:
            self.times[idx] = timestamp
            self.hits[idx] = 1
        else:
            self.hits[idx] += 1

    def getHits(self, timestamp):
        expire_timestamp = timestamp - 300
        return sum(self.hits[i] for i in xrange(300) if self.times[i] > expire_timestamp)


class HitCounterImplOrderedDict(HitCounter):
    def __init__(self):
        self.counter = OrderedDict()
        self.times = deque([])
        self.valid_hits = 0

    def hit(self, timestamp):
        self.clean_expired_hits(timestamp)
        if timestamp in self.counter:
            self.counter[timestamp] += 1
        else:
            self.counter[timestamp] = 1
            self.times.append(timestamp)

        self.valid_hits += 1

    def getHits(self, timestamp):
        self.clean_expired_hits(timestamp)
        return self.valid_hits

    def clean_expired_hits(self, timestamp):
        while self.times and self.times[0] + 299 < timestamp:
            self.times.popleft()
            (time, count) = self.counter.popitem(last=False)
            self.valid_hits -= count


class HitCounterImplDeque(object):

    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.hit_times = deque([])

    def hit(self, timestamp):
        """
        Record a hit.
        @param timestamp - The current timestamp (in seconds granularity).
        :type timestamp: int
        :rtype: void
        """
        self.clean_expired_hits(timestamp)
        self.hit_times.append(timestamp)

    def getHits(self, timestamp):
        """
        Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity).
        :type timestamp: int
        :rtype: int
        """
        self.clean_expired_hits(timestamp)
        return len(self.hit_times)

    def clean_expired_hits(self, timestamp):
        while self.hit_times and self.hit_times[0] + 299 < timestamp:
            self.hit_times.popleft()


# Your HitCounter object will be instantiated and called as such:
# obj = HitCounter()
# obj.hit(timestamp)
# param_2 = obj.getHits(timestamp)

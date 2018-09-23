from bisect import bisect_right
from collections import defaultdict


class TopVotedCandidate(object):

    def __init__(self, persons, times):
        """
        :type persons: List[int]
        :type times: List[int]
        """

        self.times = times
        self.leaders = []

        leader = -1
        counter_dict = defaultdict(int)
        for p in persons:
            counter_dict[p] += 1
            if counter_dict[p] >= counter_dict[leader]:
                leader = p
            self.leaders.append(leader)

    def q(self, t):
        """
        :type t: int
        :rtype: int
        """
        return self.leaders[bisect_right(self.times, t) - 1]

# Your TopVotedCandidate object will be instantiated and called as such:
# obj = TopVotedCandidate(persons, times)
# param_1 = obj.q(t)

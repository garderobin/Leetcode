from collections import defaultdict


class Solution(object):
    """
    Time: O(N)
    Space: O(1)
    """
    def numSubarraysWithSum(self, A, S):
        """
        :type A: List[int]
        :type S: int
        :rtype: int
        """
        if not A:
            return 0

        result = 0
        ps = 0
        prefix_sum_counter = defaultdict(int)
        prefix_sum_counter[0] = 1
        for a in A:
            ps += a
            result += prefix_sum_counter[ps - S]
            prefix_sum_counter[ps] += 1
        return result

from abc import ABCMeta, abstractmethod


class TotalHammingDistance:
    __metaclass__ = ABCMeta

    @abstractmethod
    def total_hamming_distance(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """


class TotalHammingDistanceImpl(TotalHammingDistance):

    def total_hamming_distance(self, nums):
        total_hamming_distance = 0
        for bin_index in xrange(0, 32):
            bit_one_total_count = 0
            for num in nums:
                cur_bit = num & (1 << bin_index)
                if cur_bit:
                    bit_one_total_count += 1
            total_hamming_distance += bit_one_total_count * (len(nums) - bit_one_total_count)
        return total_hamming_distance

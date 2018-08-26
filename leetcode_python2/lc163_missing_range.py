from abc import ABCMeta, abstractmethod


class MissingRanges:
    __metaclass__ = ABCMeta

    @abstractmethod
    def find_missing_ranges(self, nums, lower, upper):
        """
        :type nums: List[int]
        :type lower: int
        :type upper: int
        :rtype: List[str]
        """


class MissingRangeImplLinear(MissingRanges):
    def find_missing_ranges(self, nums, lower, upper):
        missing_ranges = []
        if not nums:
            self.add_range(missing_ranges, lower, upper)
            return missing_ranges
        n = len(nums)
        start = lower
        for index, miss in enumerate(nums):
            self.add_range(missing_ranges, start, miss - 1)
            start = miss + 1

        self.add_range(missing_ranges, nums[n-1] + 1, upper)
        return missing_ranges

    @staticmethod
    def add_range(ranges, start, end):
        if start > end:
            return
        elif start == end:
            ranges.append(str(start))
        else:
            ranges.append('%d->%d' % (start, end))

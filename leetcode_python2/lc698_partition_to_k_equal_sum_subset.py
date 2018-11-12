from abc import ABCMeta, abstractmethod


class PartitionToKEqualSumSubset:
    __metaclass__ = ABCMeta

    @abstractmethod
    def canPartitionKSubsets(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: bool
        """


class PartitionToKEqualSumSubsetImplDFSWithBuckets(PartitionToKEqualSumSubset):
    def canPartitionKSubsets(self, nums, k):
        sums = [0] * k
        total = sum(nums)
        if total % k:
            return False
        subsum = total / k
        nums.sort(reverse=True)
        n = len(nums)

        def dfs(i):
            if i == n:
                return len(set(sums)) == 1
            for bucket in xrange(k):
                sums[bucket] += nums[i]
                if sums[bucket] <= subsum and dfs(i+1):
                    return True
                sums[bucket] -= nums[i]
                if sums[bucket] == 0:
                    break   # no need to try other empty buckets
            return False

        return dfs(0)

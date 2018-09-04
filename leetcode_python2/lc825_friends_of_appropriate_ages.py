# coding=utf-8
from _bisect import bisect_right
from abc import ABCMeta, abstractmethod


class FriendsOfAppropriateAges:
    __metaclass__ = ABCMeta

    @abstractmethod
    def num_friend_requests(self, ages):
        """
        :type ages: List[int]
        :rtype: int
        """


class FriendsOfAppropriateAgesImpl(FriendsOfAppropriateAges):
    """
    Time: O(N)
    Space: O(1)
    如果遍历的时候把每个元素当做A， 去找符合条件的B的区间：(a / 2 + 7, a]
    这样做的好处是减少处理难度，因为B一定比A小，所以找符合条件有多少个B不需要二分向后查找
    """
    def num_friend_requests(self, ages):
        num_requests = 0
        num_in_age, sum_in_age = [0] * 121, [0] * 121
        for age in ages:
            num_in_age[age] += 1
        for age in xrange(1, 121):
            sum_in_age[age] = sum_in_age[age - 1] + num_in_age[age]
        for a_age in xrange(15, 121):
            if num_in_age[a_age] > 0:
                num_requests += num_in_age[a_age] * (sum_in_age[a_age] - sum_in_age[a_age // 2 + 7] - 1)
        return num_requests


class FriendsOfAppropriateAgesImplBisectWithBucketSorting(FriendsOfAppropriateAges):
    """
    Time: O(N)
    Space: O(1)
    很难bug-free
    再简单的题也要列表推演确保一步到位

    思路：先排序, 排序过程中顺便把不可能收到request的user排除掉(age < 15)
    然后从小到大把每个数当做B，找有多少符合条件的A, A的年龄取值范围是[b, 2 * b -15]
    """
    def num_friend_requests(self, ages):
        if not ages:
            return 0
        vals, counts, prefix_counts = self.bucket_sorting(ages, 15, 120)
        num_requests, n, insert_idx, valid_users = 0, len(ages), -1, counts[-1] + prefix_counts[-1]
        for i, b_age in enumerate(vals):
            max_a_age = b_age + b_age - 15
            if max_a_age >= vals[-1]:
                prefix_right = valid_users
            else:
                insert_idx = bisect_right(vals, max_a_age, max(insert_idx, i))
                prefix_right = prefix_counts[insert_idx]
            num_requests += counts[i] * (prefix_right - prefix_counts[i] - 1)
        return num_requests

    def bucket_sorting(self, nums, lower_bound, upper_bound):
        """
        :param upper_bound: upper bound of bucket, inclusive
        :param lower_bound: lower bound of bucket, inclusive
        :param nums: List[int]
        :return: (list of valid nums, list of counts of valid nums, list of prefix counts of valid_nums)
        all sorted in asc order
        """
        buckets, vals, counts, prefix_counts = [0] * (upper_bound + 1), [], [], []
        for val in nums:
            if val >= lower_bound:  # users with age < 15 can't receive any friend request
                buckets[val] += 1
        prefix_count = 0
        for val, count in enumerate(buckets):
            if count > 0:
                vals.append(val)
                counts.append(count)
                prefix_counts.append(prefix_count)
                prefix_count += count  # exclusive prefix_count
        return vals, counts, prefix_counts


class FriendsOfAppropriateAgesImplBisect(FriendsOfAppropriateAges):
    """
    很难bug-free
    再简单的题也要列表推演确保一步到位
    """
    def num_friend_requests(self, ages):
        sorted_ages = sorted(ages)
        i, n, requests, insert_idx = 0, len(ages), 0, 0
        while i < n - 1:
            b = sorted_ages[i]
            if b < 15:
                i += 1      # 用的是while不是for, 千万不要忘了！
                continue

            # requests from & to same age people
            j = i + 1
            while j < n and sorted_ages[j] == b:
                j += 1
            requests += (j - i) * (j - i - 1)

            # requests from by elder people
            insert_idx = bisect_right(sorted_ages, 2 * b - 15, max(j - 1, insert_idx))
            requests += (insert_idx - j) * (j - i)

            i = j
        return requests


if __name__ == '__main__':
    ages = [16, 17, 18]
    sol = FriendsOfAppropriateAgesImplBisectWithBucketSorting()
    print sol.num_friend_requests(ages)

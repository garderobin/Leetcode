# coding=utf-8
from abc import ABCMeta, abstractmethod


class SumOfSubarrayMinimum:
    __metaclass__ = ABCMeta

    @abstractmethod
    def sum_subarray_mins(self, A):
        """
        :type A: List[int]
        :rtype: int
        """


class SumOfSubarrayMinimumImplIncreasingStack(SumOfSubarrayMinimum):
    """
    单调栈在处理某数左边有多少个比他大的， 右边有多少比他小的这方面优势独特
    看到区间极值就应该想到单调栈
    """
    def sum_subarray_mins(self, A):
        n, mod = len(A), 10 ** 9 + 7
        left, right, s1, s2 = [0] * n, [0] * n, [], []
        for i in range(n):
            count = 1
            while s1 and s1[-1][0] > A[i]:
                count += s1.pop()[1]
            left[i] = count
            s1.append([A[i], count])
        for i in range(n)[::-1]:
            count = 1
            while s2 and s2[-1][0] >= A[i]:
                count += s2.pop()[1]
            right[i] = count
            s2.append([A[i], count])
        return sum(a * l * r for a, l, r in zip(A, left, right)) % mod


class SumOfSubarrayMinimumImplNaiveTwoPointers(SumOfSubarrayMinimum):

    def sum_subarray_mins(self, A):
        if not A:
            return 0
        n = len(A)
        result = 0
        for i, a in enumerate(A):
            left, right = i - 1, i + 1
            while left > -1 and A[left] >= a:
                left -= 1
            while right < n and A[right] > a:
                right += 1
            result += a * (i - left) * (right - i)
        return result % 1000000007


if __name__ == '__main__':
    A = [3, 1, 2, 4]
    sol = SumOfSubarrayMinimumImplIncreasingStack()
    print sol.sum_subarray_mins(A)
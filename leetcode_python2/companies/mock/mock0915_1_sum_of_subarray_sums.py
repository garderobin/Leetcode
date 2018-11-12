from abc import ABCMeta, abstractmethod


class SumOfAllSubarraySums:
    __metaclass__ = ABCMeta

    @abstractmethod
    def sum_of_all_subarray_sums(self, nums):
        """
        :param nums: List[int]
        :return: int
        """


class SumOfAllSubarraySumsImplSlidingWindow(SumOfAllSubarraySums):
    """
    Time complexity: O(N^2)
    Space complexity: O(1)
    """
    def sum_of_all_subarray_sums(self, nums):
        if not nums:
            return 0
        n = len(nums)

        result = 0
        for size in xrange(1, n + 1):
            window_sum = sum(nums[:size])
            result += window_sum

            for remove in xrange(n - size):
                window_sum += nums[remove + size] - nums[remove]
                result += window_sum
        return result


class SumOfAllSubarraySumsImplCounter(SumOfAllSubarraySums):
    """
    Time complexity: O(N)
    Space complexity: O(1)
    """
    def sum_of_all_subarray_sums(self, nums):
        if not nums:
            return 0
        n = len(nums)
        return sum(num * (index + 1) * (n - index) for index, num in enumerate(nums))


class TestCase(object):
    def __init__(self, input_data, expected_output):
        self.input_data = input_data
        self.expected_output = expected_output

    def __str__(self):
        return '\tInput: %s\n\tExpected Output: %s' % (
            str(self.input_data),
            str(self.expected_output),
        )


if __name__ == '__main__':
    test_cases = [
        TestCase(input_data=[3, 1, 2, 4], expected_output=46),
        TestCase(input_data=[], expected_output=0),
        TestCase(input_data=[1, 1, 1], expected_output=10)
    ]
    solutions = [
        SumOfAllSubarraySumsImplSlidingWindow(),
        SumOfAllSubarraySumsImplCounter()
    ]
    for sol in solutions:
        print 'test solution: ', type(sol)
        all_tests_passed = True
        for test in test_cases:
            actual_output = sol.sum_of_all_subarray_sums(test.input_data)
            if actual_output != test.expected_output:
                print 'Fail test case: \n%s\n\tActual Output:%s' % (str(test), actual_output)
                all_tests_passed = False
        if all_tests_passed:
            print '^_^ All test passed for this solution.'

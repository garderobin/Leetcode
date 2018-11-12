class Solution(object):
    def next_greater_element(self, findNums, nums):
        """
        :type findNums: List[int]
        :type nums: List[int]
        :rtype: List[int]
        """
        if not findNums or not nums:
            return []

        next_great = {}
        stack = []
        for num in nums:
            while stack and stack[-1] < num:
                next_great[stack.pop()] = num
            stack.append(num)

        return [next_great.get(f, -1) for f in findNums]


class Exercise(object):
    def greater_elements_counts(self, findNums, nums):
        """
        :type findNums: List[int]
        :type nums: List[int]
        :rtype: List[int]
        """
        if not findNums or not nums:
            return []

        greater_counts = {}
        stack = []
        for num in reversed(nums):
            while stack and stack[-1] > num:
                greater_counts[num] = greater_counts.get(num, 0) + greater_counts.get(stack.pop(), 0) + 1
            stack.append(num)

        greater_counts[nums[-1]] = -1
        return [greater_counts.get(f, -1) for f in findNums]

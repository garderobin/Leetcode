from abc import ABCMeta, abstractmethod


class SortColors:
    __metaclass__ = ABCMeta

    @abstractmethod
    def sort_colors(self, nums):
        """
        :type nums: List[int]
        :rtype: void Do not return anything, modify nums in-place instead.
        """


class SortColorsTwoPointers(SortColors):

    def sort_colors(self, nums):
        i = j = 0
        for k in xrange(len(nums)):
            v = nums[k]
            nums[k] = 2
            if v < 2:
                nums[j] = 1
                j += 1
            if v == 0:
                nums[i] = 0
                i += 1


class SortColorsFourPointers(SortColors):
    """
    Wrong
    """
    def sort_colors(self, nums):
        if not nums:
            return
        n = len(nums)
        p = [0, n // 2, (n // 2) + 1, n - 1]
        i, j = 0, -1
        while p[0] < p[1] or p[2] < p[3]:
            val = self.get_value(nums, p[i])
            if val == 2:
                j = 3
            elif val == 1:
                j = 1 if i < 2 else 2
            else:
                j = 0

            if i != j:
                self.swap(nums, p[i], p[j])
                i = j
            else:
                if i % 2:
                    p[i] -= 1
                else:
                    p[i] += 1

        return

    def swap(self, nums, idx1, idx2):
        temp1, temp2 = self.get_value(nums, idx1), self.get_value(nums, idx2)
        if 0 <= idx1 < len(nums):
            nums[idx1] = temp2
        if 0 <= idx2 < len(nums):
            nums[idx2] = temp1

    def get_value(self, nums, idx):
        if idx < 0:
            return 0
        elif idx >= len(nums):
            return 2
        else:
            return nums[idx]


if __name__ == '__main__':
    test = [2,0,2,1,1,0]
    sol = SortColorsTwoPointers()
    print sol.sort_colors(test)

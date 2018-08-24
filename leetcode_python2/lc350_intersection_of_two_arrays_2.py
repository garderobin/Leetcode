from abc import ABCMeta, abstractmethod
from collections import Counter


class IntersectionOfTwoArrays2:
    __metaclass__ = ABCMeta

    @abstractmethod
    def intersect(self, nums1, nums2):
        """
        :type nums1: List[int]
        :type nums2: List[int]
        :rtype: List[int]
        """


class IntersectionOfTwoArrays2BinarySearch(IntersectionOfTwoArrays2):
    """
     # If the given array is already sorted, we could use a two
        # pointer approach where we have one pointer per array
        # and comparing values at each pointer until one pointer
        # reaches the end of one array.

        # If elements of num2 are stored on disk, and the memory
        # is limited such that you cannot load all elements
        # into the memory at once, then we external sort both
        # arrays and use the two pointer approach

        # The below approach (binary search) is used if num1's size
        # is small compared to num2's size
    """
    def intersect(self, nums1, nums2):
        shorter, longer = (sorted(nums1), sorted(nums2)) if len(nums1) < len(nums2) else (sorted(nums1), sorted(nums2))
        start, end = 0, len(longer) - 1
        i, j = 0, 0
        intersection = []
        while i < len(shorter) and j < len(longer):
            j = self.find_first_occurrence(longer, shorter[i], start, end)
            if j == -1:
                i += 1
                continue
            while i < len(shorter) and j < len(longer) and shorter[i] == longer[j]:
                intersection.append(shorter[i])
                i += 1
                j += 1
            end = j
        return intersection

    @staticmethod
    def find_first_occurrence(arr, num, start, end):
        while start < end:
            mid = (start + end) // 2
            if arr[mid] < num:
                start = mid + 1
            else:
                end = mid
        return -1 if arr[end] != num else end


class IntersectionOfTwoArrays2ImplCounter(IntersectionOfTwoArrays2):
    def intersect(self, nums1, nums2):
        """
        :type nums1: List[int]
        :type nums2: List[int]
        :rtype: List[int]
        """
        count_map_1 = dict(Counter(nums1).items())
        intersection = []

        for element, count in Counter(nums2).iteritems():
            if element in count_map_1:
                for _ in xrange(min(count_map_1[element], count)):
                    intersection.append(element)
        return intersection

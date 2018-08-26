class IntersectionOfTwoArrays(object):
    def intersection(self, nums1, nums2):
        """
        :type nums1: List[int]
        :type nums2: List[int]
        :rtype: List[int]
        """
        if not nums1 or not nums2:
            return []
        set1 = set(nums1)
        intersection = set()
        for num in nums2:
            if num in set1:
                intersection.add(num)
        return list(intersection)

class FindLengthOfLCIS(object):
    def find_length_of_lcis(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        prev_elem = -2147483648
        cur_lcis_len, max_lcis_len = 0, 0
        for num in nums:
            if num > prev_elem:
                cur_lcis_len += 1
                max_lcis_len = max(max_lcis_len, cur_lcis_len)
            else:
                cur_lcis_len = 1
            prev_elem = num
        return max_lcis_len

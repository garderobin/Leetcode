class FindLengthOfLCIS(object):
    def find_length_of_lcis(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        prev_elem = -2147483648
        cur_lcis_len, cur_lcds_len, max_res = 0, 0, 0
        for num in nums:
            if num > prev_elem:
                cur_lcis_len += 1
                cur_lcds_len = 1
                max_res = max(max_res, cur_lcis_len)
            elif num < prev_elem:
                cur_lcis_len = 1
                cur_lcds_len += 1
                max_res = max(max_res, cur_lcds_len)
            else:
                cur_lcis_len = 1
                cur_lcds_len = 1
            prev_elem = num
        return max_res

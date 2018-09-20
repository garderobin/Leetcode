class Solution(object):
    def max_chunks_to_sorted(self, arr):
        """
        :type arr: List[int]
        :rtype: int
        """
        cur_max, chunks = -1, 0
        for i, val in enumerate(arr):
            cur_max = max(cur_max, val)
            if cur_max == i:
                chunks += 1
        return chunks

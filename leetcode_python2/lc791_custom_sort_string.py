class Solution(object):
    def customSortString(self, S, T):
        """
        :type S: str
        :type T: str
        :rtype: str
        """
        if not S:
            return T

        order_dict = {c: i + 1 for i, c in enumerate(S)}
        return ''.join(sorted(T, key=lambda c: order_dict.get(c, 0)))

class Solution(object):
    """
    TLE
    """
    def __init__(self):
        self.invalid_prefix = set([])
        self.result = []

    def beautifulArray(self, N):
        """
        :type N: int
        :rtype: List[int]
        """
        self.backtrack(N, [], set([]), set(range(N, 0, -1)))
        self.invalid_prefix = set([])
        return self.result

    def backtrack(self, N, path, used_set, candidates):
        if len(path) == N:
            self.result = path
            return True

        for c in candidates:
            booms = set(c + c - u for u in used_set) & candidates
            if not booms:
                path.append(c)
                used_set.add(c)
                candidates.remove(c)

                if self.backtrack(N, path, used_set, candidates):
                    return True

                path.pop()
                used_set.remove(c)
                candidates.add(c)
        return False


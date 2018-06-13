from abc import ABCMeta, abstractmethod


class PredictTheWinner:
    __metaclass__ = ABCMeta

    @abstractmethod
    def first_player_can_win(self, nums):
        """
        :type nums: List[int]
        :rtype: bool
        """


class PredictTheWinnerImpl2(PredictTheWinner):
    """
    Best of all with DP and an odd situation trick.
    Time: O(N^2)
    Space: O(N)
    """
    def first_player_can_win(self, nums):
        if not nums:
            return False

        n = len(nums)
        if not n % 2:
            return True

        scores, sums = list(nums), list(nums)

        for width in xrange(1, n):
            for i in xrange(n - width):
                sums[i] = sums[i] + nums[i + width]
                scores[i] = sums[i] - min(scores[i + 1], scores[i])

        return scores[0] * 2 >= sums[0]


class PredictTheWinnerImpl(PredictTheWinner):
    # for 0 <= i < j < len(nums),
    # s[i][j] is the max score that the first player on this round can get with the current array is
    # the [i, j] sub-array of the initial input
    # s[i][j] = sum[i][j] - min(s[i+1][j], s[i][j-1])
    # for 0 < i < n, 0 <= w < n, g[i][w] = s[i][i+w]
    # g[i][w] = sum[i][i+w] - min(g[i+1][m-1], g[i][m-1])
    def first_player_can_win(self, nums):
        if not nums:
            return False

        n = len(nums)
        if not n % 2:
            return True

        sums, score = [0 for _ in xrange(n)], [0 for _ in xrange(n)]

        for i in xrange(n):
            score[i], sums[i] = nums[i], nums[i]

        for width in xrange(1, n):
            for i in xrange(n - width):
                sums[i] = sums[i] + nums[i + width]
                score[i] = sums[i] - min(score[i + 1], score[i])

        return score[0] * 2 >= sums[0]



if __name__ == "__main__":
    # sol = TheMazeDFSImpl2()
    sol = PredictTheWinnerImpl()
    maze = [[0, 0, 1, 0, 0], [0, 0, 0, 0, 0], [0, 0, 0, 1, 0], [1, 1, 0, 1, 1], [0, 0, 0, 0, 0]]
    start, destination = [0, 4], [4, 4]
    res = sol.has_path(maze, start, destination)
    print res


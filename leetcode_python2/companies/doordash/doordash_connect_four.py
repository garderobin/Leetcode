from abc import ABCMeta, abstractmethod


class ConnectFour:
    __metaclass__ = ABCMeta

    @abstractmethod
    def check_win(self, move):
        """
        :return: bool
        """

    @abstractmethod
    def best_move(self):
        """
        :return: (int, int): the position of the best next move
        """


class ConnectFourImpl(ConnectFour):
    def __init__(self, row_count, col_count):
        self.matrix = [['0' for _ in xrange(col_count)] for _ in xrange(row_count)]
        self.row_count = row_count
        self.col_count = col_count
        # put 'A' for player A and 'B' for player B on a cell on move

    def check_win(self, move):
        x, y, player = move

        def check_win_on_a_row():
            conn = 1
            for c in xrange(y - 1, min(0, y - 4), -1):
                if self.matrix[x][c] == player:
                    conn += 1
                else:
                    break

            for c in xrange(y + 1, min(self.col_count, y + 4)):
                if self.matrix[x][c] == player:
                    conn += 1
                else:
                    break

            return conn >= 4

        def check_win_on_a_col():
            conn = 1
            for r in xrange(x - 1, min(0, x - 4), -1):
                if self.matrix[r][y] == player:
                    conn += 1
                else:
                    break

            for r in xrange(x + 1, min(self.row_count, x + 4)):
                if self.matrix[r][y] == player:
                    conn += 1
                else:
                    break

            return conn >= 4

        def check_win_on_a_left_up_crossing():      # check 4 in "\"
            conn = 1
            for up in xrange(min(4, x + 1, y + 1)):
                if self.matrix[x - up][y - up] == player:
                    conn += 1
                else:
                    break

            for down in xrange(min(4, self.row_count - x, self.col_count - y)):
                if self.matrix[x + down][y + down] == player:
                    conn += 1
                else:
                    break

            return conn >= 4

        # check win on a / crossing
        def check_win_on_a_right_up_crossing():     # check 4 in "/"
            conn = 1
            for up in xrange(min(4, x + 1, self.col_count - y)):
                if self.matrix[x - up][y + up] == player:
                    conn += 1
                else:
                    break

            for down in xrange(min(4, self.row_count - x, y + 1)):
                if self.matrix[x + down][y - down] == player:
                    conn += 1
                else:
                    break

            return conn >= 4

        if check_win_on_a_row() or check_win_on_a_col():
            return True
        if check_win_on_a_left_up_crossing() or check_win_on_a_right_up_crossing():
            return True
        return False

    def best_move(self):
        pass

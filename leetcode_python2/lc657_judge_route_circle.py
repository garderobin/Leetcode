from abc import ABCMeta, abstractmethod


class JudgeRouteCircle:
    __metaclass__ = ABCMeta

    @abstractmethod
    def judge_circle(self, moves):
        """
        https://leetcode.com/problems/judge-route-circle/description/
        :type moves: str
        :rtype: bool
        """


class JudgeRouteCircleImplementation2(JudgeRouteCircle):
    def judge_circle(self, moves):
        return moves.count('L') == moves.count('R') and moves.count('U') == moves.count('D')


class MoveOnBoard:
    def __init__(self, horizontal_distance, vertical_distance):
        self.horizontal_distance = horizontal_distance
        self.vertical_distance = vertical_distance


class Position:
    def __init__(self, horizontal_position, vertical_position):
        self.horizontal_position = horizontal_position
        self.vertical_position = vertical_position

    def __eq__(self, other):
        return other and self.horizontal_position == other.horizontal_position \
               and self.vertical_position == other.vertical_position


class JudgeRouteCircleImplementation1(JudgeRouteCircle):
    MOVE_DIRECTION_ON_CHAR = {
        'U': MoveOnBoard(horizontal_distance=0, vertical_distance=-1),
        'D': MoveOnBoard(horizontal_distance=0, vertical_distance=1),
        'L': MoveOnBoard(horizontal_distance=-1, vertical_distance=0),
        'R': MoveOnBoard(horizontal_distance=1, vertical_distance=0)
    }

    def judge_circle(self, moves):
        """
        Only accepts those valid input move chars, case sensitive.
        Do nothing on invalid chars
        :param moves:
        :return:
        """
        start_position = Position(0, 0)
        current_position = start_position
        for move in moves:
            if self.is_valid_move_command(move):
                current_position = self.move_to_new_position(current_position=current_position,
                                                             move_on_board=self.MOVE_DIRECTION_ON_CHAR[move])
            else:
                print 'Invalid input for move: ', move

        return current_position == start_position

    def is_valid_move_command(self, char):
        return char in self.MOVE_DIRECTION_ON_CHAR

    def move_to_new_position(self, current_position, move_on_board):
        return Position(horizontal_position=(current_position.horizontal_position + move_on_board.horizontal_distance),
                        vertical_position=(current_position.vertical_position + move_on_board.vertical_distance))


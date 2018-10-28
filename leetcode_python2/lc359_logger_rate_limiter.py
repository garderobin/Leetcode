# coding=utf-8
class Logger(object):
    """
    注意判断条件是 record < timestamp - 9 而不是record < timestamp - 10
    """
    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.last_print_timestamps = {}

    def shouldPrintMessage(self, timestamp, message):
        """
        Returns true if the message should be printed in the given timestamp, otherwise returns false.
        If this method returns false, the message will not be printed.
        The timestamp is in seconds granularity.
        :type timestamp: int
        :type message: str
        :rtype: bool
        """
        if message not in self.last_print_timestamps or self.last_print_timestamps[message] < timestamp - 9:
            self.last_print_timestamps[message] = timestamp
            return True
        else:
            return False


# Your Logger object will be instantiated and called as such:
# obj = Logger()
# param_1 = obj.shouldPrintMessage(timestamp,message)

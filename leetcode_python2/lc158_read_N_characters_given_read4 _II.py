from abc import ABCMeta, abstractmethod


def read4(destination):
    return len(destination)  # nonsense


class ReadNCharactersGivenRead4CallMultipleTimes:
    __metaclass__ = ABCMeta

    @abstractmethod
    def read(self, buf, n):
        """
        :type buf: Destination buffer (List[str])
        :type n: Maximum number of characters to read (int)
        :rtype: The number of characters read (int)
        """


class ReadNCharactersGivenRead4CallMultipleTimesImpl(ReadNCharactersGivenRead4CallMultipleTimes):
    def __init__(self):
        self.buffer = [''] * 4
        self.head = 0
        self.tail = 0

    def read(self, buf, n):
        number_of_chars_read = 0
        while number_of_chars_read < n:
            if self.buffer_queue_is_empty():
                self.head = 0
                self.tail = read4(self.buffer)
                if self.tail == 0:
                    break
            while number_of_chars_read < n and not self.buffer_queue_is_empty():
                buf[number_of_chars_read] = self.buffer[self.head]
                number_of_chars_read += 1
                self.head += 1
        return number_of_chars_read

    def buffer_queue_is_empty(self):
        return self.head == self.tail

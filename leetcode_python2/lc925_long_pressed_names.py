from abc import ABCMeta, abstractmethod


class LongPressedName:
    __metaclass__ = ABCMeta

    @abstractmethod
    def isLongPressedName(self, name, typed):
        """
        :type name: str
        :type typed: str
        :rtype: bool
        """


class LongPressedNameImplTwoPointers(LongPressedName):
    def isLongPressedName(self, name, typed):
        if not name:
            return True

        if (not typed) or (len(typed) < len(name)):
            return False

        repeat1, repeat2 = 1, 0
        j, lt = 0, len(typed)
        for i, c1 in enumerate(name):
            if i == 0 or c1 != name[i - 1]:
                repeat1, repeat2 = 1, 0
                while j < lt and typed[j] == c1:
                    repeat2 += 1
                    j += 1
            else:
                repeat1 += 1

            if repeat2 < repeat1:
                return False

        return True

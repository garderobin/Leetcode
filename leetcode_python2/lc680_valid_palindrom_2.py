from abc import ABCMeta, abstractmethod


class ValidPalindrom2:
    __metaclass__ = ABCMeta

    @abstractmethod
    def valid_palindrome(self, s):
        """
        :type s: str
        :rtype: bool
        """


class ValidPalindrom2ImplFromEdgeToCenter(ValidPalindrom2):
    def valid_palindrome(self, s):
        return self.valid_palindrom_helper(s, 0, len(s) - 1, True)

    def valid_palindrom_helper(self, s, start, end, allow_delete):
        if not s:
            return True
        left, right = start, end
        while left <= right:
            if s[left] != s[right]:
                if allow_delete:
                    return (left < len(s) - 1 and s[left + 1] == s[right] and
                            self.valid_palindrom_helper(s, left+1, right, False)) or \
                           (right > 0 and s[left] == s[right - 1] and
                            self.valid_palindrom_helper(s, left, right-1, False))
                else:
                    return False
            else:
                left += 1
                right -= 1
        return True

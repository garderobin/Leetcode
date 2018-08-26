from abc import ABCMeta, abstractmethod


class EncodeAndDecodeStrings:
    __metaclass__ = ABCMeta

    @abstractmethod
    def encode(self, strs):
        """Encodes a list of strings to a single string.

        :type strs: List[str]
        :rtype: str
        """

    @abstractmethod
    def decode(self, s):
        """Decodes a single string to a list of strings.

        :type s: str
        :rtype: List[str]
        """


class EncodeAndDecodeStringsImpl(EncodeAndDecodeStrings):
    ESCAPE_CHAR, CONNECTOR_SUFFIX = ':', ':;'

    def encode(self, strs):
        encoded_char_list = []

        for s in strs:
            for c in s:
                encoded_char_list.append(c)
                if c == self.ESCAPE_CHAR:
                    encoded_char_list.append(self.ESCAPE_CHAR)
            encoded_char_list.append(self.CONNECTOR_SUFFIX)
        return ''.join(encoded_char_list)

    def decode(self, s):
        i, n = 0, len(s)
        decodes_strings = []
        cur_decoding_char_list = []
        while i < n:
            if s[i] == self.ESCAPE_CHAR:
                if s[i+1] == self.ESCAPE_CHAR:
                    cur_decoding_char_list.append(self.ESCAPE_CHAR)
                    i += 2
                else:
                    decodes_strings.append(''.join(cur_decoding_char_list))
                    cur_decoding_char_list = []
                    i += 2
            else:
                cur_decoding_char_list.append(self.ESCAPE_CHAR)
                i += 1
        return decodes_strings

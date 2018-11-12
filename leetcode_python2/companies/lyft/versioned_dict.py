from abc import ABCMeta, abstractmethod


class VersionedDict:
    __metaclass__ = ABCMeta

    @abstractmethod
    def set(self, k, v):
        """
        :param k: str
        :param v: object
        :return: void
        """

    @abstractmethod
    def get(self, k):
        """
        :param k: str
        :return: object
        """

    @abstractmethod
    def getValueWithVersion(self, k, version):
        """
        :param k: str
        :param version: int
        :return: object
        """


class VersionedDictImpl(VersionedDict):
    def __init__(self):
        self.store = {}

    def set(self, k, value):
        if k in self.store:
            self.store[k].append(value)
        else:
            self.store[k] = [value]

    def get(self, k):
        if k in self.store and len(self.store[k]) > 0:
            return self.store[k][-1]
        else:
            raise KeyError

    def getValueWithVersion(self, k, version):
        if k in self.store and len(self.store[k]) <= version:
            return self.store[k][version]
        else:
            raise KeyError

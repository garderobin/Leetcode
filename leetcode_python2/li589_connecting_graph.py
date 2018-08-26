class ConnectingGraph:
    """
    @param: n: An integer
    """
    def __init__(self, n):
        self.father = [x for x in xrange(n+1)]

    """
    @param: a: An integer
    @param: b: An integer
    @return: nothing
    """
    def connect(self, a, b):
        self.father[self.find(a)] = self.find(b)

    """
    @param: a: An integer
    @param: b: An integer
    @return: A boolean
    """
    def query(self, a, b):
        return self.find(a) == self.find(b)

    def find(self, a):
        path = []
        node = a
        while node != self.father[node]:
            path.append(node)
            node = self.father[node]
        for compress in path:
            self.father[compress] = node
        return node

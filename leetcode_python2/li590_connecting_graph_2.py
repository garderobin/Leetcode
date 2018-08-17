# coding=utf-8
class ConnectingGraph2:
    """
    @param: n: An integer
    """
    def __init__(self, n):
        self.father = [x for x in xrange(n+1)]
        self.component_size = [0 for _ in xrange(n+1)]

    """
    @param: a: An integer
    @param: b: An integer
    @return: nothing
    """
    def connect(self, a, b):
        component_a, component_b = self.find(a), self.find(b)
        if component_a != component_b:
            self.father[component_a] = component_b
            self.component_size[component_b] += self.component_size[component_a]
            self.component_size[component_a] = self.component_size[component_b]

    """
    @param: a: An integer
    @return: An integer
    """
    def query(self, a):
        return self.component_size[self.find(a)]

    def find(self, a):
        path = []
        node = a
        while node != self.father[node]:
            path.append(node)
            node = self.father[node]
        for compress in path:
            self.father[compress] = node
        # 绝对不能在find的时候加component size!
        return node

# coding=utf-8
class ConnectingGraph3:
    """
    @param a: An integer
    @param b: An integer
    @return: nothing
    """

    def __init__(self, n):
        self.father = [x for x in xrange(n + 1)]  # 这里绝对不能自作聪明地写成[x for x in xrange(1, n+1)]
        self.components = set(self.father[1:])

    def connect(self, a, b):
        component_a, component_b = self.find(a), self.find(b)
        if component_a != component_b:
            self.father[component_a] = component_b
            self.components.remove(component_a)
            self.components.add(component_b)

    """
    @return: An integer
    """
    def query(self):
        return len(self.components)

    def find(self, a):
        path = []
        node = a
        while node != self.father[node]:
            path.append(node)
            node = self.father[node]
        for compress in path:
            self.father[compress] = node
        return node

from abc import ABCMeta, abstractmethod


class MinimumSpanningTree(object):
    __metaclass__ = ABCMeta

    @abstractmethod
    def lowest_cost(self, connections):
        """
        :param connections: {Connection[]} connections given a list of connections, each includes two cities and cost
        :return: a list of connections from results
        """


class MinimumSpanningTreeImplKruskal(MinimumSpanningTree):
    def __init__(self):
        self.father = dict()
        self.components = set([])

    def lowest_cost(self, connections):
        self.init_components(connections)

        minimum_spanning_tree = []
        for conn in sorted(connections, key=lambda c: (c.cost, c.city1, c.city2)):
            if self.connect(conn):
                minimum_spanning_tree.append(conn)
            if len(self.components) == 1:
                return minimum_spanning_tree
        return []

    def init_components(self, connections):
        for conn in connections:
            self.father[conn.city1] = conn.city1
            self.components.add(conn.city1)
            self.father[conn.city2] = conn.city2
            self.components.add(conn.city2)

    def connect(self, connection):
        component1, component2 = self.find(connection.city1), self.find(connection.city2)
        if component1 == component2:
            return False
        else:
            self.father[component1] = component2
            self.components.remove(component1)
            self.components.add(component2)
            return True

    def find(self, v):
        path = []
        node = v
        while node != self.father[node]:
            path.append(node)
            node = self.father[node]
        for compress in path:
            self.father[compress] = node
        return node


class Connection:
    def __init__(self, city1, city2, cost):
        self.city1, self.city2, self.cost = city1, city2, cost

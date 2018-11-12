# coding=utf-8
from abc import ABCMeta, abstractmethod
from collections import deque, defaultdict


class BusRoutes:
    __metaclass__ = ABCMeta

    @abstractmethod
    def numBusesToDestination(self, routes, S, T):
        """
        :type routes: List[List[int]]
        :type S: int
        :type T: int
        :rtype: int
        """


class BusRoutesImplBFS2(BusRoutes):
    """
    想法：用bus_to_buses来压缩BFS
    TODO: 复习那种需要记录路径的BFS， 有环有向图 单源最短路径
    """
    def __init__(self):
        self.stop_to_buses, self.bus_to_stops, self.bus_to_buses = {}, {}, {}

    def numBusesToDestination(self, routes, S, T):
        if S == T:
            return 0
        self.build_graph(routes)
        return self.min_path_len(S, T)

    def build_graph(self, routes):
        """
        Time: linear of routes
        """
        self.bus_to_stops = [set(route) for route in routes]
        self.stop_to_buses = defaultdict(set)
        self.bus_to_buses = defaultdict(set)

        for bus, route in enumerate(routes):
            for stop in route:
                self.stop_to_buses[stop].add(bus)

        for bus, route in enumerate(routes):
            for stop in route:
                self.bus_to_buses[bus] |= self.stop_to_buses[stop]

    def min_path_len(self, S, T):
        """
        Time: O(number of buses)
        """
        q = deque([(bus, 1) for bus in self.stop_to_buses[S]])
        visited = set([])
        while q:
            bus, path_len = q.popleft()

            if T in self.bus_to_stops[bus]:
                return path_len

            visited.add(bus)
            for bus in (self.bus_to_buses[bus] - visited):
                q.append((bus, path_len + 1))
        return -1


class BusRoutesImplBFS(BusRoutes):
    def numBusesToDestination(self, routes, S, T):
        if S == T:
            return 0
        stop_to_buses, bus_to_stops = self.build_graph(routes)
        return self.min_path_len(stop_to_buses, bus_to_stops, S, T)

    def build_graph(self, routes):
        n = len(routes)
        bus_to_stops = [set(route) for route in routes]
        stop_to_buses = defaultdict(set)
        for bus, route in enumerate(routes):
            for stop in route:
                stop_to_buses[stop].add(bus)
        return stop_to_buses, bus_to_stops

    def min_path_len(self, stop_to_buses, bus_to_stops, S, T):
        q = deque([(bus, 1) for bus in stop_to_buses[S]])
        visited = set([])
        while q:
            bus, path_len = q.popleft()

            if T in bus_to_stops[bus]:
                return path_len

            visited.add(bus)
            for stop in bus_to_stops[bus]:
                for bus in (stop_to_buses[stop] - visited):
                    q.append((bus, path_len + 1))
        return -1

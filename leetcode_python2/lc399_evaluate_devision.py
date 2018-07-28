# coding=utf-8
from abc import ABCMeta, abstractmethod
from collections import defaultdict, deque


class EvaluateDivision:
    __metaclass__ = ABCMeta

    @abstractmethod
    def calc_equation(self, equations, values, queries):
        """
        :type equations: List[List[str]]
        :type values: List[float]
        :type queries: List[List[str]]
        :rtype: List[float]
        """


class DirectedEdge(object):
    def __init__(self, start, end, weight):
        self.start = start
        self.end = end
        self.weight = weight


class EvaluateDivisionImplAdjacentListDFS(EvaluateDivision):
    """
    Adjacent List + DFS
    Time: O(V + E)
    Space: O(bm) space complexity,
        where b is the branching factor of the tree and m is the maximum length of any path in the state space
    """

    def calc_equation(self, equations, values, queries):
        adj_lists = self.get_adjacent_lists(equations, values)
        return [self.calc_single_query(adj_lists, query) for query in queries]

    def get_adjacent_lists(self, equations, values):
        adj_lists = defaultdict(list)
        n = len(equations)
        for i in xrange(n):
            dividend, divisor, quotient = equations[i][0], equations[i][1], values[i]
            adj_lists[dividend].append(DirectedEdge(dividend, divisor, quotient))
            adj_lists[divisor].append(DirectedEdge(divisor, dividend, 1.0 / quotient))
        return adj_lists

    def calc_single_query(self, adj_lists, query):
        dividend, divisor = query
        connected, weight = self.find_path_weight(adj_lists, dividend, divisor)
        return weight if connected else -1.0

    def find_path_weight(self, adj_lists, start, end):
        """
        :return: (has_path_from_start_to_end, path_weight_sum)
        """
        return self.dfs(adj_lists, start, end, 1.0, set())

    def dfs(self, adj_lists, start, end, pre_weight, visited_set):
        if start in visited_set or start not in adj_lists:
            return False, pre_weight
        elif start == end:
            return True, pre_weight
        else:
            visited_set.add(start)  # 这一步我极其极其容易忘！！！
            for adj_edge in adj_lists[start]:
                connected, weight = self.dfs(adj_lists, adj_edge.end, end, pre_weight * adj_edge.weight, visited_set)
                # We are guaranteed there's no contradiction. So only one success is enough.
                if connected:
                    return True, weight  # 这里绝对不能把weight和参数穿进来的prev_weight使用同一名字!
            return False, pre_weight


class EvaluateDivisionImplAdjacentListBFS(EvaluateDivisionImplAdjacentListDFS):
    """
    Adjacent List + BFS
    Time: O(V + E)
    Space: O(V + E)
    """

    def find_path_weight(self, adj_lists, start, end):
        """
        :return: (has_path_from_start_to_end, path_weight_sum)
        """
        if start not in adj_lists or end not in adj_lists:
            return False, -1.0
        elif start == end:
            return True, 1.0
        else:
            visited, queue = set(), deque([(start, 1.0)])
            while queue:
                cur_start, weight = queue.popleft()  # tuple的顺序千万别错了
                visited.add(cur_start)
                for adj_edge in adj_lists[cur_start]:
                    if adj_edge.end in visited:  # never forget to check visited
                        continue
                    updated_weight = weight * adj_edge.weight
                    if adj_edge.end == end:
                        return True, updated_weight
                    else:
                        queue.append((adj_edge.end, updated_weight))  # tuple的顺序千万别错了

            return False, -1.0


class EvaluateDivisionImplFloydWarshall(EvaluateDivision):
    """
    Floyd-Warshall: finding shortest paths in a weighted graph with positive or negative edge weights
    (but with no negative cycles) 全源最短路径算法，前提是无负权边。
    http://www.cnblogs.com/gaochundong/p/floyd_warshall_algorithm.html
    """
    def calc_equation(self, equations, values, queries):
        quot = defaultdict(dict)
        for (num, den), val in zip(equations, values):  # 妙用zip, 不适合严格复杂度的面试
            quot[num][num] = quot[den][den] = 1.0
            quot[num][den] = val
            quot[den][num] = 1 / val
        for k in quot:
            for i in quot[k]:
                for j in quot[k]:
                    quot[i][j] = quot[i][k] * quot[k][j]
        return [quot[num].get(den, -1.0) for num, den in queries]


if __name__ == "__main__":
    solutions = [EvaluateDivisionImplAdjacentListDFS(), EvaluateDivisionImplAdjacentListBFS()]

    equations = [["a", "b"], ["b", "c"]]
    values = [2.0, 3.0]
    queries = [["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"]]
    # queries = [["x", "x"]]
    for sol in solutions:
        print sol.calc_equation(equations, values, queries)

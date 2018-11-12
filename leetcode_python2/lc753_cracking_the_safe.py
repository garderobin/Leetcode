# coding=utf-8
from abc import ABCMeta, abstractmethod


class CrackingTheSafe:
    __metaclass__ = ABCMeta

    @abstractmethod
    def crackSafe(self, n, k):
        """
        :type n: int
        :type k: int
        :rtype: str
        """


class CrackingTheSafeImplLoop(CrackingTheSafe):

    def crackSafe(self, n, k):
        s = '0' * (n - 1)
        seen = {s}
        candidate = map(str, range(k - 1, -1, -1))      # 反过来就不能成立了
        for _ in range(k ** n):
            last = s[- n + 1:] if n > 1 else ''
            for c in candidate:
                if last + c not in seen:
                    seen.add(last + c)
                    s += c
                    break
        return s


class CrackingTheSafeImplGreedyDFS3(CrackingTheSafe):
    """
    """
    def crackSafe(self, n, k):
        goal = k ** n
        path = ['0'] * n
        visited = {''.join(path)}
        candidates = map(str, range(k))

        def dfs():
            if len(visited) == goal:
                return ''.join(path)

            for c in candidates:
                key = ''.join((path[-(n - 1):] if n > 1 else []) + [c])
                if key in visited:
                    continue

                visited.add(key)
                path.append(c)
                dfs()

            return None

        return dfs()


class CrackingTheSafeImplGreedyDFS2(CrackingTheSafe):
    """
    TODO 由这道题复习DFS
    DFS的解法当中如何return, 如何通过add/delete 节省memory都很重要
    """
    def crackSafe(self, n, k):
        goal = k ** n
        path = ['0'] * n
        visited = {''.join(path)}
        candidates = map(str, range(k))

        def dfs():
            if len(visited) == goal:
                return ''.join(path)

            for c in candidates:
                key = ''.join((path[-(n - 1):] if n > 1 else []) + [c])
                if key in visited:
                    continue

                visited.add(key)
                path.append(c)

                res = dfs()
                if res:
                    return res

                path.pop()
                visited.remove(key)

            return None

        return dfs()


class CrackingTheSafeImplGreedyDFS(CrackingTheSafe):
    """
    Memory Limit Exceeded
    """
    def crackSafe(self, n, k):
        goal = k ** n

        def dfs(path, visited):
            if len(visited) == goal:
                return path

            for v in xrange(k):
                key = (path[-(n - 1):] if n > 1 else '') + str(v)
                if key in visited:
                    continue
                res = dfs(path + str(v), visited | {key})
                if res:
                    return res
            return None

        init_path = ''.join(['0'] * n)
        return dfs(init_path, {init_path})

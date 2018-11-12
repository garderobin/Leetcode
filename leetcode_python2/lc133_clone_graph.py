from collections import deque


class Solution:
    # @param node, a undirected graph node
    # @return a undirected graph node
    def cloneGraph(self, node):
        if not node:
            return None

        root = UndirectedGraphNode(node.label)
        q0, q1 = deque([node]), deque([root])

        visited_nodes = {root.label: root}
        while q0:
            node0 = q0.popleft()
            node1 = q1.popleft()
            for nb0 in node0.neighbors:
                if nb0.label == node0.label:
                    node1.neighbors.append(node1)
                else:
                    if nb0.label in visited_nodes:
                        nb1 = visited_nodes[nb0.label]
                    else:
                        nb1 = UndirectedGraphNode(nb0.label)
                        q0.append(nb0)
                        q1.append(nb1)
                    node1.neighbors.append(nb1)
                    visited_nodes[nb1.label] = nb1
        return root


# Definition for a undirected graph node
class UndirectedGraphNode:
    def __init__(self, x):
        self.label = x
        self.neighbors = []

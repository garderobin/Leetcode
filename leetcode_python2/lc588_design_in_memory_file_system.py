# https://leetcode.com/problems/design-in-memory-file-system/discuss/103334/Java-Solution-using-Trie.-No-need-to-sort.
from collections import defaultdict


class Node:
    #     int type = 0; // 1 - dir ; 2 - file
    #     StringBuilder content;
    #     Node [] children = new Node[27];
    # }
    def __init__(self):
        self.node_type = 0
        self.content_chars = []
        self.children = defaultdict(Node)


if __name__ == '__main__':
    root = Node()
    root.children['/'] = Node()
    print root.children

from collections import defaultdict


class TrieNode(object):
    def __init__(self, action, user_ids):
        self.action = action
        self.user_ids = user_ids
        self.children = {}


class Log(object):
    def __init__(self, user_id, timestamp, action):
        self.user_id = user_id
        self.timestamp = timestamp
        self.action = action


class Trie(object):
    def __init__(self):
        self.root = TrieNode('', [])

    def insert(self, logs):
        node = self.root
        for log in logs:
            if log.action in node.children:
                node.children[log.action].user_ids.append(log.user_id)
            else:
                node.children[log.action] = TrieNode(action=log.action, user_ids=[log.user_id])
            node = node.children[log.action]


class LogGraph(object):
    INDENT_PREFIX = '|    '
    NODE_PREFIX = '|---'

    def __init__(self, logs):
        self.user_actions = defaultdict(list)
        self.trie = Trie()

        for log in sorted(logs, key=lambda l: (l.user_id, l.timestamp)):
            self.user_actions[log.user_id].append(log)

        for user_id in sorted(self.user_actions.keys()):
            self.trie.insert(self.user_actions[user_id])

    def __str__(self):
        strings = []

        def dfs(root, tabs):
            strings.extend([self.INDENT_PREFIX] * tabs)
            strings.append('%s%s(%d)\n' % (self.NODE_PREFIX, root.action, len(root.user_ids)))
            for child in sorted(root.children.values(), key=lambda trie_node: trie_node.user_ids[0]):
                dfs(child, tabs + 1)

        for key, top in self.trie.root.children.iteritems():
            dfs(top, 0)

        return ''.join(strings)

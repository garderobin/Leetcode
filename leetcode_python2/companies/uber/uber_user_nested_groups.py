from abc import ABCMeta, abstractmethod
from collections import defaultdict, deque


class FindUserNestedGroups:
    __metaclass__ = ABCMeta

    @abstractmethod
    def find_user_nested_groups(self, group_info):
        """
        return the dict that all groups (including nested) that the user belongs to for each user.
        :param group_info: dict(dict)
        e.g.
        {
            'Group1': {
                'NestedGroups': ['Group3', 'Group4'],
                'Users': ['User2', 'User3']
            },

            'Group2: {
                'NestedGroups': ['Group1', 'Group4'],
                'Users': ['User7', 'User8']
            }
        }
        :return: dict(list)
        e.g.
        {
            'User2': ['Group1', 'Group2']
        }
        """


class FindUserNestedGroupsImplBFS(FindUserNestedGroups):
    def find_user_nested_groups(self, group_info):
        return self.bfs(self.build_graph(group_info))

    def build_graph(self, group_info):
        user_to_direct_groups = defaultdict(set)
        group_direct_parents = defaultdict(set)

        for group, info in group_info.iteritems():
            for child_group in info['NestedGroups']:
                group_direct_parents[child_group].add(group)
            for user in info['Users']:
                user_to_direct_groups[user].add(group)

        return [user_to_direct_groups, group_direct_parents]

    def bfs(self, graph):
        user_to_direct_groups, group_direct_parents = graph

        group_all_ancestors = defaultdict(set)
        for group, ancestors in group_direct_parents.iteritems():
            q = deque(ancestors)
            group_all_ancestors[group] = set([])
            while q:
                parent = q.popleft()
                if parent in group_all_ancestors:
                    continue
                group_all_ancestors[group].add(parent)
                q.extend(group_direct_parents[parent] - group_all_ancestors[group])

        user_to_all_groups = defaultdict(set)
        for user, direct_groups in user_to_direct_groups.iteritems():
            for direct_group in direct_groups:
                user_to_all_groups[user] |= group_all_ancestors[direct_group]

        return user_to_all_groups

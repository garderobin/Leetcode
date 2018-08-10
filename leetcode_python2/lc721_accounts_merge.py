from abc import ABCMeta, abstractmethod
from collections import defaultdict


class AccountsMerge:
    __metaclass__ = ABCMeta

    @abstractmethod
    def accounts_merge(self, accounts):
        """
        :type accounts: List[List[str]]
        :rtype: List[List[str]]
        """


class AccountsMergeImpl(AccountsMerge):
    def __init__(self):
        self.father = {}

    @staticmethod
    def get_email_to_ids(accounts):
        email_to_ids = defaultdict(list)
        for user_id, account in enumerate(accounts):
            for email in account[1:]:
                email_to_ids[email].append(user_id)
        return email_to_ids

    @staticmethod
    def get_merged_accounts(accounts, root_id_to_distinct_emails):
        merged_accounts = []
        for user_id, emails in root_id_to_distinct_emails.items():
            merged_accounts.append([accounts[user_id][0]] + sorted(emails))
        return merged_accounts

    def accounts_merge(self, accounts):
        """
        :type accounts: List[List[str]]
        :rtype: List[List[str]]
        """
        self.initialize(len(accounts))
        email_to_ids = self.get_email_to_ids(accounts)
        self.connect_ids_for_each_email(email_to_ids)
        root_id_to_distinct_emails = self.get_root_id_to_distinct_emails(accounts)
        return self.get_merged_accounts(accounts, root_id_to_distinct_emails)

    def initialize(self, n):
        for i in xrange(n):
            self.father[i] = i

    def union(self, id1, id2):
        self.father[self.find(id1)] = self.find(id2)

    def connect_ids_for_each_email(self, email_to_ids):
        for email, ids in email_to_ids.items():
            root_id = ids[0]
            for user_id in ids[1:]:
                self.union(user_id, root_id)

    def get_root_id_to_distinct_emails(self, accounts):
        id_to_distinct_emails = defaultdict(set)
        for user_id, account in enumerate(accounts):
            root_id = self.find(user_id)
            for email in account[1:]:
                id_to_distinct_emails[root_id].add(email)
        return id_to_distinct_emails

    def find(self, user_id):
        path = []
        node = user_id
        while node != self.father[node]:
            path.append(node)
            node = self.father[node]
        for p in path:
            self.father[p] = node
        return node


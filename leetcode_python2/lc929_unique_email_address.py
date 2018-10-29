class Solution(object):
    """
    Time: O(N)
    Space: O(N)
    """
    def numUniqueEmails(self, emails):
        """
        :type emails: List[str]
        :rtype: int
        """
        unique_emails = set([])
        for email in emails:
            local, domain = email.split('@')
            unique_local = []
            for c in local:
                if c == '.':
                    continue
                if c == '+':
                    break
                unique_local.append(c)
            unique_emails.add('%s@%s' % (''.join(unique_local), domain))
        return len(unique_emails)

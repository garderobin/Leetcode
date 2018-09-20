# coding=utf-8
from abc import ABCMeta, abstractmethod
from collections import defaultdict, Counter


class LongestSubstringWithAtLeastKRepeatingCharacters:
    __metaclass__ = ABCMeta

    @abstractmethod
    def longest_substring(self, s, k):
        """
        :type s: str
        :type k: int
        :rtype: int
        """


class LongestSubstringWithAtLeastKRepeatingCharactersImpl(LongestSubstringWithAtLeastKRepeatingCharacters):

    def longest_substring(self, s, k):
        if not s or len(s) < k:
            return 0
        res = 0
        for allowed_unique in xrange(1, 27):
            n, i, unique, no_less_than_k = len(s), 0, 0, 0
            counts = defaultdict(int)
            for j, c in enumerate(s):
                while i < j and unique > allowed_unique or no_less_than_k > allowed_unique:
                    if counts[s[i]] == k:
                        no_less_than_k -= 1
                    counts[s[i]] -= 1
                    if counts[s[i]] == 0:
                        unique -= 1
                    i += 1

                    # if counts[s[j]] == 0:
                    #     unique += 1
                    # counts[s[j]] += 1
                    # if counts[s[j]] == k:
                    #     no_less_than_k += 1
                    # j += 1

                # if allowed_unique == 2:
                #     print allowed_unique, i, j, unique, no_less_than_k

                if unique == allowed_unique and no_less_than_k == allowed_unique:
                    res = max(res, j - i)

                # if counts[s[i]] == k:
                #     no_less_than_k -= 1
                # counts[s[i]] -= 1
                # if counts[s[i]] == 0:
                #     unique -= 1
                if counts[s[j]] == 0:
                    unique += 1
                counts[s[j]] += 1
                if counts[s[j]] == k:
                    no_less_than_k += 1
        return res


class LongestSubstringWithAtLeastKRepeatingCharactersImplTwoPointers(LongestSubstringWithAtLeastKRepeatingCharacters):
    """
    源自java 答案
    https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/discuss/87739/Java-Strict-O(N)-Two-Pointer-Solution
    1. 用26个lower letter的常数性质，构造的sliding window不是确切size的window, 而是内涵了多少个unique char的window
    2. 这个题即使用了26个lower letter的window, 用九章的模板也非常难套，因为动左或者动右都需要立刻检查边界条件，
    所以要被下来这个模板：
    while j < n:
        if window too large:
            i++ (缩减window)
        else:
            j++ (扩大window)
        if window meets requirement:
            比较最优解
    return 最优解
    3. 在比较最优解这一步， 是j - i 而不是j - i + 1, 因为本质是(j - 1) - i + 1, 这个最优区间是[i, j-1]
    """
    def longest_substring(self, s, k):
        if not s or len(s) < k:
            return 0
        res = 0
        for window_unique in xrange(1, 27):
            i, j, unique, no_less_than_k = 0, 0, 0, 0
            counts = defaultdict(int)
            while j < len(s):
                if unique > window_unique:
                    if counts[s[i]] == k:
                        no_less_than_k -= 1
                    counts[s[i]] -= 1
                    if counts[s[i]] == 0:
                        unique -= 1
                    i += 1
                else:
                    if counts[s[j]] == 0:
                        unique += 1
                    counts[s[j]] += 1
                    if counts[s[j]] == k:
                        no_less_than_k += 1
                    j += 1

                if unique == window_unique and no_less_than_k == window_unique:
                    res = max(res, j - i)
        return res

    def longest_substring_backup(self, s, k):
        if not s or len(s) < k:
            return 0
        res = 0
        for window_unique in xrange(1, 26):
            start, unique, no_less_than_k = 0, 0, 0
            counts = defaultdict(int)
            for end, end_char in enumerate(s):
                # if unique < window_unique:
                #     if counts[end_char] == 0:
                #         unique += 1
                #     counts[end_char] += 1
                #     if counts[end_char] == k:
                #         no_less_than_k += 1
                # else:
                #     if counts[s[start]] == k:
                #         no_less_than_k -= 1
                #     counts[s[start]] -= 1
                #     if counts[s[start]] == 0:
                #         unique -= 1
                #     start += 1
                while unique > window_unique:
                    if counts[s[start]] == k:
                        no_less_than_k -= 1
                    counts[s[start]] -= 1
                    if counts[s[start]] == 0:
                        unique -= 1
                    start += 1

                if counts[end_char] == 0:
                    unique += 1
                    counts[end_char] += 1
                if counts[end_char] == k:
                    no_less_than_k += 1

                if unique == window_unique and no_less_than_k == window_unique:
                    res = max(res, end - start + 1)
        return res


class LongestSubstringWithAtLeastKCharactersImplSlidingWindow(LongestSubstringWithAtLeastKRepeatingCharacters):
    """
    这道题想要O(N), 不能用九章的同向双指针模板，必须用sliding window。
    虽然他和Longest Substring with At Most K(Two) Distinct Characters 长的很像，
    但归根结底的区别在于，at most K distinct characters检查的是一种 类型的条件，可以dp化，
    而 longest substring with at least K repeating characters 检查的是for each element, 而外层结果是longest不是shortest
    因此无法dp化，
    必须在外面套一层sliding window.
    如果这个题改成shortest substring with at least K repeating characters,
    马上也能用九章同向双指针模板，因为边界条件变得可以DP化。
    所以面试遇到这样的题，还是应该仔细分析有没有不能使用同向双指针的地方，不能的话果断sliding window宁可加复杂度，也不能卡在一半。
    """
    def longest_substring(self, s, k):
        pass


class LongestSubstringWithAtLeastKCharactersImplBackup(LongestSubstringWithAtLeastKRepeatingCharacters):
    """
    都不work
    """
    def longest_substring(self, s, k):
        n = len(s)
        if k < 2:
            return n
        if k > n:
            return ''

        counter = Counter(s)
        if k > counter.most_common(1)[0][1]:
            return ''

        counter_items = counter.items()
        counts = dict(counter_items)
        unmatched_unique_chars = set(filter(lambda key: counts[key] < k, counter.iterkeys()))
        # sub_string_counter = defaultdict(int)
        # sub_string_counter[s[0]] = 1
        # unmatched_unique_chars = {s[0]}
        left, right = 0, n - 1
        res = n
        while left < right:
            while left < right and len(unmatched_unique_chars) > 0 and counts[s[left]] != k:
                counts[s[left]] -= 1
                if counts[s[left]] == 0:
                    unmatched_unique_chars.remove(s[left])
                left += 1

            while left < right and len(unmatched_unique_chars) > 0 and counts[s[right]] != k:
                counts[s[right]] -= 1
                if counts[s[right]] == 0:
                    unmatched_unique_chars.remove(s[right])
                right -= 1

            # Fix left, move right
            r = right
            new_unmatched_unique_chars = set(unmatched_unique_chars)
            while left < r and len(new_unmatched_unique_chars) > 0:
                counts[s[r]] -= 1
                if counts[s[r]] == 0:
                    new_unmatched_unique_chars.remove(s[r])
                elif counts[s[r]] == k-1:
                    new_unmatched_unique_chars.add(s[r])
                r -= 1

            if len(new_unmatched_unique_chars) == 0:
                return r - left + 1

            # Fix right, move left
            new_left = left
            new_unmatched_unique_chars = set(unmatched_unique_chars)
            while new_left < right and len(new_unmatched_unique_chars) > 0:
                counts[s[new_left]] -= 1
                if counts[s[new_left]] == 0:
                    new_unmatched_unique_chars.remove(s[new_left])
                elif counts[s[new_left]] == k-1:
                    new_unmatched_unique_chars.add(s[r])
                new_left += 1

            if len(new_unmatched_unique_chars) == 0:
                return right - new_left + 1

        # for left, left_char in enumerate(s):

            # while right > 0 and len(unmatched_unique_chars) > 0:
            #     sub_string_counter[s[right]] -= 1
            #     if sub_string_counter[s[right]] < k:
            #         unmatched_unique_chars.add(s[right])
            #     elif sub_string_counter[s[right]] == 0:
            #         unmatched_unique_chars.remove(s[right])
            #     right -= 1
            #
            # if len(unmatched_unique_chars) == 0:
            #     res = max(res, right - left + 1)
            #
            # sub_string_counter[left_char] -= 1
            # if sub_string_counter[left_char] < k:
            #     unmatched_unique_chars.add(left_char)
            # elif sub_string_counter[left_char] == 0:
            #     unmatched_unique_chars.remove(left_char)

        return res

    def remove_char(self, sub_string_counter, unmatched_unique_chars, char, k):
        sub_string_counter[char] -= 1
        if sub_string_counter[char] < k:
            unmatched_unique_chars.add(char)
        elif sub_string_counter[char] == 0:
            unmatched_unique_chars.remove(char)

    def longest_substring_draft(self, s, k):
        n = len(s)
        if k < 2:
            return n
        if k > n:
            return ''

        counter = Counter(s)
        if k > counter.most_common(1)[0][1]:
            return ''

        # counter_items = counter.items()
        # sub_string_counter = dict(counter_items)
        # unmatched_unique_chars = set(filter(lambda (char, count): count < k, counter_items))
        sub_string_counter = defaultdict(int)
        sub_string_counter[s[0]] = 1
        unmatched_unique_chars = {s[0]}
        right = 1
        res = 0
        for left, left_char in enumerate(s):
            while right < n and len(unmatched_unique_chars) > 0:
                sub_string_counter[s[right]] += 1
                if sub_string_counter[s[right]] == k:
                    unmatched_unique_chars.remove(s[right])
                elif sub_string_counter[s[right]] == 1:
                    unmatched_unique_chars.add(s[right])
                right += 1

            while right < n and sub_string_counter[s[right]] + 1 >= k:
                sub_string_counter[s[right]] += 1
                right += 1

            if len(unmatched_unique_chars) == 0:
                res = max(res, right - left)

            sub_string_counter[left_char] -= 1
            if sub_string_counter[left_char] == 0:
                sub_string_counter.pop(left_char)
            elif sub_string_counter[left_char] == k - 1:
                unmatched_unique_chars.add(left_char)

        return res

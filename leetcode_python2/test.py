# coding=utf-8

if __name__ == "__main__":
    s1 = 'abd'
    s2 = 'eidcabbdab'
    unique_chars_s2 = set(s2)
    window_char_counts = {}
    for c in s1:
        window_char_counts[c] = window_char_counts.get(c, 0) + 1
    window = dict(window_char_counts)
    window['a'] += 1
    print window_char_counts, window








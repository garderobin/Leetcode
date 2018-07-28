# coding=utf-8
from collections import deque, defaultdict, Counter

if __name__ == "__main__":
    # d = {
    #     'A': 3,
    #     'B': 1,
    #     'C': 2
    # }
    # for w in sorted(d, key=d.get, reverse=True):
    #     print w, d[w]
    #
    # print sorted(d, key=d.get, reverse=True)[-1]
    # counter = Counter(["A", "B", "B", "B", "B", "B", "B", "C", "C", "D", "E", "F", "G"])
    s = 'aaabbccdef'
    counts = Counter(s).items()
    valid = dict(map(lambda (char, count): (char, 0), counts))
    print valid
    # print 'counter', counter
    # print 'counter.items()', counter.items()
    # print 'counter.most_common()', counter.most_common()
    #
    # for k, v in counter.iteritems():
    #     print k, v
    #
    # iv = counter.itervalues()
    # first = iv.next()
    # print 'first', first
    #
    # for v in iv:
    #     print v

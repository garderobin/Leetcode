# coding=utf-8
from collections import deque, defaultdict

if __name__ == "__main__":
    res = [1, 2, 3, 4, 5, 6, 7]
    print res[::2]

    # PATTEN dcba...dcba dcb..dcb dc...dc
    # d-c <= (char_nums - 1) * count(a)? 第一组解决战斗
    # (char_nums - 1) * count(a) < d-c <= (char_nums - 1) * count(a) + (char_nums - 2) * (count(b) - count(a))? 第一组
    # 必须在PATTEN以前消化掉的d是d-c
    # 必须在dc...dc以前消化掉的d是c-b


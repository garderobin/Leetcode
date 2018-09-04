# coding=utf-8
from _bisect import bisect_right

if __name__ == "__main__":
    # arr = [20, 30, 45, 100, 110, 120]
    # insert_index = bisect_right(arr, 45, 1)
    arr = [54, 23, 102, 90, 40, 74, 112, 74, 76, 21]
    sorted_arr = sorted(arr)
    a_limit = map(lambda b: b + b - 15, sorted_arr)
    print sorted_arr
    print a_limit







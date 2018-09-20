# coding=utf-8
from abc import ABCMeta, abstractmethod


class Game24:
    __metaclass__ = ABCMeta

    @abstractmethod
    def judge_point_24(self, nums):
        """
        :type nums: List[int]
        :rtype: bool
        """


def calculate_two_nums(a, b):
    results = {a + b, a - b, b - a, a * b}
    if a != 0.0:
        results.add(b / a)
    if b != 0.0:
        results.add(a / b)
    results.remove(0.0)     # 原数组只能是1-9，如果有两个数字计算满足24， 另两个数字一模一样只需要先乘一个再除一个就好，不需造0
    return list(results)


def get_combinations(nums):
    combinations = []   # 如果内部用tuple (三元也可以）， 外部可以用set, 如果内部结构是list, list不能hash化！

    for i, num in enumerate(nums):
        combinations.append(([num], [nums[j] for j in xrange(len(nums)) if j != i]))    # 需要更好的划分partition的写法

    if len(nums) > 3:
        left, right = combinations[0]
        for i, num in enumerate(right):
            combinations.append(([left[0], num], [right[j] for j in xrange(len(right)) if j != i]))
    return combinations


def float_equals(fa, fb):
    return abs(fa - fb) < 1e-2      # 1e-3过不了, 精度问题，TODO 面试的时候我怎么知道改选1e-2还是1e-3? 很难想test case


class Game24ImplDFS(Game24):
    """
    已经能beat 95%了
    这里用的最重要的trick是:
    1. 从头到尾不需要0的存在，只要算出了零，马上排除出结果集
    2. 在前提1的基础上，expect_right = calculate_two_nums(left[0], target)
    TODO：这道题结束的很漂亮，但是值得复习，因为用到除法和组合，因此涉及了很多数学的trick，常见bug 以及基本运算的复习。
    一定要熟悉python语言是怎么处理这些问题的
    """
    def judge_point_24(self, nums):
        arr = [float(num) for num in nums]
        return self.can_get_points(arr, 24.0)

    def can_get_points(self, arr, target):
        if len(arr) == 1:
            return float_equals(arr[0], target)
        elif len(arr) == 2:
            for calc in calculate_two_nums(arr[0], arr[1]):
                if float_equals(calc, target):
                    return True
            return False
        else:
            for left, right in get_combinations(arr):
                if len(left) == 2:
                    for left_calc in calculate_two_nums(left[0], left[1]):
                        for expect_right in calculate_two_nums(left_calc, target):
                            if self.can_get_points(right, expect_right):    # expect_right 不能为零
                                return True
                else:
                    for expect_right in calculate_two_nums(left[0], target):
                        if self.can_get_points(right, expect_right):
                            return True
            return False


class Game24ImplMemoSearch(Game24):
    """
    memo search 于这道题并没有大益处，因为只有四张牌，可以cache的部分计算复杂度为0.
    只有当牌数扩展到N且N很大的时候，memo search才有价值。
    这题用DFS完全足够了，既能加快速度又不回写起来很复杂
    """
    def __init__(self):
        self.combination_cache = {}
        self.calculation_cache = {}

    def judge_point_24(self, nums):
        arr = map(float, nums)
        return self.can_get_points(arr, 24.0)

    def can_get_points(self, arr, target):
        if len(arr) == 1:
            return float_equals(arr[0], target)
        elif len(arr) == 2:
            for calc in self.calculate_two_nums(arr[0], arr[1]):
                if float_equals(calc, target):
                    return True
            return False
        else:
            for left, right in self.get_combinations(arr):
                if len(left) == 2:
                    for left_calc in self.calculate_two_nums(left[0], left[1]):
                        for expect_right in self.calculate_two_nums(left_calc, target):
                            if self.can_get_points(right, expect_right):    # expect_right 不能为零
                                return True
                else:
                    for expect_right in self.calculate_two_nums(left[0], target):
                        if self.can_get_points(right, expect_right):
                            return True
            return False

    def get_combinations(self, arr):
        if len(arr) == 2:
            key = sorted((arr[0], arr[1]))
            if key not in self.combination_cache:
                self.combination_cache[key] = get_combinations(key)
            return self.combination_cache[key]
        else:
            return get_combinations(arr)

    def calculate_two_nums(self, num1, num2):
        a, b = sorted((num1, num2))
        key = (a, b)
        if key not in self.calculation_cache:
            self.calculation_cache[key] = calculate_two_nums(a, b)
        return self.calculation_cache[key]


class Game24ImplNaive(Game24):
    """
    坑实在是太多了
    """
    def judge_point_24(self, nums):
        return 24.0 in self.calculate(nums)

    def calculate(self, nums):
        n = len(nums)
        points = set([])
        if n == 1:
            return {float(nums[0])}
        elif n < 5:
            for left, right in get_combinations(nums):
                for left_calc in list(self.calculate(left)):    # 这个函数返回的是set不是list, 时刻检查返回值
                    for right_calc in list(self.calculate(right)):
                        for calculation in calculate_two_nums(left_calc, right_calc):
                            if n == 4:
                                if abs(calculation - round(calculation)) < 1e-3:    # 小数误差的表示方法，这里引入了除法，所以比较多时候容易错
                                    points.add(round(calculation))
                            else:
                                points.add(calculation)

            return points
        else:
            return points

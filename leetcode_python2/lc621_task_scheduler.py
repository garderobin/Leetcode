# coding=utf-8
from abc import ABCMeta, abstractmethod
from collections import defaultdict, Counter


class TaskScheduler:
    __metaclass__ = ABCMeta

    @abstractmethod
    def least_interval(self, tasks, n):
        """
        :type tasks: List[str]
        :type n: int
        :rtype: int
        """


class TaskSchedulerImplIdleSlots(TaskScheduler):
    """
    https://leetcode.com/problems/task-scheduler/solution/ Approach #3
    一维问题二维化，瞬间形象生动
    Time: O(len(tasks))
    Space: O(1) (only 26 available keys)
    极快，beat 98.9%
    """
    def least_interval(self, tasks, n):
        task_count_dict = defaultdict(int)
        for t in tasks:
            task_count_dict[t] += 1

        sorted_keys = sorted(task_count_dict, key=task_count_dict.get)
        max_val = task_count_dict[sorted_keys[-1]] - 1
        idle_slots = max_val * n

        for i in xrange(len(sorted_keys) - 1):
            idle_slots -= min(task_count_dict[sorted_keys[i]], max_val)

        return idle_slots + len(tasks) if idle_slots > 0 else len(tasks)


class TaskSchedulerImplIdleSlotsWithCounter(TaskScheduler):
    """
    https://leetcode.com/problems/task-scheduler/solution/ Approach #3
    一维问题二维化，瞬间形象生动
    Time: O(mlogm), m = len(tasks)
    Space: O(1) (only 26 available keys)
    不够快
    """
    def least_interval(self, tasks, n):
        sorted_counters = Counter(tasks).most_common()  # O(mlogm), m = len(tasks)
        max_val = sorted_counters[0][1] - 1
        idle_slots = max_val * n

        for i in xrange(1, len(sorted_counters)):
            idle_slots -= min(sorted_counters[i][1], max_val)

        return idle_slots + len(tasks) if idle_slots > 0 else len(tasks)

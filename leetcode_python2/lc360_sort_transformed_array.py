from abc import ABCMeta, abstractmethod
from collections import deque


class SortTransformedArray:
    __metaclass__ = ABCMeta

    @abstractmethod
    def sort_transformed_array(self, nums, a, b, c):
        """
        :type nums: List[int]
        :type a: int
        :type b: int
        :type c: int
        :rtype: List[int]
        """


class SortTransformedArrayImplDoublePointer(SortTransformedArray):
    def sort_transformed_array(self, nums, a, b, c):
        """
        Performance is not that good. Perhaps due to desc?
        """
        def transform(i):
            return a * i * i + b * i + c

        if a == 0:
            return [b * x + c for x in (nums if b >= 0 else nums[::-1])]
        else:
            extreme_point = (-float(b)) / (2 * a)
            if extreme_point >= nums[-1]:
                return [transform(x) for x in nums[::-1]]
            elif extreme_point <= nums[0]:
                return [transform(x) for x in nums]
            else:
                n, insert, res = len(nums), self.get_insert_index(nums, 0, len(nums) - 1, extreme_point), []
                transform_queues_desc = [
                    [transform(nums[i]) for i in (xrange(insert) if a > 0 else xrange(insert-1, -1, -1))],
                    [transform(nums[i]) for i in (xrange(n-1, insert-1, -1) if a > 0 else xrange(insert, n))]]

                print transform_queues_desc

                while transform_queues_desc[0] and transform_queues_desc[1]:
                    index_of_queue_with_smallest_element = transform_queues_desc[1][-1] < transform_queues_desc[0][-1]
                    res.append(transform_queues_desc[index_of_queue_with_smallest_element].pop())

                for q in transform_queues_desc:
                    while q:
                        res.append(q.pop())

                return res

    def sort_transformed_array_using_list(self, nums, a, b, c):
        """
        Performance is not that good. Perhaps due to desc?
        """
        def transform(i):
            return a * i * i + b * i + c

        if a == 0:
            return [b * x + c for x in (nums if b >= 0 else nums[::-1])]
        else:
            extreme_point = (-float(b)) / (2 * a)
            if extreme_point >= nums[-1]:
                return [transform(x) for x in nums[::-1]]
            elif extreme_point <= nums[0]:
                return [transform(x) for x in nums]
            else:
                n, insert, res = len(nums), self.get_insert_index(nums, 0, len(nums) - 1, extreme_point), []
                print insert, nums[(insert-1):-1:-1]
                transform_queues_desc = [
                    [transform(nums[i]) for i in (xrange(insert) if a > 0 else xrange(insert-1, -1, -1))],
                    [transform(nums[i]) for i in (xrange(n-1, insert-1, -1) if a > 0 else xrange(insert, n))]]

                print transform_queues_desc

                while transform_queues_desc[0] and transform_queues_desc[1]:
                    index_of_queue_with_smallest_element = transform_queues_desc[1][-1] < transform_queues_desc[0][-1]
                    res.append(transform_queues_desc[index_of_queue_with_smallest_element].pop())

                for q in transform_queues_desc:
                    while q:
                        res.append(q.pop())

                return res

    def sort_transformed_array_deque(self, nums, a, b, c):
        def quadratic_transform(i):
            return a * i * i + b * i + c

        if a == 0:
            return [b * x + c for x in (nums if b >= 0 else nums[::-1])]
        else:
            extreme_point = (-float(b)) / (2 * a)
            if extreme_point >= nums[-1]:
                return [quadratic_transform(x) for x in nums[::-1]]
            elif extreme_point <= nums[0]:
                return [quadratic_transform(x) for x in nums]
            else:
                insert_index, res = self.get_insert_index(nums, 0, len(nums) - 1, extreme_point), []
                transform_queue_left = deque([quadratic_transform(x) for x in nums[:insert_index]])
                transform_queue_right = deque([quadratic_transform(x) for x in nums[insert_index:]])

                while transform_queue_left and transform_queue_right:
                    left, right = transform_queue_left[-1], transform_queue_right[0]
                    if (a < 0) ^ (left < right):
                        res.append(left)
                        transform_queue_left.pop()
                    else:
                        res.append(right)
                        transform_queue_right.popleft()

                while transform_queue_left:
                    res.append(transform_queue_left.pop())

                while transform_queue_right:
                    res.append(transform_queue_right.popleft())

                return res[::-1] if a < 0 else res

    def sort_transformed_array_not_clean(self, nums, a, b, c):
        def quadratic_transform(i):
            return a * i * i + b * i + c

        if a == 0:
            return [b * x + c for x in (nums if b >= 0 else nums[::-1])]
        else:
            extreme_point = (-float(b)) / (2 * a)
            if extreme_point >= nums[-1]:
                return [quadratic_transform(x) for x in nums[::-1]]
            elif extreme_point <= nums[0]:
                return [quadratic_transform(x) for x in nums]
            else:
                insert_index, res = self.get_insert_index(nums, 0, len(nums) - 1, extreme_point), []
                left, right = insert_index - 1, insert_index
                if nums[insert_index] == extreme_point:
                    res.append(quadratic_transform(nums[insert_index]))
                    right += 1

                transform_left, transform_right = quadratic_transform(nums[left]), quadratic_transform(nums[right])
                while True:
                    if (a < 0) ^ (transform_left < transform_right):
                        res.append(transform_left)
                        left -= 1
                        if left < 0:
                            break
                        else:
                            transform_left = quadratic_transform(nums[left])
                    else:
                        res.append(transform_right)
                        right += 1
                        if right >= len(nums):
                            break
                        else:
                            transform_right = quadratic_transform(nums[right])

                for index in xrange(left, -1, -1):
                    res.append(quadratic_transform(nums[index]))

                for index in xrange(right, len(nums)):
                    res.append(quadratic_transform(nums[index]))

                return res if a > 0 else res[::-1]

    def get_insert_index(self, nums, start, end, value):
        if start > end:
            return -1
        elif start == end:
            return start if value < nums[start] else end + 1
        else:
            mid = start + (end - start) // 2
            if value == nums[mid]:
                return mid
            elif value < nums[mid]:
                return self.get_insert_index(nums, start, mid, value)
            else:
                return self.get_insert_index(nums, mid + 1, end, value)


if __name__ == "__main__":
    sol = SortTransformedArrayImplDoublePointer()
    # nums = [-99,-94,-90,-88,-84,-83,-79,-68,-58,-52,-52,-50,-47,-45,-35,-29,-5,-1,9,12,13,25,27,33,36,38,40,46,47,49,57,57,61,63,73,75,79,97,98]
    # a, b, c = -2, 44, -56
    # nums = [-98,-97,-96,-93,-90,-89,-89,-88,-85,-83,-83,-79,-78,-78,-76,-74,-63,-63,-63,-62,-59,-59,-57,-55,-54,-53,-49,-45,-41,-37,-35,-31,-25,-22,-20,-20,-17,-16,-16,-15,-13,-12,-12,-11,-4,-1,0,5,6,7,8,9,13,16,16,29,29,29,31,31,32,32,33,33,34,35,36,39,41,42,43,45,47,49,53,56,59,59,65,66,68,68,70,75,78,80,80,81,82,84,85,85,89,90,90,92,99,99]
    # a, b, c = -8, -16, 69
    nums = [-4, -2, 2, 4]
    a, b, c = -1, 3, 5
    print sol.sort_transformed_array(nums, a, b, c)

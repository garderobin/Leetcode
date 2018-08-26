from abc import ABCMeta, abstractmethod


class NextClosestTime:
    __metaclass__ = ABCMeta

    @abstractmethod
    def next_closest_time(self, time_str):
        """
        :type time_str: str
        :rtype: str
        """


def get_ordered_digits_asc(digital_strings):
    # digits = []
    # for s in digital_strings:
    #     for c in s:
    #         digits.append(int(c))
    digits = [c for s in digital_strings for c in s]
    digits.sort()
    return digits


def next_closest_number_str(single_unit_time_str, digits_asc, digit_order_map, upper_limit):
    first_digit, last_digit = int(single_unit_time_str[0]), int(single_unit_time_str[-1])
    if last_digit < digits_asc[-1]:
        next_possible_num = first_digit * 10 + digits_asc[digit_order_map[last_digit] + 1]
    elif first_digit < digits_asc[-1]:
        next_possible_num = digits_asc[digit_order_map[first_digit] + 1] * 10 + digits_asc[0]
    else:
        raise ValueError('Cannot find next closest number because first and last digits are all largest of all.')

    if next_possible_num < upper_limit:
        return '%02d' % next_possible_num
    else:
        raise ValueError('Cannot find next closest number because the construction if out of limit.')


class NextClosestTimeImpl(NextClosestTime):
    def next_closest_time(self, time_str):
        splits_by_unit = time_str.split(':')
        hour_str, minute_str = splits_by_unit[0], splits_by_unit[1]
        digits = [int(c) for s in splits_by_unit for c in s]
        digits.sort()
        digit_order_map = {digit: index for index, digit in enumerate(digits)}
        try:
            closest_minute = next_closest_number_str(minute_str, digits, digit_order_map, 60)
            closest_hour = hour_str
        except ValueError:
            try:
                closest_hour = next_closest_number_str(hour_str, digits, digit_order_map, 24)
                closest_minute = '%d%d' % (digits[0], digits[0])
            except ValueError:
                closest_hour = '%d%d' % (digits[0], digits[0])
                closest_minute = closest_hour
        return closest_hour + ':' + closest_minute


class NextClosestTimeImpl2(NextClosestTime):
    def next_closest_time(self, time_str):
        """
        Return the smallest time that uses the given digits, just make being larger than the input a priority.
        """
        return min((t <= time_str, t)
                   for i in range(24 * 60)
                   for t in ['%02d:%02d' % divmod(i, 60)]
                   if set(t) <= set(time_str))[1]


if __name__ == "__main__":
    time = '23:59'
    print NextClosestTimeImpl().next_closest_time(time)
    min_array = min((t <= time, t)
                    for i in range(24 * 60)
                    for t in ['%02d:%02d' % divmod(i, 60)]
                    if set(t) <= set(time))
    print min_array

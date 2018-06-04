from abc import ABCMeta, abstractmethod


class DailyTemperatures:
    """
    https://leetcode.com/problems/daily-temperatures/description/
    """

    __metaclass__ = ABCMeta

    @abstractmethod
    def daily_temperature(self, temperatures):
        """
        :type temperatures: List[int]
        :rtype: List[int]
        """


class DailyTemperatureImpl2(DailyTemperatures):

    def daily_temperature(self, temperatures):
        days_wait_until_a_warming_temperature = [0] * len(temperatures)
        not_warmed_dates_stack = []
        for date, temperature in enumerate(temperatures):
            while not_warmed_dates_stack and temperatures[not_warmed_dates_stack[-1]] < temperature:
                days_wait_until_a_warming_temperature[not_warmed_dates_stack.pop()] = date - not_warmed_dates_stack[-1]
            not_warmed_dates_stack.append(date)
        return days_wait_until_a_warming_temperature


class DailyTemperaturesImpl(DailyTemperatures):
    def daily_temperature(self, temperatures):
        daily_temperatures = [0] * len(temperatures)
        waiting_temperatures_stack = []
        for date, temperature in enumerate(temperatures):
            while len(waiting_temperatures_stack) > 0 and waiting_temperatures_stack[-1].temperature < temperature:
                latest_waiting_temperature = waiting_temperatures_stack.pop()
                daily_temperatures[latest_waiting_temperature.date] = date - latest_waiting_temperature.date
            waiting_temperatures_stack.append(SingleDayTemperature(date, temperature))
        return daily_temperatures


class SingleDayTemperature:
    def __init__(self, date, temperature):
        self.date = date
        self.temperature = temperature


if __name__ == "__main__":
    test_case = [73, 74, 75, 71, 69, 72, 76, 73]
    print test_case
    print DailyTemperaturesImpl().daily_temperature(test_case)




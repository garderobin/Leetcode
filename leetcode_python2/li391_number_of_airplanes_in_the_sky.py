class NumberOfAirplanesInTheSky(object):

    def count_of_airplanes(self, airplanes):
        """
        @param airplanes: An interval array
        @return: Count of airplanes are in the sky.
        """
        points = []
        for airplane in airplanes:
            points.append((airplane.start, 1))
            points.append((airplane.end, -1))

        number_of_airplanes, max_count_airplanes = 0, 0
        for _, count_delta in sorted(points):
            number_of_airplanes += count_delta
            max_count_airplanes = max(max_count_airplanes, number_of_airplanes)

        return max_count_airplanes


class Interval(object):
    def __init__(self, start, end):
        self.start = start
        self.end = end

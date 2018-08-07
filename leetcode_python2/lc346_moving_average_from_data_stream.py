from Queue import Queue
from abc import ABCMeta, abstractmethod


class MovingAverageFromDataStream:
    __metaclass__ = ABCMeta

    @abstractmethod
    def next(self, val):
        """
        :type val: int
        :rtype: float
        """

# Your MovingAverage object will be instantiated and called as such:
# obj = MovingAverage(size)
# param_1 = obj.next(val)


class MovingAverageFromDataStreamImplRollingArrays(MovingAverageFromDataStream):
    """
    Time: O(1) for next(), O(N) for init
    Space: O(size)
    """
    def __init__(self, size):
        self.max_window_size = size
        self.prefix_sums = [0 for _ in xrange(size + 1)]
        self.cur_sum = 0
        self.input_index = -1
        self.divisor = self.max_window_size + 1

    def next(self, val):
        self.cur_sum += val
        self.input_index += 1
        if self.input_index >= self.max_window_size:
            moving_sum = self.cur_sum - self.prefix_sums[(self.input_index - self.max_window_size) % self.divisor]
            moving_data_size = self.max_window_size
        else:
            moving_sum = self.cur_sum
            moving_data_size = self.input_index + 1
        self.prefix_sums[self.input_index % self.divisor] = self.cur_sum
        return float(moving_sum) / moving_data_size


# https://stackoverflow.com/questions/717148/queue-queue-vs-collections-deque

class MovingAverageFromDataStreamImplQueue(MovingAverageFromDataStream):
    """
    Time: O(1) for next() and init()
    Space: O(size)
    """
    def __init__(self, size):
        self.max_window_size = size
        self.window_queue = Queue()
        self.window_sum = 0

    def next(self, val):
        self.window_sum += val
        if self.window_queue.qsize() == self.max_window_size:
            self.window_sum -= self.window_queue.get()
        self.window_queue.put(val)
        return self.window_sum


if __name__ == "__main__":
    obj = MovingAverageFromDataStreamImplRollingArrays(20)
    data = [[23637],[-11625],[-5615],[-32455],[-4011],[-2940],[-9682],[21676],[-26616],[17045],[25362],[-29570],[-15360],[-32552],[17348],[11362],[822],[-25697],[-29180],[16630],[30227],[1483],[29470],[14410],[-26439],[-17137],[12798],[30882],[16298],[-30495],[11481],[-32419],[-2946],[-9264],[-13300],[21715],[22524],[11045],[4417],[-15042],[8045],[-31304],[21742],[-17831],[-29418],[-10879],[16123],[-18726],[19135],[713],[-2752],[-28381],[2820],[-32150],[-25995],[-28554],[-22725],[-934],[-11983],[3360],[30530],[-11762],[21171],[25964],[20898],[469],[-15350],[1398],[-21969],[-17686],[24260],[-13108],[14043],[17426],[23885],[26660],[-2828],[-9470],[13214],[23249],[-17781],[16086],[29081],[4686],[6411],[19428],[-17740],[-23564],[-22063],[-3570],[15530],[27195],[12369],[4861],[-16436],[20578],[-30233],[7350],[-12937],[-26468]]
    expected = [23637.0,6006.0,2132.33333,-6514.5,-6013.8,-5501.5,-6098.71429,-2626.875,-5292.33333,-3058.6,-474.90909,-2899.5,-3858.0,-5907.57143,-4357.2,-3374.75,-3127.88235,-4381.72222,-5686.89474,-4571.05,-4241.55,-3586.15,-1831.9,511.35,-610.05,-1319.9,-195.9,264.4,2410.1,33.1,-660.95,-803.4,-182.7,981.7,-550.7,-33.05,1052.05,2889.15,4569.0,2985.4,1876.3,236.95,-149.45,-1761.5,-1910.45,-1597.55,-1431.3,-3911.7,-3769.85,-2209.45,-2921.1,-2719.2,-2430.9,-3575.2,-4209.95,-6723.4,-8985.85,-9584.8,-10404.8,-9484.7,-8360.45,-7383.35,-7411.9,-5222.15,-2706.35,-2138.95,-3712.6,-2706.4,-4761.6,-5681.55,-4330.95,-3567.3,-3006.15,-527.35,1966.65,4727.35,5722.2,5295.4,6555.25,7549.7,5134.15,6526.55,6922.05,5858.15,5133.8,6081.75,5962.25,4714.15,4709.45,5415.25,4978.75,6993.9,6910.2,6281.95,4265.9,3961.8,2591.55,3432.55,2125.0,-360.85]
    res = []
    for i, d in enumerate(data):
        output = obj.next(d[0])
        print i, d, output, expected[i], output == expected[i]
        res.append(output)



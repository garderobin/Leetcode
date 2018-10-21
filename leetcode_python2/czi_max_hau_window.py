# Path to a file
# In that file:
# 512451434,123457

# 24 hours of usage on a site

# Takes in the file and returns the maximum number of hourly active users found in file.
# hourly active users: distinct users over a 3600 second period
# 12:15:36 to 1:15:35
# 12:15:37 to 1:15:36

# # calling back now
# # calling back again

# 23 * 60 * 60 = 1380 * 60 # window starts
# counter_dict: {} key: user_id, values: activity count in the current 3600 second window
# second_active_stats = {} key: second, value: dict(key: user_id, value: activity count in the timestamp)
# Time: O(n)
# Space: O(unique_user_ids)
from collections import defaultdict


class MaximumHAUWindow(object):
    def __init__(self, log_file_path):
        # TODO: read all activities from the file first
        self.activities = []
        pass

    def count_max_hau_hour(self):
        if not self.activities:
            # TODO raise alerts
            return (0, 3600)
        counter_dict = {}
        sec_active_stats = defaultdict(dict)
        start = int(activities[0].split(':')[0])

        for a in activities:
            st, su = a.split(':')
            t, u = int(st), int(su)
            if t not in sec_active_stats:
                sec_active_stats[t] = {}
            sec_active_stats[t][u] = sec_active_stats[t].get(u, 0) + 1

        right = start
        cur_max = (0, 0)  # start, hau
        for left in xrange(start, start + 23 * 60 * 60):
            # add right
            while right < start + 3600:
                for uid, count in sec_active_stats[right]:
                    counter_dict[uid] = counter_dict.get(uid, 0) + count

            # remove left
            if left > start:
                for uid, count in sec_active_stats[left - 1]:
                    counter_dict[uid] = counter_dict.get(uid, 0) - count
                    if counter_dict[uid] == 0:
                        del counter_dict[uid]

            hau = len(counter_dict)
            if cur_max[1] < hau:
                cur_max = (left, hau)
        return (cur_max[0], cur_max[0] + 3600)


if __name__ == '__main__':
    sol = MaximumHAUWindow('')
    sol.activities = ['']
    print sol.count_max_hau_hour()

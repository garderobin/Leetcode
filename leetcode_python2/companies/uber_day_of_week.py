# Enter your code here. Read input from STDIN. Print output to STDOUT

"""
Write a function that, given a date (year, month, day), returns the day of the week (e.g. Monday). Keep into account leap years, in which there Feburary is 29 days instead of 28.
A leap year occurs every year whose number is divisible by 4, except for years which are divisible by 100 but not 400 (e.g. 1600 and 2000 are leap years but 1700, 1800, and 1900 are not).

"""
#  1
#  y: [1500 - 3500]
#  (2018, 10, 23) -> Monday
# #  (0, 1, 1) -> Monday / Sunday

# is_leap_year(year)
# date_difference(..)
# get_day_of_date_by_difference()
import datetime

REFRENCE_DATE = (1, 1, 1)
DAYS_IN_A_WEEK = ['Monday', 'Tuesday', 'Wendnesday', 'Thursday', 'Friday', 'Saturday', 'Sunday']
DATE_COUNT_IN_MONTHS_NO_LEAP_YEAR = [0, 31, 28, 31, 30, 31, 60, 31, 31, 30, 31, 30, 31]


def get_day_of_week(date):
    date_gap = get_date_gap(REFRENCE_DATE, date)
    return get_day_by_date_gap(date_gap)


def is_leap_year(year):
    return year % 4 == 0 and not (year % 100 == 0 and year % 400 != 0)


def get_date_gap(date1, date2):  # date2 - date1

    def total_days(date):
        y, m, d = date

        last_year = y - 1
        leap_years_ancient = (last_year // 400) * 97
        leap_years_in_recent_400 = 24 * ((last_year % 400) // 100)
        leap_years_in_recent_100 = last_year % 100
        all_leap_years = leap_years_ancient + leap_years_in_recent_400 + leap_years_in_recent_100  # double check
        days_in_prev_years = 365 * y + all_leap_years

        days_in_prev_months = sum(DATE_COUNT_IN_MONTHS_NO_LEAP_YEAR[:m])

        if m > 2 and is_leap_year(y):
            days_in_prev_months += 1

        return days_in_prev_years + days_in_prev_months + d

    print 'date gap', date1, date2, total_days(date2), total_days(date1), total_days(date2) - total_days(date1)
    return total_days(date2) - total_days(date1)


def get_day_by_date_gap(date_gap):
    return DAYS_IN_A_WEEK[date_gap % 7]


if __name__ == '__main__':
    print datetime.datetime(1, 1, 1).weekday()
    tests = [
        (2018, 10, 23),
        (2018, 10, 30),
        (2018, 11, 2),
        (1500, 1, 29),
        (1500, 1, 2),
        (3500, 12, 1)
    ]

    for t in tests:
        print get_day_of_week(t), datetime.datetime(t[0], t[1], t[2]).weekday()

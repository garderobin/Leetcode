# Welcome to Facebook!

# This is just a simple shared plaintext pad, with no execution capabilities.

# When you know what language you'd like to use for your interview,
# simply choose it from the dropdown in the top bar.

# Enjoy your interview!

# I = 1
# V = 5
# X = 10
# L = 50
# C = 100
# D = 500
# M = 1000

# IV = 4
# IX = 9
# XL = 40
# XC = 90
# CD = 400
# CM = 900

# X V I I = 17
# X IV = 14
# CXLIV = 1 4 4
# C XL IV
# CM XC IX
# LXXX = 80

# VII, VIII

ROMAN_NUMERAL_DECODES = {
    'I': 1,
    'V': 5,
    'X': 10,
    'L': 50,
    'C': 100,
    'D': 500,
    'M': 1000
}


# roman_str = XVII
def roman_numerals_to_arabic_numerals(roman_str):
    """
    @param roman_str: string
    @return int
    """
    n = len(roman_str)
    result = ROMAN_NUMERAL_DECODES[roman_str[0]]  # n = 4, result = 10

    cur_num, prev_num = 0, result  # cur_num = 0, prev_num = 10
    i = 1
    while i < n:
        char = roman_str[i]
        cur_num = ROMAN_NUMERAL_DECODES[char]  # char = 'V', cur_num = 5

        if char == 'V':
            j = i + 1
            j_limit = min(n, i + 4)  # VI, VII, VIII

            while j < j_limit:  # j: [2, 6)
                if roman_str[j] == 'I':
                    cur_num += ROMAN_NUMERAL_DECODES[roman_str[j]]
                else:
                    break

            result += cur_num  # cur_num = 7, result = 17
            i = j  # i = 4
        else:
            if cur_num > prev_num:  # combination of two
                result += cur_num - prev_num - prev_num
            i += 1

        prev_num = cur_num  # prev_num = 7
    return result


if __name__ == '__main__':
    test_cases = [
        'XVII',
        'XIV',
        'LXXX',
        'CXLIV'
    ]
    for t in test_cases:
        print roman_numerals_to_arabic_numerals(t)

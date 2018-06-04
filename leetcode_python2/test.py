if __name__ == "__main__":
    # time = '23:59'
    # splits = time.split(':')
    # digits = {int(c) for c in splits[0] + splits[1]}
    # print digits
    digital_strings = '14', '23'
    digits = [c for s in digital_strings for c in s]
    print digits

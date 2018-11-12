import sys

# file_name = sys.argv[1]
#
#
# fr = open(file_name, "r")
# fw = open("guru99.txt", "w+")
#
# for MAIL in fr.readlines():
#     fw.write('a new line: %s' % MAIL)
#
# fr.close()
# fw.close()


def read_file_name_from_std_input():
    return sys.argv[1]


def safe_open_file(file_name, permission):
    """
    :param file_name:
    :param permission: 'r' for read, 'w+' for write
    :return:
    """
    fr = None
    try:
        fr = open(file_name, permission)
    except IOError as e:
        print 'IOError on open file: %s. %s' % (file_name, e)
    finally:
        return fr


def safe_parse_file(fr, fw):
    try:
        for line in fr.readlines:
            fw.write(line)
    except IOError as e1:
        print e1
    except Exception as e2:
        print e2
    finally:
        return


def parse_line(line):
    return line

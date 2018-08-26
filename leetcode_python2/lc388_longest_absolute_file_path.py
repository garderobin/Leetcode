from abc import ABCMeta, abstractmethod


class LongestAbsoluteFilePath:
    __metaclass__ = ABCMeta

    @abstractmethod
    def longest_absolute_file_path_length(self, input):
        """
        :type input: str
        :rtype: int
        """


class LongestAbsoluteFilePathImpl(LongestAbsoluteFilePath):

    def longest_absolute_file_path_length(self, input_str):
        lines = input_str.splitlines()
        path_len_by_depth = [0] * (len(lines) + 1)
        max_abs_path_len = 0
        for line in lines:
            name = line.lstrip('\t')
            depth = len(line) - len(name)
            if '.' in name:
                max_abs_path_len = max(max_abs_path_len, path_len_by_depth[depth] + len(name))
            else:
                path_len_by_depth[depth + 1] = path_len_by_depth[depth] + len(name) + 1
        return max_abs_path_len


class LongestAbsoluteFilePathImplIteration(LongestAbsoluteFilePath):

    def longest_absolute_file_path_length(self, input_str):
        node_with_depth_prefix_splits = input_str.split('\n')
        dummy_root = FileSystemNode('', -1, 0)
        cur_path_node_stack = [dummy_root]
        max_abs_file_path_length = 0
        for index, node_str in enumerate(node_with_depth_prefix_splits):
            cur_node = FileSystemNode.init_with_depth_prefix_str(node_str)

            while cur_path_node_stack[-1].depth > cur_node.depth:
                cur_path_node_stack.pop()
            cur_node.update_abs_path_length_by_parent_abs_path_length(cur_path_node_stack[-1].abs_path_length)

            if cur_node.is_file():
                max_abs_file_path_length = max(cur_node.abs_path_length, max_abs_file_path_length)
            else:
                cur_path_node_stack.append(cur_node)

        return max_abs_file_path_length


class FileSystemNode:
    @classmethod
    def init_with_depth_prefix_str(cls, str_with_depth_prefix):
        splits_by_depth = str_with_depth_prefix.split('\t')
        depth = len(splits_by_depth) - 1
        return cls(splits_by_depth[depth], depth, 0)

    def __init__(self, name, depth, abs_path_length):
        self.name = name
        self.depth = depth
        self.abs_path_length = abs_path_length

    def __str__(self):
        return str((self.name, self.depth, self.abs_path_length))

    def update_abs_path_length_by_parent_abs_path_length(self, parent_abs_path_length):
        self.abs_path_length = parent_abs_path_length + len(self.name) + min(1, self.depth)

    def is_file(self):
        return '.' in self.name


class LongestAbsoluteFilePathImplTree(LongestAbsoluteFilePath):

    def longest_absolute_file_path_length(self, input):
        pass


if __name__ == "__main__":
    # print len("dir/subdir2/subsubdir2/file2.ext")
    #
    # print LongestAbsoluteFilePathImplIteration().longest_absolute_file_path_length(
    #     "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext")

    # print LongestAbsoluteFilePathImplIteration().longest_absolute_file_path_length(
    #     "dir\n    file.txt")

    test_case = "skd\n\talskjv\n\t\tlskjf\n\t\t\tklsj.slkj\n\t\tsdlfkj.sdlkjf\n\t\tslkdjf.sdfkj\n\tsldkjf\n\t\tlskdjf\n\t\t\tslkdjf.sldkjf\n\t\t\tslkjf\n\t\t\tsfdklj\n\t\t\tlskjdflk.sdkflj\n\t\t\tsdlkjfl\n\t\t\t\tlskdjf\n\t\t\t\t\tlskdjf.sdlkfj\n\t\t\t\t\tlsdkjf\n\t\t\t\t\t\tsldkfjl.sdlfkj\n\t\t\t\tsldfjlkjd\n\t\t\tsdlfjlk\n\t\t\tlsdkjf\n\t\tlsdkjfl\n\tskdjfl\n\t\tsladkfjlj\n\t\tlskjdflkjsdlfjsldjfljslkjlkjslkjslfjlskjgldfjlkfdjbljdbkjdlkjkasljfklasjdfkljaklwejrkljewkljfslkjflksjfvsafjlgjfljgklsdf.a"
    print test_case
    print LongestAbsoluteFilePathImplIteration().longest_absolute_file_path_length(
        test_case
    )

    # test_case_2 = "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"



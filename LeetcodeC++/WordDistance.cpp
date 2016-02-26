//
//  WordDistance.cpp
//  LeetcodeC++
//
//  Created by Jasmine Liu on 2/19/16.
//  Copyright © 2016 Jasmine Liu. All rights reserved.
//

#include <stdio.h>
#include <unordered_map>
#include <vector>
#include <cstdlib>
#include <string>

using namespace std;

class WordDistance {
private:
    unordered_map<string, vector<size_t>> map;
public:
    WordDistance(vector<string>& words) {
        for (size_t i = 0, len = words.size(); i < len; i++) {
            map[words[i]].push_back(i);
        }
    }
    
    int shortestDistance(string& word1, string& word2) {
        vector<size_t> list1 = map[word1], list2 = map[word2];
        int i = 0, j = 0, rst = INT_MAX, len1 = (int)(list1.size()), len2 = (int)(list2.size());
        while (i < len1 && j < len2) {
            rst = min(rst, abs((int)(list1[i] - list2[j])));
            if (rst == 0) { return rst; }
            else if (list1[i] < list2[j]) { i++; }
            else { j++; }
        }
        return rst;
    }
};

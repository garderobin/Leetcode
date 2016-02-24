//
//  TwoSumDesign.cpp
//  LeetcodeC++
//
//  Created by Jasmine Liu on 2/18/16.
//  Copyright © 2016 Jasmine Liu. All rights reserved.
//
#include <unordered_map>
using namespace std;

class TwoSum {
    unordered_map<int, int> map;
public:
    void add(int num) {
        map[num]++;
    }
    
    bool find(int target) {
        for (unordered_map<int,int>::iterator it = map.begin(); it != map.end(); it++) {
            int x = it->first, y = target - x;
            if ((x == y && it->second > 1) || (x != y && map.find(y) != map.end())) {
                return true;
            }
        }
        return false;
    }
};




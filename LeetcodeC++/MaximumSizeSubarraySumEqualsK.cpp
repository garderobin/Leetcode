//
//  MaximumSizeSubarraySumEqualsK.cpp
//  LeetcodeC++
//
//  Created by Jasmine Liu on 2/19/16.
//  Copyright © 2016 Jasmine Liu. All rights reserved.
//

#include <stdio.h>
#include <vector>
#include <unordered_map>

using namespace std;

class Solution {
public:
    int maxSubArrayLen(vector<int>& nums, int k) {
//        int n = (int)nums.size();
//        if (n < k) { return -1; }
//        int rst = -1, total = 0;
//        int sum[n];
//        for (int i = 0; i < n; i++) {
//            total += nums[i];
//            sum
//        }
//        return -1;
        
        //<sumFromZero, endIndex>
        unordered_map<int, int> sums;
        int cur_sum = 0;
        int max_len = 0;
        for (int i = 0; i < nums.size(); i++) {
            cur_sum += nums[i];
            if (cur_sum == k) {
                max_len = i + 1;
            } else if (sums.find(cur_sum - k) != sums.end()) {
                max_len = max(max_len, i - sums[cur_sum - k]);
            }
            if (sums.find(cur_sum) == sums.end()) {
                sums[cur_sum] = i;
            }
        }
        return max_len;
    }
};
//
//  SlidingWindowMaximum.cpp
//  LeetcodeC++
//
//  Created by Jasmine Liu on 3/11/16.
//  Copyright © 2016 Jasmine Liu. All rights reserved.
//

#include <stdio.h>
#include <vector>
#include <deque>
using namespace std;

class SlidingWindowMaximum {
public:
    /* 有长度限制的最大堆的应用 */
     vector<int> maxSlidingWindow(vector<int>& nums, int k) {
         vector<int> rst;
         deque<int> q; // stores index in initial order as in nums. Always keep the max element in front.
         for (int i = 0; i < nums.size(); ++i) {
             // remove the left indexes which are out of window range
             if (!q.empty() && q.front() == i-k) q.pop_front();
             
             // remove the right smaller elements since they are useless
             while (!q.empty() && nums[q.back()] < nums[i]) q.pop_back();
             
             // compute window maximum
             q.push_back(i);
             if (i >= k-1) rst.push_back(nums[q.front()]);
         }
         
         return rst;
     }
};

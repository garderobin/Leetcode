//
//  BurstBalloon.cpp
//  LeetcodeC++
//
//  Created by Jasmine Liu on 2/29/16.
//  Copyright © 2016 Jasmine Liu. All rights reserved.
//

#include <stdio.h>
#include <vector>
#include <cmath>

using namespace std;

class Solution {
    
public:
    int maxCoins(vector<int>& nums) {
        int n = (int)nums.size();
        if (n == 0) { return 0; }
        else if (n == 1) { return nums[0]; }
        return helper(nums, 0, n).second;
    }
    
private:
    
    /* return: first is the last element in the maximum algorithm which acts as the multiplier
     second is the sum without adding the last element*/
    pair<int, int> helper(vector<int>& nums, int i, int j) {
        if (i > j) {
            return make_pair(0, 0);
        } else if (i == j) {
            return make_pair(nums[i], 0);
        } else if (i+1 == j) {
            return make_pair(i*j, max(nums[i], nums[j]));
        } else if (i+2 == j) {
            
            
//            return make_pair(<#_T1 &&__t1#>, <#_T2 &&__t2#>)
        } else {
            
        }
        return make_pair(i, j);
    }
};
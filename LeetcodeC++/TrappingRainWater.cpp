//
//  TrappingRainWater.cpp
//  LeetcodeC++
//
//  Created by Jasmine Liu on 3/12/16.
//  Copyright © 2016 Jasmine Liu. All rights reserved.
//

#include <stdio.h>
#include <vector>
using namespace std;

class TrappingRainWater {
public:
    int trap(vector<int>& height) {
        int n = (int)(height.size()), left = 0, right = n-1;
        int res = 0;
        int maxLeft = 0, maxRight = 0;
        while(left <= right){
            if(height[left] <= height[right]){
                if(height[left] >= maxLeft) maxLeft = height[left];
                else res += maxLeft - height[left];
                ++left;
            }
            else{
                if(height[right] >= maxRight) maxRight = height[right];
                else res += maxRight - height[right];
                --right;
            }
        }
        return res;
    }
};
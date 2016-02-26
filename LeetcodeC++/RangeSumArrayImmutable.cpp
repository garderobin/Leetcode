//
//  RangeSumArrayImmutable.cpp
//  LeetcodeC++
//
//  Created by Jasmine Liu on 2/24/16.
//  Copyright © 2016 Jasmine Liu. All rights reserved.
//

#include <stdio.h>
#include <iostream>
#include <vector>

using namespace std;


class NumArray {
public:
    NumArray(vector<int> &nums) {
        sum.push_back(0);
        for (int num : nums) {
            sum.push_back(sum.back() + num);
        }
    }
    
    int sumRange(int i, int j) {
        return sum[j+1] - sum[i];
    }
    
private:
    vector<int> sum;
};


//int main(int argc, const char * argv[]) {
//    // insert code here...
//    vector<int> input{-2,0,3,-5,2,-1};
//    NumArray na(input);
//    
//    cout << na.sumRange(0, 2) << endl;
//    cout << na.sumRange(2, 5) << endl;
//    cout << na.sumRange(0, 5) << endl;
//    
//    return 0;
//}



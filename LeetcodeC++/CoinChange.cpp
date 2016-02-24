//
//  CoinChange.cpp
//  LeetcodeC++
//
//  Created by Jasmine Liu on 2/23/16.
//  Copyright © 2016 Jasmine Liu. All rights reserved.
//

#include <stdio.h>
#include <vector>

using namespace std;

class CoinChange {
public:
    int coinChange(vector<int>& coins, int amount) {
        if (amount == 0) { return 0; }
        if (coins.empty()) { return -1; }
        
        // Sort coins to avoid useless comparasion
        sort(coins.begin(), coins.end());
        
        // Dynamic Programming
        int f[amount+1];
        for (int i = 1, limit = amount + 1; i < limit; i++) {
            f[i] = -1;
            for (vector<int>::iterator iter = coins.begin(); iter < coins.end() && *iter <= i; iter++) {
                int cur = f[i- (*iter)] + 1;
                if (cur > 0) {
                    if (f[i] < 0 || f[i] > cur) {
                        f[i] = cur;
                    }
                }
            }
        }
        
        return f[amount];
    }
};

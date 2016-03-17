//
//  ImplementAtoi.cpp
//  LeetcodeC++
//
//  Created by Jasmine Liu on 3/15/16.
//  Copyright © 2016 Jasmine Liu. All rights reserved.
//

#include <stdio.h>
#include <limits.h>
#include <string>
using namespace std;

class ImplementAtoi {
public:
    
    /*
     * We need to handle four cases:
     * 1. discards all leading whitespace
     * 2. sign of the number
     * 3. overflow 
     * (一定要include <limits.h>!!!)
     * -2147483648虽然不是overflow, 但我们把它当做overflow来处理，这样返回的结果是一样的。
     * 4. invalid input
     */
    int myAtoi(string str) {
        int sign = 1, base = 0, i = 0;
        // discard all leading whitespace
        while (str[i] == ' ') { ++i; }
        
        // handle sign
        if (str[i] == '-' || str[i] == '+') sign = 1 - 2 * (str[i++] == '-');
        
        // parse numbers until invalid characters found or overflow
        for (int baseLimit = INT_MAX/10; str[i] >= '0' && str[i] <= '9'; base = 10 * base + (str[i++] - '0')) {
            if (base > baseLimit || (base == baseLimit && str[i] - '0' > 7)) return (sign == 1) ? INT_MAX : INT_MIN;
        }
        
        return base * sign;
    }
};
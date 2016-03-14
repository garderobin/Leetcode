//
//  ScrambleString.cpp
//  LeetcodeC++
//
//  Created by Jasmine Liu on 3/8/16.
//  Copyright © 2016 Jasmine Liu. All rights reserved.
//

#include <stdio.h>
#include <string>
using namespace std;

class ScrambleString {
public:
    bool isScramble(string s1, string s2) {
        // check character validness
        if (s1.length() != s2.length()) { return false; }
        int n = (int)(s1.length());
        int letters1[256] = {0}, letters2[256] = {0};
        for (int i = 0; i < n; ++i) {
            ++letters1[s1[i]];
            ++letters2[s2[i]];
        }
        for (int i = 0; i < n; ++i) {
            if (letters1[s1[i]] != letters2[s1[i]]) { return false; }
        }
        
        // dfs pruning
        for (int i = 1; i < n; ++i) {
            if ((isScramble(s1.substr(0, i), s2.substr(0, i)) && isScramble(s1.substr(i, n), s2.substr(i, n))) ||
                (isScramble(s1.substr(0, i), s2.substr(n-i, n)) && isScramble(s1.substr(i, n), s2.substr(0, n-i)))) {
                return true;
            }
        }
        
        return n == 1;
    }
};

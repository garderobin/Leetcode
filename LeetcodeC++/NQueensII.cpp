//
//  NQueensII.cpp
//  LeetcodeC++
//
//  Created by Jasmine Liu on 3/9/16.
//  Copyright © 2016 Jasmine Liu. All rights reserved.
//

#include <stdio.h>
#include <stdlib.h>
#include <vector>
#include <string>
#include <iostream>
using namespace std;

class NQueensII {
public:
    int totalNQueens(int n) {
        vector<bool> cq(n), d1(n+n), d2(n+n);
        int count = 0;
        backtrack(n, count, 0, cq, d1, d2);
        return count;
    }
private:
    void backtrack(int& n, int& count, int row, vector<bool>&cq, vector<bool>&d1, vector<bool>&d2) {
        if (row == n) { ++count; return; }
        for (int col = 0; col < n; ++col) {
            if (!cq[col] || !d1[col + row] || !d2[row - col]) {
                cq[col] = d1[col + row] = d2[row - col] = true;
                backtrack(n, count, row, cq, d1, d2);
                cq[col] = d1[col + row] = d2[row - col] = false;
            }
        }

    }
    
};

int main() {
    NQueensII nq;
    int rst = nq.totalNQueens(1);
    cout << rst << endl;
}
//
//  NQueens.cpp
//  LeetcodeC++
//
//  Created by Jasmine Liu on 3/8/16.
//  Copyright © 2016 Jasmine Liu. All rights reserved.
//

/*point(x1, y1) and point(x2, y2) shares the same diagonal iff:
 (x1 + y1) == (x2 + y2) || abs(x1 - y1) == abs(x2 - y2) || abs(x1 - y1) = x2 + y2*/

#include <stdio.h>
#include <stdlib.h> //这个才是正牌的调用abs()函数的库！
#include <vector>
#include <iostream>
#include <string> //如果不需要对string进行操作可以布引入这个类，但是需要对string进行操作的时候必须引入

using namespace std;

class XQueens {
public:
    static const int BOARD_SIZE = 8;
    /*我这个方法解决不是N皇后问题，而是更难的在永恒的额8*8棋盘上放置X个皇后的问题。*/
    vector<vector<string>> solveXQueens(int n) {
        vector<vector<string>> rst;
        if (n == 0) { return rst; }
        else { return backtrack(n, rst, 0, vector<int>()); }
    }
    
private:
    vector<vector<string>> backtrack(int n, vector<vector<string>> rst, int row, vector<int> sol) {
        if (n == 0) {
            rst.push_back(generateSolutionByQueenPositions(sol));
            return rst;
        } else if (n + row > BOARD_SIZE) { return rst; }
        
        switch (row) {
            case 0:
                for (int i = 0; i < BOARD_SIZE; ++i) {
                    vector<int> line0{i+1};
                    rst = backtrack(n-1, rst, 1, line0);
                }
                sol.push_back(0);
                rst = backtrack(n, rst, 1, vector<int>{0}); // put no queen on first row
                sol.pop_back();
                return rst;
            case BOARD_SIZE: return rst;
            default:
//                cout << "\nn=" << n << ", row=" << row << ", rst=" << (int)(rst.size()) << endl;
               
                for (int j = 0; j < BOARD_SIZE; ++j) {
                    
                    if (find(begin(sol), end(sol), j+1) != sol.end()) { continue; } // check column not visited
                    
                    else { // check diagonal visited
                        bool diagonalVisited = false;
                        for (int k = 0, limit = (int)(sol.size()); k < limit; ++k) {
                            if (sol[k] > 0 && ((sol[k]-1==j) || shareDiagonal(k, sol[k]-1, row, j))) {
                                diagonalVisited = true;
                                break;
                            }
                        }
                        if (diagonalVisited) { continue; }
                        sol.push_back(j+1);
//                        cout << "j=" << j << ", row=" << row << ", rst=" << (int)(rst.size()) << endl;
                        rst = backtrack(n-1, rst, row+1, sol);
                        sol.pop_back();
                    }
                    
                }
                sol.push_back(0);
                rst = backtrack(n, rst, row+1, sol); // put no queen on this row
                sol.pop_back();
                return rst;
        }
    }
    
    bool shareDiagonal(int x1, int y1, int x2, int y2) {
        int dif = abs(x1 - y1);
        return (x1 + y1) == (x2 + y2) || dif == (abs(x2 - y2)) || dif == x2 + y2;
    }
    
    vector<string> generateSolutionByQueenPositions(vector<int> sol) {
        vector<string> solution;
        
        for (int i = 0; i < BOARD_SIZE; ++i) {
            if (i >= (int)(sol.size())) { solution.push_back("........"); }
            else {
                string row = ".......";
                switch (sol[i]) {
                    case 0:             row.push_back('.'); break;
                    case BOARD_SIZE:    row.push_back('Q'); break;
                    default:            row.insert(sol[i]-1, "Q"); break;
                }
                solution.push_back(row);
            }
            
        }
        return solution;
    }
    
    
};


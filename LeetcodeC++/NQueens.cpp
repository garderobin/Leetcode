//
//  NQueens.cpp
//  LeetcodeC++
//
//  Created by Jasmine Liu on 3/8/16.
//  Copyright © 2016 Jasmine Liu. All rights reserved.
//

#include <stdio.h>
#include <stdlib.h>
#include <vector>
#include <string>
#include <iostream>
using namespace std;

class NQueens {
public:
    
    vector<vector<string>> solveNQueensV3(int n) {
        vector<vector<string>> rst;
        vector<string> board(n, string(n, '.'));
        vector<bool> hasQueen(n * 5 - 2, false);
        backtrackV3(n, 0, rst, board, hasQueen);
        return rst;
    }
    
    vector<vector<string>> solveNQueensV2(int n) {
        vector<vector<string>> rst;
        vector<string> board(n, string(n, '.'));
        vector<vector<bool>> hasQueen(3, vector<bool>(n+n-1, false));
        backtrackV2(n, 0, rst, board, hasQueen);
        return rst;
    }
    
    vector<vector<string>> solveNQueensV1(int n) {
        return backtrackV1(n, 0, vector<vector<string>>(), vector<string> (n, string(n, '.')));
    }
    
private:
    
    void backtrackV3(int& n, int row, vector<vector<string>>& rst, vector<string>& board, vector<bool>& hasQueen) {
        if (row == n) { rst.push_back(board); }
        else {
            for (int col = 0; col < n; ++col) {
                int i = row + col + n, j = row - col + (n * 4) - 2;
                if (!hasQueen[col] && !hasQueen[i] & !hasQueen[j]) {
                    board[row][col] = 'Q';
                    hasQueen[col] = hasQueen[i] = hasQueen[j] = true;
                    
                    backtrackV3(n, row+1, rst, board, hasQueen);
                    
                    board[row][col] = '.';
                    hasQueen[col] = hasQueen[i] = hasQueen[j] = false;
                }
            }
        }
        
    }
    
    /*
     hasQueen[0] to flag[n - 1] to indicate if the column had a queen before.
     hasQueen[n] to flag[3 * n - 2] to indicate if the 135° diagonal had a queen before. compute: (x+y+n)
     hasQueen[3 * n - 1] to flag[5 * n - 3] to indicate if the 455° diagonal had a queen before. (x-y+4*n-2)
     */
    bool canPutQueenV3(int row, int col, int& n, vector<bool>& hasQueen) {
        return !hasQueen[col] && !hasQueen[row + col + n] & !hasQueen[row - col + (n * 4) - 2];
    }
    
    void backtrackV2(int& n, int row, vector<vector<string>>& rst, vector<string>& board, vector<vector<bool>>& hasQueen) {
        if (row == n) { rst.push_back(board); }
        else {
            for (int col = 0; col < n; ++col) {
                if (canPutQueenV2(row, col, n, board, hasQueen)) {
                    board[row][col] = 'Q';
                    hasQueen[0][col] = true;
                    hasQueen[1][row - col + n - 1] = true;
                    hasQueen[2][row + col] = true;
                    
                    backtrackV2(n, row+1, rst, board, hasQueen);
                    
                    board[row][col] = '.';
                    hasQueen[0][col] = false;
                    hasQueen[1][row - col + n - 1] = false;
                    hasQueen[2][row + col] = false;
                }
            }
        }
        
    }

    
    /* Version 2: indexing all hasQueenonals.
     * 45 degree hasQueenonal depends on x-y, from -n+1 to n-1, totally 2n-1. mapping: (x-y+n-1), from (0, 2n-1)
     * 135 degree hasQueenonal depens on x+y, from 0 to 2(n-1), totally 2n-1.
     * hasQueen[0] stores column validness, hasQueen[1] stores 45 degree. hasQueen[2] stores 135 degree.
     */
    bool canPutQueenV2(int row, int col, int n, vector<string> board, vector<vector<bool>> hasQueen) {
        return !hasQueen[0][col] && !hasQueen[1][row - col + n - 1] && !hasQueen[2][row + col];
    }
    
    
    vector<vector<string>> backtrackV1(int n, int row, vector<vector<string>> rst, vector<string> board) {
        if (row == n) { rst.push_back(board); }
        else {
            for (int col = 0; col < n; ++col) {
                if (canPutQueenV1(row, col, board, n)) {
                    board[row][col] = 'Q';
                    rst = backtrackV1(n, row+1, rst, board);
                    board[row][col] = '.';
                }
            }
        }
        return rst;
    }
    
    /* Version 1: brute-force*/
    bool canPutQueenV1(int row, int col, vector<string> board, int n) {
        // check if the column had a queen before.
        for (int i = 0; i < row; ++i) {
            if (board[i][col] == 'Q') { return false; } // 操作string需要import string类
        }
        
        // check if the 45° hasQueenonal had a queen before.
        for (int i = row-1, j = col-1; i >= 0 && j >= 0; --i, --j) {
            if (board[i][j] == 'Q') { return false; }
        }
        
        //check if the 135° hasQueenonal had a queen before.
        for (int i = row-1, j = col+1; i >= 0 && j < n; --i, ++j) {
            if (board[i][j] == 'Q') { return false; }
        }
        return true;
    }
    
    bool sharehasQueenonal(int x1, int y1, int x2, int y2) {
        int dif = abs(x1 - y1);
        return (x1 + y1) == (x2 + y2) || dif == (abs(x2 - y2)) || dif == x2 + y2;
    }

};


//
//  WallsAndGates.cpp
//  LeetcodeC++
//
//  Created by Jasmine Liu on 2/24/16.
//  Copyright © 2016 Jasmine Liu. All rights reserved.
//

#include <stdio.h>
#include <vector>
#include <queue>

using namespace std;

/*  BFS solution. time limit exceeded. */
class WallsAndGates {
public:
    void wallsAndGates(vector<vector<int>>& rooms) {
        if (rooms.empty()) { return; }
        for (int i = 0, r = (int)(rooms.size()); i < r; i++) {
            for (int j = 0, c = (int)(rooms[0].size()); j < c; j++) {
                if (rooms[i][j] == 0) { updateFromGate(rooms, make_pair(i, j)); }
            }
        }
    }
private:
    pair<int, int> dirs[4] = {make_pair(-1, 0), make_pair(1, 0), make_pair(0, -1), make_pair(0, 1)};
    
    void updateFromGate(vector<vector<int>>& rooms, pair<int, int> gate) {
        int r = (int)rooms.size(), c = (int)(rooms[0].size());
        deque<pair<int, int>> q;
        q.push_back(gate);
        
        while (!q.empty()) {
            pair<int, int> point = q.front();
            int distance = rooms[point.first][point.second];
            q.pop_front();
            for (pair<int, int> dir: dirs) {
                int x = point.first + dir.first;
                int y = point.second + dir.second;
                if (x >= 0 && x < r && y >= 0 && y < c && rooms[x][y] > distance + 1) {
                    // 最后一个条件很重要，BFS一定要注意，不是所有情况都分层，因此计数器不能计层数，要从前往后加
                    rooms[x][y] = distance + 1;
                    q.push_back(make_pair(x, y));
//                    q.emplace(x, y);
                }
            }
            
        }
        
    }
};

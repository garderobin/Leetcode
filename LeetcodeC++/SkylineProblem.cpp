//
//  SkylineProblem.cpp
//  LeetcodeC++
//
//  Created by Jasmine Liu on 3/11/16.
//  Copyright © 2016 Jasmine Liu. All rights reserved.
//

#include <stdio.h>
#include <vector>
#include <deque>
#include <stdlib.h>

using namespace std;

class Skyline {
public:
    /* time limit exceeded.*/
    vector<pair<int, int>> getSkyline(vector<vector<int>>& buildings) {
        vector<pair<int, int>> skyline;
        int lastHeight = -2147483648;
        deque<pair<int, int>> q, q1;
        
        for (vector<int> bd: buildings) {
            int ib = 0;
            vector<int> height{bd[2], 0};
            
            // pop before left border
            for (; !q.empty() && q.front().first < bd[ib]; q.pop_front()) { // pop into skyline
                pair<int, int> pf = q.front();
                if (pf.second != lastHeight) {
                    lastHeight = pf.second;
                    skyline.push_back(pf);
                }
            }
            
            // insert left border
            q1.push_back(make_pair(bd[ib], max(lastHeight, height[ib])));
            if (!q.empty() && q.front().first == bd[ib]) { q.pop_front(); }
            
            // insert before right border
            for (++ib; !q.empty() && q.front().first < bd[ib]; q.pop_front()) {
                pair<int, int> pf = q.front();
                int curHeight = max(bd[2], pf.second);
                if (curHeight != q1.front().second) { // only push those not-secutive borders
                    q1.push_back(make_pair(pf.first, curHeight));
                }
            }
            
            // insert right border
            q1.push_back(make_pair(bd[1],(q.empty() ? 0 : lastHeight)));
            
            // insert after right border
            for (; !q.empty(); q.pop_front()) {
                q1.push_back(q.front());
            }
            
            q = q1;
            
        }
        
        for (; !q1.empty(); q1.pop_front()) { skyline.push_back(q1.front()); }
        
        return skyline;
    }

};

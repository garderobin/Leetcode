//
//  CourseSchedule.cpp
//  LeetcodeC++
//
//  Created by Jasmine Liu on 3/24/16.
//  Copyright © 2016 Jasmine Liu. All rights reserved.
//

#include <stdio.h>
#include <vector>
#include <unordered_set>

using namespace std;

class CourseSchedule {
public:
//    bool canFinish(int numCourses, vector<pair<int, int>>& prerequisites) {
//        vector<unordered_set<int>> graph = make_graph(numCourses, prerequisites);
//        vector<bool> onpath(numCourses, false), visited(numCourses, false);
//        for (int i = 0; i < numCourses; ++i) {
//            if (dfs_cycle(graph, i, onpath, visited)) return false;
//        }
//        return true;
//    }
    bool canFinish(int numCourses, vector<pair<int, int>>& prerequisites) {
        vector<unordered_set<int>> graph = make_graph(numCourses, prerequisites);
        vector<bool> onpath(numCourses, false), visited(numCourses, false);
        for (int i = 0; i < numCourses; ++i) {
            if (!visited[i] && dfs_cycle(graph, i, onpath, visited)) return false;
        }
        return true;
    }
    
private:
    vector<unordered_set<int>> make_graph(int numCourses, vector<pair<int, int>>& prerequisites) {
        vector<unordered_set<int>> graph(numCourses);
        for (auto pre : prerequisites) graph[pre.second].insert(pre.first);
        return graph;
    }
    
//    bool dfs_cycle(vector<unordered_set<int>>& graph, int node, vector<bool>& onpath, vector<bool>& visited) {
//        if (visited[node]) return false;
//        visited[node] = true;
//        for (int nb : graph[node]) {
//            if (!dfs_cycle(graph, nb, onpath, visited)) return false;
//        }
//        visited[node] = false;
//        return true;
//    }
    
    bool dfs_cycle(vector<unordered_set<int>>& graph, int node, vector<bool>& onpath, vector<bool>& visited) {
        if (visited[node]) return false;
        onpath[node] = visited[node] = true;
        for (int nb : graph[node]) {
            if (onpath[nb] || dfs_cycle(graph, nb, onpath, visited)) return true;
        }
        return onpath[node] = false;
    }
};
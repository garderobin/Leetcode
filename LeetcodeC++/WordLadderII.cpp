//
//  WordLadderII.cpp
//  LeetcodeC++
//
//  Created by Jasmine Liu on 3/8/16.
//  Copyright © 2016 Jasmine Liu. All rights reserved.
//

#include <stdio.h>
#include <vector>
#include <string>
#include <unordered_set>
#include <unordered_map>
using namespace std;

class WordLadderII {
public:
//    /*
//     * BFS version
//     */
//    vector<vector<string>> findLaddersBFS(string beginWord, string endWord, unordered_set<string> &wordList) {
//        vector<vector<string>> rst;
//        if (beginWord.length() != endWord.length()) { return rst;}
////        vector<string> prev{beginWord}, cur;
//        int n = (int)(beginWord.length());
//        vector<unordered_set<string>> dp{{beginWord}};
//        if (beginWord == endWord) {
//            rst[0][0] = beginWord;
//            return rst;
//        }
//        wordList.erase(beginWord);
//        wordList.erase(endWord);
//        for (int i = 0; i <= n; ++i) {
//            unordered_set<string> prev = dp[i], cur;
//            for (auto const &w: wordList) {
//                for (auto const &p: prev) {
//                    if (difByOneLetter(w, p)) { cur.insert(w); break; }
//                }
//            }
//        }
//        return rst;
//    }
    
    bool difByOneLetter(string s1, string s2) {
        if (s1.length() != s2.length()) { return false; }
        bool foundOneDifference = false;
        for (int i = 0, n = (int)(s1.length()); i < n; ++i) {
            if (s1[i] != s2[i]) {
                if (foundOneDifference) { return false; }
                else { foundOneDifference = true; }
            }
        }
        return foundOneDifference;
    }

    vector<vector<string> > findLadders(string beginWord, string endWord, unordered_set<string> &wordList) {
        vector<vector<string> > paths;
        vector<string> path(1, beginWord);
        if (beginWord == endWord) {
            paths.push_back(path);
            return paths;
        }
        unordered_set<string> words1, words2;
        words1.insert(beginWord);
        words2.insert(endWord);
        unordered_map<string, vector<string> > nexts;
        bool words1IsBegin = false;
        if (findLaddersHelper(words1, words2, wordList, nexts, words1IsBegin))
            getPath(beginWord, endWord, nexts, path, paths);
        return paths;
    }
private:
    bool findLaddersHelper(
                           unordered_set<string> &words1,
                           unordered_set<string> &words2,
                           unordered_set<string> &wordList,
                           unordered_map<string, vector<string> > &nexts,
                           bool &words1IsBegin) {
        words1IsBegin = !words1IsBegin;
        if (words1.empty())
            return false;
        if (words1.size() > words2.size())
            return findLaddersHelper(words2, words1, wordList, nexts, words1IsBegin);
        for (auto it = words1.begin(); it != words1.end(); ++it)
            wordList.erase(*it);
        for (auto it = words2.begin(); it != words2.end(); ++it)
            wordList.erase(*it);
        unordered_set<string> words3;
        bool reach = false;
        for (auto it = words1.begin(); it != words1.end(); ++it) {
            string word = *it;
            for (auto ch = word.begin(); ch != word.end(); ++ch) {
                char tmp = *ch;
                for (*ch = 'a'; *ch <= 'z'; ++(*ch))
                    if (*ch != tmp) {
                        if (words2.find(word) != words2.end()) {
                            reach = true;
                            words1IsBegin ? nexts[*it].push_back(word) : nexts[word].push_back(*it);
                        }
                        else if (!reach && wordList.find(word) != wordList.end()) {
                            words3.insert(word);
                            words1IsBegin ? nexts[*it].push_back(word) : nexts[word].push_back(*it);
                        } else {}
                    }
                *ch = tmp;
            }
        }
        return reach || findLaddersHelper(words2, words3, wordList, nexts, words1IsBegin);
    }
    void getPath(
                 string beginWord,
                 string &endWord,
                 unordered_map<string, vector<string> > &nexts,
                 vector<string> &path,
                 vector<vector<string> > &paths) {
        if (beginWord == endWord)
            paths.push_back(path);
        else
            for (auto it = nexts[beginWord].begin(); it != nexts[beginWord].end(); ++it) {
                path.push_back(*it);
                getPath(*it, endWord, nexts, path, paths);
                path.pop_back();
            }
    }

 
};
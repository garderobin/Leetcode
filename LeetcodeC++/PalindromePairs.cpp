////
////  PalindromePairs.cpp
////  LeetcodeC++
////
////  Created by Jasmine Liu on 3/11/16.
////  Copyright © 2016 Jasmine Liu. All rights reserved.
////
//
//#include <stdio.h>
//#include <vector>
//#include <string>
//#include "Trie.hpp"
//
//using namespace std;
//
///*incomple. See java discussion version in eclipse.*/
//class PalindromePairs {
//public:
//    vector<vector<int>> palindromePairs(vector<string>& words) {
//        vector<vector<int>> rst;
//        
//        // First pass: construct Tries and palindrom start index count
//        vector<Trie> vpre, vpost; // vpre[i] stores all words from i index (inclusive)
//        Trie preTrie, postTrie;
//        vector<int> leftStart, rightStart; // the minimum number of chars needed to add on the left/right side.
//        
//        for (int i = (int)(words.size()); i >0; --i) {
//            wordPreprocessor(words, i-1, preTrie, postTrie, leftStart, rightStart);
//            vpre.push_back(preTrie);
//            vpost.push_back(postTrie);
//        }
//        
//        // Second pass: find all palindrom pairs
//        return rst;
//    }
//    
//private:
//    void findPalindromPairsForOneWord(vector<vector<int>>& rst, vector<string>& words, int index,
//            vector<Trie>& vpre, vector<Trie>& vpost, vector<int>& leftStart, vector<int>& rightStart) {
//        string s = words[index], leftAppend = "", rightAppend = "";
//        int n = (int)(s.length()), limit = n+2, i = 0, j = n-1, ls = leftStart[index], rs = rightStart[index];
//
////        for (; i < ls; ++i) { leftAppend +=
//        for (int i = leftStart[index]; i < limit; ++i) {
//            
//        }
////        vpost[index+1]
//    }
//    
//    /* Insert the word into prefix trie, insert its reverse into postfix trie, 
//     count its palindrome left start and right start */
//    void wordPreprocessor(vector<string>& words, int index,
//            Trie& preTrie, Trie& postTrie, vector<int>& leftStart, vector<int>& rightStart) {
//
//        string s = words[index];
//        int n = (int)(s.length()), rs = n, ls = n;
//        char firstLetter = s[0], lastLetter = s[n-1];
//        bool leftTrailing = true, rightTrailing = true;
//        TrieNode *pre = preTrie.root, *post = postTrie.root;
//        
//        for (int i = 0; i < n; ++i) {
//            if (leftTrailing) {
//                if (s[i] == firstLetter) { --ls; }
//                else { leftTrailing = false; }
//            }
//
//            if (pre -> next[s[i] - 'a'] == NULL) {
//                pre -> next[s[i] - 'a'] = new TrieNode();
//            }
//            pre = pre -> next[s[i] - 'a'];
//        }
//        pre -> is_word = true;
//        pre -> index = index;
//        pre -> left_start = ls;
////        leftStart[index] = ls;
//        
//        for (int i = n-1; i >= 0; --i) {
//            if (rightTrailing) {
//                if (s[i] == lastLetter) { --rs; }
//                else { rightTrailing = false; }
//            }
//            
//            if (post -> next[s[i] - 'a'] == NULL) {
//                post -> next[s[i] - 'a'] = new TrieNode();
//            }
//            post = post -> next[s[i] - 'a'];
//        }
//        post -> is_word = true;
//        post -> index = index;
//        post -> right_start = rs;
////        rightStart[index] = rs;
//    }
//};

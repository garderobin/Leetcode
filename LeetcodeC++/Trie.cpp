//
//  Trie.cpp
//  LeetcodeC++
//
//  Created by Jasmine Liu on 3/11/16.
//  Copyright © 2016 Jasmine Liu. All rights reserved.
//

#include <stdio.h>
#include <string>
#include "Trie.hpp"
using namespace std;


TrieNode:: TrieNode(bool b) {
    memset(next, 0, sizeof(next));
    is_word = b;
    index = -1;
    left_start = 0;
    right_start = 0;
}

TrieNode:: TrieNode(bool b, int i) {
    memset(next, 0, sizeof(next));
    is_word = b;
    index = i;
    left_start = 0;
    right_start = 0;
}


Trie:: Trie() {
    root = new TrieNode();
}
    // Inserts a word into the trie.
void Trie::insert(string s) {
    TrieNode *p = root;
    for (int i = 0; i < s.size(); ++ i) {
        if (p -> next[s[i] - 'a'] == NULL) {
            p -> next[s[i] - 'a'] = new TrieNode();
        }
        p = p -> next[s[i] - 'a'];
    }
    p -> is_word = true;
}
    
    // Returns if the word is in the trie.
bool Trie::search(string key) {
        TrieNode *p = find(key);
        return p != NULL && p -> is_word;
}

bool Trie::search(string key, int start, int end) {
    TrieNode *p = root;
    for(int i = start; i < end && p != NULL; ++ i)
        p = p -> next[key[i] - 'a'];
    return p != NULL && p -> is_word;
}

    // Returns if there is any word in the trie
    // that starts with the given prefix.
bool Trie::startsWith(string prefix) {
        return find(prefix) != NULL;
}

TrieNode* Trie::find(string key) {
        TrieNode *p = root;
        for(int i = 0; i < key.size() && p != NULL; ++ i)
            p = p -> next[key[i] - 'a'];
        return p;
    }

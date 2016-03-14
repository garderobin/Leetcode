//
//  Trie.hpp
//  LeetcodeC++
//
//  Created by Jasmine Liu on 3/11/16.
//  Copyright © 2016 Jasmine Liu. All rights reserved.
//
#include "string.h"
#include <stdlib.h>
using namespace std;

#ifndef Trie_h
#define Trie_h

class TrieNode {
public:
    TrieNode *next[26];
    bool is_word;
    int index;
    int left_start;
    int right_start;
    vector<string> appendToPalinderomList;
    
    // Initialize your data structure here.
    TrieNode(bool b = false);
    TrieNode(bool b, int i);
//    TrieNode * createInstance();
    ~TrieNode() { free(next); }
};

class Trie {
    
public:
    TrieNode *root;
    Trie();
    virtual ~Trie();
    void insert(string s);
    bool search(string key);
    bool search(string key, int start, int end);
    bool searchReverse(string key, int rs, int re); // rs >= re
    bool startsWith(string prefix);
private:
    TrieNode * find(string key);
//    TrieInterface * createInstance();
    //    virtual TrieNodeInterface* find(string key) = 0;
};

class TrieNodeInterface {
public:
    TrieNodeInterface *next[26];
    bool is_word;
    
    // Initialize your data structure here.
    TrieNodeInterface(bool b = false) {}
    TrieNodeInterface * createInstance();
};

class TrieInterface {
    TrieNodeInterface *root;
public:
    TrieInterface();
    virtual ~TrieInterface() {}
    virtual void insert(string s) = 0;
    virtual bool search(string key) = 0;
    virtual bool startsWith(string prefix) = 0;
    TrieInterface * createInstance();
//    virtual TrieNodeInterface* find(string key) = 0;
};


#endif /* Trie_h */

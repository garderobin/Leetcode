//
//  LRUCache.cpp
//  LeetcodeC++
//
//  Created by Jasmine Liu on 2/18/16.
//  Copyright © 2016 Jasmine Liu. All rights reserved.
//

#include <stdio.h>
#include <list>
#include <unordered_map>

using namespace std;

/**
 * hashmap +  list + splice()
 */
class LRUCache {
private:
    int capacity;                                       // the capacity of the cache
    list<pair<int, int>> items;                         // the list of (key, value) pair
    unordered_map<int, list<pair<int, int>>::iterator> cache;     // map items to iterators (pointers) to list nodes
    

public:
    LRUCache(int capacity) : capacity(capacity) {}
    
    int get(int key) {
        // If key is not found in the map, return -1
        if (cache.find(key) == cache.end()) { return -1; }
        
        // Move the (key, value) pair to the beginning of the list
        items.splice(items.begin(), items, cache[key]);
        return cache[key]->second;
    }
    
    void set(int key, int value) {
        // The key is not in the hash table
        if (cache.find(key) == cache.end()) {
            // If the cache is full then delete the least recently
            // used item, which is at the end of the list
            if (items.size() == capacity) {
                cache.erase(items.back().first);
                items.pop_back();
            }
            items.push_front(make_pair(key, value));
            cache[key] = items.begin();
            
        } else {
            // Update the value associated with the key
            cache[key]->second = value;
            // Move the (key, value) pair to the beginning of the list
            items.splice(items.begin(), items, cache[key]);
        }
    }
};
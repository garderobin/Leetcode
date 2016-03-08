//
//  WordCombinations.cpp
//  LeetcodeC++
//
//  Created by Jasmine Liu on 3/7/16.
//  Copyright © 2016 Jasmine Liu. All rights reserved.
//

#include <iostream>
using namespace std;
#include <vector>
#include <string>
#include <iostream>
#include <stdlib.h>
using std::vector;
using std::string;
using std::cout;
using std::endl;

vector<vector<string> > words;

// I am assuming C++11 support

void create_vectors(int argc, char *argv[]) {
    cout << endl;
    cout << "in function";
    cout << endl;
    /* The argument parsing/checking definitely would need to be a lot
     more rigorous and validated, to ensure malicious code couldn't
     just be tacked onto the end, or simply if a user forgot an argument
     or miscounted the words in an array
     */
    
    // Loop through all arguments, will be incremented inside loop
    // since will be pulling multiple arguments each loop
    int arg_offset = 1;
    while (arg_offset < argc) {
        vector<string> new_vec;
        // Get length of this vector
        int vec_len = atoi(argv[arg_offset]);
        arg_offset++;
        // For each word in thie vector, add it to the vector
        for (int j = arg_offset; j < arg_offset + vec_len; j++)
            new_vec.push_back(string(argv[j]));
        words.push_back(new_vec);
        arg_offset += vec_len;
    }
}

int main(int argc, char *argv[]) {
    // If no arguments given
    if (argc == 1) {
        cout << "here";
        cout << endl;
        // Populate words using sample arrays
        for (int i = 0; i < 3; i++) {
            // Create new vector
            vector<string> new_vec;
            switch (i) {
                    // Assign words based on position in top-level vector
                case 0:
                    new_vec.assign({"quick", "lazy"});
                    break;
                case 1:
                    new_vec.assign({"brown", "black", "grey"});
                    break;
                case 2:
                    new_vec.assign({"fox", "fox"});
                    break;
            }
            words.push_back(new_vec);
        }
    } else if (argc < atoi(argv[1]) * 2) {
        cout << "herer 2";
        /* If less arguments given than the supposed length of the top-level array * 2,
         (which would be one length and word per sub-array), print help
         This could be done using a library too, but given I don't know how to actually
         provide command line parameters to the web-based GUI I figured this is adequate
         */
        cout << "Program usage: " << endl;
        cout << "    ./solution will use a default word list, provided in the comment spec. These are: " << endl;
        cout << "    [['quick', 'lazy'], ['brown', 'black', 'grey'], ['fox', 'dog']]" << endl << endl;
        cout << "    Alternatively, if you wish to specify a different set of words, you may use the following format: " << endl;
        cout << "    ./solution <length of first vector> <word1> <word2> …. <length of second vector> <word1> <word2> … …." << endl;
        return 1;
    } else {
        cout << "here 3";
        // Otherwise load the words
        create_vectors(argc, argv);
    }
    
    // Start all array positions at 0
    vector<unsigned> offsets(words.size());
    offsets.assign(words.size(), 0);
    // Loop until all values have been printed
    while (1) {
        // This will print out an extra space at the end of the line
        // For simplicity I am leaving it
        for (unsigned i = 0; i < words.size(); i++)
            cout << words[i][offsets[i]] << " ";
        cout << endl;
        // Increment last vector offset
        offsets[words.size() - 1]++;
        
        // If the offset is past the end of the last array, ripple upwards
        if (offsets[words.size() - 1] >= words[words.size() - 1].size()) {
            // Next vector position
            int word_vec = (int)(words.size() - 1);
            while (1) {
                // Increase offset for given vector
                offsets[word_vec]++;
                // If in bounds of that vector, print next word combination
                if (offsets[word_vec] < words[word_vec].size())
                    break;
                // If in this loop and at the top vector, then we're done
                else if (word_vec == 0)
                    return 0;
                // Otherwise reset the last vector count to 0
                else
                    offsets[word_vec] = 0;
                // Decrement the word vector to check the next level
                word_vec--;
            }
        }
    }
    return 0;
}


/**
 You're given a vector of vectors of words, e.g.:
 [['quick', 'lazy'], ['brown', 'black', 'grey'], ['fox', 'dog']].
 
 Write a generalized function that prints all combinations of one word from the first vector, one word from the second vector, etc.
 NOTE: the number of vectors and number of elements within each vector may vary.
 
 For the input above, it should print (in any order):
 quick brown fox
 quick brown dog
 quick black fox
 quick black dog
 … 
 lazy grey dog
 **/
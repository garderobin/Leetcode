//
//  FreeABST.c
//  LeetcodeC++
//
//  Created by Jasmine Liu on 3/15/16.
//  Copyright © 2016 Jasmine Liu. All rights reserved.
//

#include <stdio.h>
#include <stdlib.h>

typedef struct tree_s tree_t;

struct tree_s {
    char *str;
    tree_t *left;
    tree_t *right;
};

/* postorder traversal */
void preord(struct tree_s *root) {
    if(root) {
        preord(root->left);
        preord(root->right);
        free(root->str);
        free(root);
    }
}
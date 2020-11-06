#include <stdio.h>
#include <stdlib.h>

struct node{
    int value;
    int null;
    struct node left;
    struct node *right;
};

struct tree{
    struct node root;
};

struct node *add(struct node root, int value){
    if(root.null == NULL){
        struct node newNode;
        newNode.value = value;
        newNode.null = 0;
        //printf("Value: %d", newNode.value);
        return &newNode;
    }
    else if(value < root.value){
        root.left = add(root.left, value);
    }
    else{
        root->right = add(root->right, value);
    }
};

struct node *traverse(struct node *root){
    if(root->value != NULL){
        //printf("Here2");
        traverse(root->left);
        printf("Value: %d", root->value);
        traverse(root->right);
        //printf("test2");
    }
}


int main(){
    int nodes[] = {57, 10, 93, 88, 77, 74, 78};
    size_t nodesLength = sizeof(nodes) / sizeof(nodes[0]);
    struct tree bst;
    struct node newNode;
    newNode.value = NULL;
    newNode.null = NULL;
    bst.root = newNode;
    for(int i=0; i < nodesLength; i++){
        bst.root = add(bst.root, nodes[i]);
    }
    printf("before");
    printf(bst.root);
    //traverse(bst.root);
    printf("after");
}

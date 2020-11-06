#include <stdio.h>
#include <stdlib.h>

struct node{
    int value;
    struct node* left;
    struct node *right;
};

struct tree{
    struct node root;
};

struct node* add(struct node *root, int value){
    if(root == NULL){
        struct node* newNode;
        newNode = (struct node*)malloc(sizeof(struct node));
        newNode->value = value;
        //printf("Value: %d", newNode.value);
        return newNode;
    }
    else if(value < root->value){
        root->left = add(root->left, value);
    }
    else{
        root->right = add(root->right, value);
    }
};

struct node *traverse(struct node *root){
    if(root != NULL){
        //printf("Here2");
        traverse(root->left);
        printf("Value: %d\n", root->value);
        traverse(root->right);
        //printf("test2");
    }
}


int main(){
    int nodes[] = {57, 10, 93, 88, 77, 74, 78};
    size_t nodesLength = sizeof(nodes) / sizeof(nodes[0]);

    struct node* root = NULL;
    //struct node* left = NULL;
    //struct node* right = NULL;


    //root->left = left;
    //root->right = right;

    for(int i=0; i < nodesLength; i++){
        root = add(root, nodes[i]);
    }
    traverse(root);
}

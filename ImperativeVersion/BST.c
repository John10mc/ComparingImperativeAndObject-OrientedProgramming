#include <stdio.h>
#include <stdlib.h>
#include <string.h>

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
        struct node* newNode = (struct node*)malloc(sizeof(struct node));
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

struct node* find(struct node *root, int value){
    if(root->value == value){
        return root;
    }
    else if(value < root->value){
        return find(root->left, value);
    }
    else{
        return find(root->right, value);
    }
};

struct node* delete(struct node *root, int value){
    if(root == NULL){
        return NULL;
    }
    else if(value > root->value){
        root->right = delete(root->right, value);
    }
    else if(value < root->value){
        root->left = delete(root->left, value);  
    }
    else{
        if(root->left == NULL && root->right == NULL){
            return NULL;
        }
        else if(root->left == NULL || root->right == NULL){
            if(root->left == NULL){
                return root->right;
            }
            else{
                return root->left;  
            }
        }
        else{
            struct node* minNode = root->right;
            struct node* parentNode;
            while(minNode != NULL){
                parentNode = minNode;
                minNode = minNode->left;
            }
            root->value = parentNode->value;
            root->right = delete(root->right, parentNode->value);
        }
    }
}

struct node *traverse(struct node *root){
    if(root != NULL){
        //printf("Here2");
        traverse(root->left);
        printf("Value: %d\n", root->value);
        traverse(root->right);
        //printf("test2");
    }
};


int main(){
    //printf("here");
    int nodes[] = {57, 10, 93, 88, 77, 74, 78};
    size_t nodesLength = sizeof(nodes) / sizeof(nodes[0]);
    char line[200];
    char name[50];
    char *names[5];
    FILE *file = fopen("data.txt", "r");

    int i = 0;
    int nameIndex = 0;
    while (fgets(line, 100, file) != NULL) {
        while(line[i] != ','){
            //printf("%c", line[i]);
            name[i] = line[i];
            i++;
        }
        i = 0;
        printf("%s\n", name);
        memset(&name[0], 0, sizeof(name));
        strcpy(&names[nameIndex], name);
        nameIndex++;
    }

    printf("%s", names[2]);
    
    //fclose(file);

    // struct node* root = NULL;

    // for(int i=0; i < nodesLength; i++){
    //     root = add(root, nodes[i]);
    // }
    // printf("Root: %d\n", root->left->value);
    // traverse(root);

    // struct node* found = find(root, 93);
    // printf("Found value: %d\n", found->value);


    // delete(root, 74);
    // traverse(root);
}

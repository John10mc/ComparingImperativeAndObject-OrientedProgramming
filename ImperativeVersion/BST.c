#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct node{
    struct node* left;
    struct node *right;
    char *value;
};

struct tree{
    struct node root;
};

int compareTo(char *s1, char *s2){
    size_t lenS1 = strlen(s1);
    size_t lenS2 = strlen(s2);
    int len;
    if(lenS1 > lenS2){
        len = lenS2;
    }
    else{
        len = lenS1;
    }
    for(int i = 0; i <= len; i++){
        if(s1[i] > s2[i]){
            return 1;
        }
        else if(s1[i] < s2[i]){
            return -1;
        }
    }
    if(len == lenS2){
        return 0;
    }
    else if(lenS1 > lenS2){
        return 1;
    }
    else{
        return -1;
    }
}

struct node* add(struct node *root, char *value){
    if(root == NULL){
        struct node *newNode = (struct node*)malloc(sizeof(struct node));
        (newNode)->value = malloc(sizeof(value));
        strcpy((newNode)->value, value);
        //newNode->value = value;
        //printf("Value: %1s", newNode->value);
        return newNode;
    }
    else if(compareTo(value, root->value) == 1){
        //printf("Left");
        root->left = add(root->left, value);
    }
    else{
        //printf("Right");
        root->right = add(root->right, value);
    }
};

struct node* find(struct node *root, char *value){
    if(compareTo(value, root->value) == 0){
        return root;
    }
    else if(compareTo(value, root->value) == -1){
        return find(root->left, value);
    }
    else{
        return find(root->right, value);
    }
};

struct node* delete(struct node *root, char *value){
    if(root == NULL){
        return NULL;
    }
    else if(compareTo(value, root->value) == -1){
        root->right = delete(root->right, value);
    }
    else if(compareTo(value, root->value) == 1){
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
        printf("Value: %s\n", root->value);
        traverse(root->right);
        //printf("test2");
    }
};

int main(){
    char line[200];
    char name[50];
    struct node* root = NULL;
    FILE *file = fopen("data.txt", "r");

    int i = 0;
    int nameIndex = 0;
    while (fgets(line, 100, file) != NULL) {
        while(line[i] != ','){
            //printf("%c", line[i]);
            name[i] = line[i];
            i++;
        }
        root = add(root, name);
        //printf("%s\n", name);
        memset(&name[0], 0, sizeof(name));
        i = 0;
    }

    
    //fclose(file);

    //printf("Root: %s\n", root->left->value);
    traverse(root);

    struct node* found = find(root, "James");
    printf("Found value: %s\n", found->value);


    delete(root, "James");
    traverse(root);
}

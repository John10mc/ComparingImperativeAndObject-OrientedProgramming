#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

struct node{
    char *name;
    char *address;
    char *number;
};

struct tree{
    struct node *record;
    char *id;
    struct tree *left;
    struct tree *right;
};

struct binarySearchTree{
    struct tree *numberTree;
    struct tree *nameTree;
};

// compareTo is used to compare two strings ard return either -1, 0, 1 for less than, equal and greater than respectively
// based on the lexicographical order of the strings
int compareTo(char *s1, char *s2){
    size_t lenS1 = strlen(s1);
    size_t lenS2 = strlen(s2);
    int len;

    //find the shortest string
    if(lenS1 > lenS2){
        len = lenS2;
    }
    else{
        len = lenS1;
    }

    // loop for the length of the shortest string
    for(int i = 0; i < len; i++){

        // return if the characters differ
        if(s1[i] > s2[i]){
            return 1;
        }
        else if(s1[i] < s2[i]){
            return -1;
        }
    }

    // return when the length of the shortest has be looped through
    // same string
    if(len == lenS2){
        return 0;
    }
    // strings up to shortest string are the same but one is longer
    else if(lenS1 > lenS2){
        return 1;
    }
    else{
        return -1;
    }
};

// recFind is used to find if an element is in the tree and return if it is.
struct tree* recFind(struct tree *root, char *id){
    // element not found. At leaf node or root
    if(root == NULL){
        return NULL;
    }

    // if strings are the same return the root of that sub tree
    else if(compareTo(id, root->id) == 0){
        return root;
    }

    // if search string is less than the roots string then search from the next left sub tree
    else if(compareTo(id, root->id) == -1){
        return recFind(root->left, id);
    }

    // if search string is greater than the roots string then search from the next right sub tree
    else{
        return recFind(root->right, id);
    }
};

// find calls recFind with either the nameTree or numberTree based on the first character of the search string
void find(struct binarySearchTree *bst, char *id){
    struct tree* found;

    // check if the first character is a letter. If it is use the nameTree to search
    if(isalpha(id[0])){
        found = recFind(bst->nameTree, id);
    }

    // use the numberTree to search
    else{
        found = recFind(bst->numberTree, id);
    }

    // if the search string is found in either of the trees print out its attributes else print it couldnt be found
    if(found != NULL){
        printf("Found record: %s, %s, %s.\n",(found)->record->name, (found)->record->address, (found)->record->number);
    }
    else{
        printf("Could not find record: %s.\n", id);
    }
};

// recAdd is used to traverse a tree and find the approiate place to add the new node
struct tree* recAdd(struct tree *root, struct node *newNode, char *value){

    // found a leaf node or the root, add at this point
    if(root == NULL){

        //allocate the needed space in memory for the size of a tree and store a pointer to it
        struct tree *newTree = (struct tree*)malloc(sizeof(struct tree));

        // in the memory allocated above store the pointer of newNode to the record pointer and and the value pointer to the id pointer
        // then return the newTree pointer
        newTree->record = newNode;
        newTree->id = value;
        return newTree;
    }

    // if value string is less than the roots string then continue from the next left sub tree
    else if(compareTo(value, root->id) == -1){
        root->left = recAdd(root->left, newNode, value);
    }

    // if value string is greater than the roots string then continue from the next right sub tree
    else{
        root->right = recAdd(root->right, newNode, value);
    }
};

// add function calls recAdd for both the numberTree and nameTree with the appropriate value to find the right position
void add(struct binarySearchTree *bst, struct node *newNode){

    // check to see that the record doesnt exist in the trees already
    if(recFind(bst->nameTree, newNode->name) == NULL){
        // find the appropriate leaf node in numberTree and add the newNode there then store the new tree
        bst->numberTree = recAdd(bst->numberTree, newNode, newNode->number);
        // find the appropriate leaf node in nameTree and add the newNode there then store the new tree
        bst->nameTree = recAdd(bst->nameTree, newNode, newNode->name);
        printf("Inserted record: %s, %s, %s\n",(newNode)->name, (newNode)->address, (newNode)->number);
    }
    // node is already in the trees
    else{
        printf("Record: %s, %s, %s already exists in phonebook.\n",(newNode)->name, (newNode)->address, (newNode)->number);
    }
};

// recDelete is used to 
struct tree* recDelete(struct tree *root, char *id){
    if(root == NULL){
        return NULL;
    }
    else if(compareTo(id, root->id) == 1){
        root->right = recDelete(root->right, id);
    }
    else if(compareTo(id, root->id) == -1){
        root->left = recDelete(root->left, id);  
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
            struct tree* minNode = root->right;
            struct tree* parentNode;
            while(minNode != NULL){
                parentNode = minNode;
                minNode = minNode->left;
            }
            root->id = parentNode->id;
            root->record->name = parentNode->record->name;
            root->record->address = parentNode->record->address;
            root->record->number = parentNode->record->number;
            root->right = recDelete(root->right, root->id);
        }
    }
};

struct binarySearchTree* delete(struct binarySearchTree *bst, char *id){
    struct tree* found;
    if(isalpha(id[0])){
        found = recFind(bst->nameTree, id);
    }
    else{
        found = recFind(bst->numberTree, id);
    }
    if(found != NULL){
        printf("Deleted record: %s, %s, %s\n", found->record->name, found->record->address, found->record->number);
        recDelete(bst->nameTree, found->record->name);
        recDelete(bst->numberTree, found->record->number);
    }
    else{
        printf("Could not delete record: %s. Could not be found.\n", id);
    }
};

void *traverse(struct tree *root){
    if(root != NULL){
        //printf("Here2");
        traverse(root->left);
        printf("Record: %s, %s, %s\n", root->record->name, root->record->address, root->record->number);
        traverse(root->right);
        //printf("test2");
    }
};

int main(){
    struct binarySearchTree *bst= (struct binarySearchTree*)malloc(sizeof(struct binarySearchTree));
    struct tree *nameTree = NULL;
    struct tree *numberTree = NULL;
    bst->nameTree = nameTree;
    bst->numberTree = numberTree;

    char line[200];
    char address[100];
    char name[50];
    char number[50];
    FILE *file = fopen("../data.txt", "r");

    memset(&name[0], 0, sizeof(name));
    memset(&address[0], 0, sizeof(address));
    memset(&number[0], 0, sizeof(number));
    memset(&line[0], 0, sizeof(line));

    int i = 0;
    int j = 0;
    while (fgets(line, 200, file) != NULL) {
        while(line[i] != ','){
            name[j] = line[i];
            i++;
            j++;
        }
        i += 2;
        j = 0;
        while(line[i] != ','){
            address[j] = line[i];
            i++;
            j++;
        }
        i += 2;
        j = 0;
        while(line[i] != '\0'){
            number[j] = line[i];
            i++;
            j++;
        }

        number[strcspn(number, "\n")] = 0;

        struct node *newNode = (struct node*)malloc(sizeof(struct node));
        (newNode)->name = malloc(sizeof(name));
        (newNode)->address = malloc(sizeof(address));
        (newNode)->number = malloc(sizeof(number));

        strcpy((newNode)->name, name);
        strcpy((newNode)->address, address);
        strcpy((newNode)->number, number);

        add(bst, newNode);
        //free(newNode);

        memset(&name[0], 0, sizeof(name));
        memset(&address[0], 0, sizeof(address));
        memset(&number[0], 0, sizeof(number));
        memset(&line[0], 0, sizeof(line));
        i = 0;
        j = 0;
    }

        printf("\n");
        traverse(bst->nameTree);
        printf("\n");
        traverse(bst->numberTree);

        printf("\n");
        find(bst, "Max");
        printf("\n");
        find(bst, "305-896-0661");
        printf("\n");
        find(bst, "Brian Ruble");
        printf("\n");

        traverse(bst->nameTree);
        printf("\n");

        delete(bst, "Brian Ruble");
        printf("\n");
        traverse(bst->nameTree);
        printf("\n");
        traverse(bst->numberTree);
        printf("\n");

        delete(bst, "Brian Ruble");
        printf("\n");

        traverse(bst->numberTree);
        printf("\n");

        delete(bst, "305-896-0661");
        printf("\n");
        traverse(bst->nameTree);
        printf("\n");
        traverse(bst->numberTree);
        printf("\n");

        delete(bst, "305-896-0661");
        printf("\n");

    free(bst);
};



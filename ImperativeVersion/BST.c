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

// struct bst{
//     struct tree *numberTree;
//     struct tree 
// }

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
};

struct tree* recFind(struct tree *root, char *id){
    if(root == NULL){
        return NULL;
    }
    else if(compareTo(id, root->id) == 0){
        return root;
    }
    else if(compareTo(id, root->id) == -1){
        return recFind(root->left, id);
    }
    else{
        return recFind(root->right, id);
    }
};

struct tree* find(struct tree *nameTree, struct tree *numberTree, char *id){
    struct tree* found;
    if(isalpha(id[0])){
        found = recFind(nameTree, id);
    }
    else{
        found = recFind(numberTree, id);
    }
    if(found != NULL){
        printf("Found record: %s, %s, %s.\n",(found)->record->name, (found)->record->address, (found)->record->number);
    }
    else{
        printf("Could not find record: %s.\n", id);
    }
}

struct tree* add(struct tree *root, struct node *newNode, char *value){
    if(root == NULL){
        struct tree *newTree = (struct tree*)malloc(sizeof(struct tree));
        newTree->record = newNode;
        newTree->id = value;

        return newTree;
    }
    else if(compareTo(value, root->id) == -1){
        //printf("Left");
        root->left = add(root->left, newNode, value);
    }
    else if(compareTo(value, root->id) == 1){
        //printf("Right");
        root->right = add(root->right, newNode, value);
    }
    else{
        
        return root;
    }
};

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
            root->right = recDelete(root->right, parentNode->id);
        }
    }
};

struct tree* delete(struct tree *nameTree, struct tree *numberTree, char *id){
    struct tree* found;
    if(isalpha(id[0])){
        found = recFind(nameTree, id);
    }
    else{
        found = recFind(numberTree, id);
    }
    if(found != NULL){
        recDelete(nameTree, found->record->name);
        recDelete(numberTree, found->record->number);
        printf("Deleted record: %s, %s, %s\n", found->record->name, found->record->address, found->record->number);
    }
    else{
        printf("Could not delete record: %s. Could not be found.\n", id);
    }

};

struct node *traverse(struct tree *root){
    if(root != NULL){
        //printf("Here2");
        traverse(root->left);
        printf("Record: %s, %s, %s\n", root->record->name, root->record->address, root->record->number);
        traverse(root->right);
        //printf("test2");
    }
};

int main(){
    char line[200];
    char name[50];
    char address[100];
    char number[50];
    struct tree* nameTree = NULL;
    struct tree* numberTree = NULL;
    FILE *file = fopen("data.txt", "r");

    int i = 0;
    int j = 0;
    while (fgets(line, 100, file) != NULL) {
        while(line[i] != ','){
            //printf("%c", line[i]);
            name[j] = line[i];
            i++;
            j++;
        }
        i += 2;
        j = 0;
        while(line[i] != ','){
            //printf("%c", line[i]);
            address[j] = line[i];
            i++;
            j++;
        }
        i += 2;
        j = 0;
        while(line[i] != '\0'){
            //printf("%c", line[i]);
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
 
        if(recFind(nameTree, newNode->name) == NULL){
            nameTree = add(nameTree, newNode, newNode->name);
            numberTree = add(numberTree, newNode, newNode->number);
            printf("Inserted record: %s, %s, %s\n",(newNode)->name, (newNode)->address, (newNode)->number);
        }
        else{
            printf("Record: %s, %s, %s already exists in phonebook.\n",(newNode)->name, (newNode)->address, (newNode)->number);
        }

        memset(&name[0], 0, sizeof(name));
        memset(&address[0], 0, sizeof(address));
        memset(&number[0], 0, sizeof(number));
        i = 0;
        j = 0;
    }

    printf("\n");
    traverse(nameTree);
    printf("\n");
    traverse(numberTree);
    // //fclose(file);

    printf("\n");
    find(nameTree, numberTree, "Max");
    printf("\n");

    traverse(nameTree);
    printf("\n");
    delete(nameTree, numberTree ,"James");
    printf("\n");
    traverse(numberTree);
    printf("\n");
    traverse(nameTree);
    printf("\n");
    delete(nameTree, numberTree ,"James");
    printf("\n");

    traverse(numberTree);
    printf("\n");
    delete(nameTree, numberTree ,"530-841-5851");
    printf("\n");
    traverse(nameTree);
    printf("\n");
    traverse(numberTree);
    printf("\n");
    delete(nameTree, numberTree ,"530-841-5851");
    printf("\n");
};

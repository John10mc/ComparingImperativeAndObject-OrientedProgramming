public  class PhoneBookBST extends BST{
    Tree nameTree;
    Tree numberTree;

    // add function calls recAdd for both the numberTree and nameTree with the appropriate value to find the right position
    public void add(Node newNode){

        // check to see that the record doesnt exist in the trees already
        if(recFind(this.nameTree, newNode.getName()) == null){

            // find the appropriate leaf node in nameTree and add the newNode there then store the new tree
            this.nameTree = recAdd(this.nameTree, newNode, newNode.getName());

            // find the appropriate leaf node in numberTree and add the newNode there then store the new tree
            this.numberTree = recAdd(this.numberTree, newNode, newNode.getNumber());
            System.out.printf("Inserted record: %s\n", newNode);
        }

        // node is already in the trees
        else{
            System.out.printf("Record: %s already exists in phonebook.\n", newNode);
        }
    }

    // delete function calls recDelete for both the numberTree and nameTree with the appropriate value to delete the right sub tree
    public void delete(String id){
        Tree foundNumber = new Tree();
        Tree foundName = new Tree();

        // check if the id of the node to be deleted is a phone number. Find the record that has to be deleted
        try{
            Integer.parseInt(id.substring(0, 1));
            foundNumber = recFind(this.numberTree, id);

            // number has to found in the number tree to search the name tree
            if(foundNumber != null){
                foundName = recFind(this.nameTree, foundNumber.getRecord().getName());
            }
        }
        catch(NumberFormatException error){
            foundName = recFind(this.nameTree, id);

            // name has to found in the name tree to search the number tree
            if(foundName != null){
                foundNumber = recFind(this.numberTree, foundName.getRecord().getNumber());
            }
        }

        //if the record was found then delete it from both trees
        if(foundName != null && foundNumber != null){

            System.out.printf("Deleted record: %s\n", foundName.getRecord());
            nameTree = recDelete(this.nameTree, foundName.getRecord().getName());
            numberTree = recDelete(this.numberTree, foundNumber.getRecord().getNumber());
        }

        // if it couldnt be found print that it couldnt be found
        else{
            System.out.printf("Could not delete record: %s. Could not be found.\n", id);
        }
    }

    // find calls recFind with either the nameTree or numberTree based on the first character of the search string
    public void find(String id){
        Tree found;

        // check if the first character is a number. If it is, use the numberTree to search
        try{
            Integer.parseInt(id.substring(0, 1));
            found = recFind(this.numberTree, id);
        }

        // use the numberTree to search
        catch(NumberFormatException error){
            found = recFind(this.nameTree, id);
        }

        // if the search string is found in either of the trees print out its attributes else print it couldnt be found
        if(found != null){
            System.out.printf("Found record: %s.\n",found.getRecord());
        }
        else{
            System.out.printf("Could not find record: %s.\n", id);
        }
    }

    // call to recTraverse the tree from smallest to highest and print the record
    public void traverse(Tree root){
        recTraverse(root);
    }
}
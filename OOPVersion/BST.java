public class BST{
    Tree nameTree;
    Tree numberTree;

    public BST(){
        this.nameTree = null;
        this.numberTree = null;
    }

        public void add(Node newNode){
        if(recFind(this.nameTree, newNode.getName()) == null){
            this.nameTree = recAdd(this.nameTree, newNode, newNode.getName());
            this.numberTree = recAdd(this.numberTree, newNode, newNode.getNumber());
            System.out.printf("Inserted record: %s\n", newNode);
        }
        else{
            System.out.printf("Record: %s already exists in phonebook.\n", newNode);
        }
    }


    public Tree recAdd(Tree root, Node newNode, String id){
        //System.out.println(root);
        if(root == null){
            Tree newTree = new Tree();
            newTree.setRecord(newNode);
            newTree.setID(id);
            return newTree;
        }
        else if(id.compareTo(root.getID()) < 0){
            root.setLeft(recAdd(root.getLeft(), newNode, id));
        }
        else{
            root.setRight(recAdd(root.getRight(), newNode, id));
        }
        return root;
    }

    public void delete(String id){
        Tree foundNumber = new Tree();
        Tree foundName = new Tree();
        try{
            Integer.parseInt(id.substring(0, 1));
            foundNumber = recFind(this.numberTree, id);
            if(foundNumber != null){
                foundName = recFind(this.nameTree, foundNumber.getRecord().getName());
            }
        }
        catch(NumberFormatException error){
            foundName = recFind(this.nameTree, id);
            if(foundName != null){
                foundNumber = recFind(this.numberTree, foundName.getRecord().getNumber());
            }
        }
        if(foundName != null && foundNumber != null){

            System.out.printf("Deleted record: %s\n", foundName.getRecord());
            nameTree = recDelete(this.nameTree, foundName.getRecord().getName()); // try get to from diffrent node
            numberTree = recDelete(this.numberTree, foundNumber.getRecord().getNumber());
        }
        else{
            System.out.printf("Could not delete record: %s. Could not be found.\n", id);
        }
    }

    public Tree recDelete(Tree root, String id){
        if(root == null){
            return null;
        }
        else if(id.compareTo(root.getID()) > 0){
            root.setRight(recDelete(root.getRight(), id));
        }
        else if(id.compareTo(root.getID()) < 0){
            root.setLeft(recDelete(root.getLeft(), id));
        }
        else{
            if(root.getLeft() == null && root.getRight() == null){
                return null;
            }
            else if(root.getLeft() == null ^ root.getRight() == null){
                    if(root.getLeft() == null){
                        return root.getRight();
                    }
                    else{
                        return root.getLeft();
                    }
            }
            else{
                Tree minNode = root.getRight();
                Tree parentNode = null;
                while(minNode != null){
                    parentNode = minNode;
                    minNode = minNode.getLeft();
                }
                root.setID(parentNode.getID());
                root.setRecord(parentNode.getRecord());
                root.setRight(recDelete(root.getRight(), parentNode.getID()));
            }
        }
        return root;
    }

    public void find(String id){
        Tree found;
        try{
            Integer.parseInt(id.substring(0, 1));
            found = recFind(this.numberTree, id);
        }
        catch(NumberFormatException error){
            found = recFind(this.nameTree, id);
        }
        if(found != null){
            System.out.printf("Found record: %s.\n",found.getRecord());
        }
        else{
            System.out.printf("Could not find record: %s.\n", id);
        }
    }

    public Tree recFind(Tree root, String id){
        if(root == null){
            return null;
        }
        else if(id.compareTo(root.getID()) == 0){
            return root;
        }
        else if(id.compareTo(root.getID()) < 0){
            return recFind(root.getLeft(), id);
        }
        else{
            return recFind(root.getRight(), id);
        }
    }


    public void traverse(Tree root){
        recTraverse(root);
    }

    public void recTraverse(Tree root){
        if(root != null){
            recTraverse(root.getLeft());
            System.out.printf("Record: " + root.getRecord() + "\n");
            recTraverse(root.getRight());
        }
    }

    public int compareTo(String s1, String s2){
        int lenS1 = s1.length();
        int lenS2 = s2.length();
        int len;
        if(lenS1 > lenS2){
            len = lenS2;
        }
        else{
            len = lenS1;
        }
        for(int i = 0; i < len; i++){
            if(s1.charAt(i) > s2.charAt(i)){
                return 1;
            }
            else if(s1.charAt(i) < s2.charAt(i)){
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
}
public abstract class BST{

    public abstract void add(Node newNode);

    public abstract void delete(String id);

    public abstract void find(String id);

    public abstract void traverse(Tree root);

    // recAdd is used to traverse a tree and find the approiate place to add the new node
    public Tree recAdd(Tree root, Node newNode, String id){

        // found a leaf node or the root, add at this point
        if(root == null){
            // create a new sub tree, set the record to the newNode and the sub trees id, and return it
            Tree newTree = new Tree();
            newTree.setRecord(newNode);
            newTree.setID(id);
            return newTree;
        }

        // if value string is less than the roots string then continue from the next left sub tree
        else if(id.compareTo(root.getID()) < 0){
            root.setLeft(recAdd(root.getLeft(), newNode, id));
        }

        // if value string is greater than the roots string then continue from the next right sub tree
        else{
            root.setRight(recAdd(root.getRight(), newNode, id));
        }
        return root;
    }

    // recDelete is used to find the position of a node and delete if from the tree
    public Tree recDelete(Tree root, String id){
        if(root == null){
            return null;
        }

        // if id string is greater than the roots string then continue from the next right sub tree 
        else if(id.compareTo(root.getID()) > 0){
            root.setRight(recDelete(root.getRight(), id));
        }

        // if id string is less than the roots string then continue from the next left sub tree 
        else if(id.compareTo(root.getID()) < 0){
            root.setLeft(recDelete(root.getLeft(), id));
        }

        // sub tree to delete was found
        else{

            // if both the left and right sub tree roots are empty remove the current sub tree
            if(root.getLeft() == null && root.getRight() == null){
                return null;
            }

            // if either the left or the right is empty remove the left or right respectively
            else if(root.getLeft() == null ^ root.getRight() == null){
                    if(root.getLeft() == null){
                        return root.getRight();
                    }
                    else{
                        return root.getLeft();
                    }
            }

            // both left are right are not empty
            else{
                // find the smallest node to the right of the current node
                Tree minNode = root.getRight();
                Tree parentNode = null;
                while(minNode != null){
                    parentNode = minNode;
                    minNode = minNode.getLeft();
                }
                // update the id, set the node to be deleted to the smallest node and delete the smallest node
                root.setID(parentNode.getID());
                root.setRecord(parentNode.getRecord());
                root.setRight(recDelete(root.getRight(), parentNode.getID()));
            }
        }
        return root;
    }

    // recFind is used to find if an element is in the tree and return if it is.
    public Tree recFind(Tree root, String id){

        // element not found
        if(root == null){
            return null;
        }

        // if strings are the same return the root of that sub tree
        else if(id.compareTo(root.getID()) == 0){
            return root;
        }

        // if search string is less than the roots string then search from the next left sub tree
        else if(id.compareTo(root.getID()) < 0){
            return recFind(root.getLeft(), id);
        }

        // if search string is greater than the roots string then search from the next right sub tree
        else{
            return recFind(root.getRight(), id);
        }
    }

    // traverse the tree from smallest to highest and print the record
    public void recTraverse(Tree root){
        if(root != null){
            recTraverse(root.getLeft());
            System.out.printf("Record: " + root.getRecord() + "\n");
            recTraverse(root.getRight());
        }
    }
}
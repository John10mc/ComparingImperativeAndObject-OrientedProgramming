public class Tree{
    private String id;
    private Tree left;
    private Tree right;
    private Node record;

    public Tree(){
        this.id = null;
        this.left = null;
        this.right = null;
        this.record = null;
    }

    public Node getRecord(){
        return this.record;
    }

    public String getID(){
        return this.id;
    }

    public Tree getLeft(){
        return this.left;
    }

    public Tree getRight(){
        return this.right;
    }

    public void setRecord(Node record){
        this.record = record;
    }

    public void setID(String id){
        this.id = id;
    }

    public void setLeft(Tree left){
        this.left = left;
    }

    public void setRight(Tree right){
        this.right = right;
    }

}
import java.io.File; 
import java.util.Scanner;

public class NameBST extends BST{
	Node root;

	public NameBST(){
		root = null;
	}

	@Override
	public void add(Node node){
		this.root = this.recAdd(this.root, node, node.name);
	}

	@Override
	public Node find(String name){
		return this.recFind(this.root, name);
	}

	@Override
	public void delete(String name){
		this.deleteNode(this.root, name);
	}

	@Override
	public void traverse(){
		this.recTraverse(this.root);
	}

}
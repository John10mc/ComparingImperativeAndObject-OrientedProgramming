import java.io.File; 
import java.util.Scanner;

public class NameBST extends BST{
	Node root;

	public NameBST(){
		root = null;
	}

	@Override
	public void add(Node node){
		this.root = this.recAdd(this.root, node, node.name, false);
	}

	@Override
	public Node find(String name){
		return this.recFind(this.root, name, false);
	}

	@Override
	public void delete(String name){
		this.root = this.recDelete(this.root, name, false);
	}

	@Override
	public void traverse(){
		this.recTraverse(this.root, false);
	}

}
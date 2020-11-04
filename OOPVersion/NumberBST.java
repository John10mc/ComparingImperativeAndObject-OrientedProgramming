import java.io.File; 
import java.util.Scanner;

public class NumberBST extends BST{
	Node root;

	public NumberBST(){
		root = null;
	}

	@Override
	public void add(Node node){
		this.root = this.recAdd(this.root, node, node.number, true);
	}

	@Override
	public Node find(String number){
		return this.recFind(this.root, number, true);
	}

	@Override
	public void delete(String number){
		this.root = this.recDelete(this.root, number, true);
	}

	@Override
	public void traverse(){
		this.recTraverse(this.root, true);
	}
}
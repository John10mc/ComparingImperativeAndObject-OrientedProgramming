public class BST{
	Node root;

	public BST(){
		root = null;
	}

	public void add(String value){
		this.root = this.recAdd(this.root, value);
	}

	public static Node recAdd(Node node, String value){
		if(node == null){
			return new Node(value);
		}
		if(node.isGreaterThan(value)){
			node.left = recAdd(node.left, value);
		}
		else{
			node.right = recAdd(node.right, value);
		}
		//Check to see why its producing an erroe
		//System.out.print("Error" + value + node.isGreaterThan(value) + "\n");
		return node;
	}

	public boolean contains(String check){
		return this.recContains(this.root, check);
	}

	public static boolean recContains(Node node, String check){
		if(node == null){
			return false;
		}
		else if(node.value.equals(check)){
			return true;
		}
		else if(node.isGreaterThan(check)){
			return recContains(node.left, check);
		}
		else{
			return recContains(node.right, check);
		}
	}

	public void delete(String value){
		Node node = this.root;
		Node parent = node;
		if(node.isEqual(value)){
			if(node.left != null){
				this.root = node.left;
			}
			else if(node.right != null){
				this.root = node.right;
			}
			else{
				this.root = null;
			}
		}
		while(!(node.isEqual(value))){
			if(node.isLessThan(value)){
				parent = node;
				node = node.right;
			}
			else{
				parent = node;
				node = node.left;
			}
			//System.out.print("\n" + node.value);
		}
		if(node.left == null && node.right == null){
			//System.out.print("Here");
			if(parent.left.isEqual(value)){
				parent.left = null;
			}
			else{
				parent.right = null;
			}
		}

		else if(node.left == null ^ node.right == null){
			if(node.left == null){
				parent.left = node.right;
			}
			else{
				//System.out.print("Here");
				parent.right = node.left;	
			}
		}
		else{
			Node minNode = node.right;
			Node parentMinNode = minNode;
			while(minNode.left != null){
				parentMinNode = minNode;
				minNode = minNode.left;
			}
			minNode.right = node.right;
			minNode.left = node.left;
			if(parent.left.isEqual(value)){
				parent.left = minNode;

			}
			else{
				parent.right = minNode;
			}
			parentMinNode.left = null;
		}
	}

	public void recTraverse(Node node){
		if(node != null){
			this.recTraverse(node.left);
			System.out.print(node.value + " ");
			this.recTraverse(node.right);
		}
	}

	public static void main(String[] args){
	BST tree = new BST();
	String[] inserts = {"0871234567", "0875678901", "0872345678", "0873456789", "0874567890"};
	for(int i=0; i<inserts.length; i++){
		tree.add(inserts[i]);
		System.out.print(inserts[i] + " ");
	}
	System.out.print("\n");
	tree.recTraverse(tree.root);
	tree.delete("0871234567");
	System.out.println();
	tree.recTraverse(tree.root);
	System.out.print("\n");
	System.out.print(tree.contains("0872345678") + "\n");
	System.out.print(tree.contains("0872345671"));
	}

}
public abstract class BST{
	Node root;

	public BST(){
		root = null;
	}

	public abstract void add(Node node);

	public abstract Node find(String value);

	public abstract void delete(String value);

	public abstract void traverse();

	public Node recAdd(Node node, Node newNode, String value){
		if(node == null){
			return newNode;
		}
		if(node.isGreaterThan(value)){
			node.left = recAdd(node.left, newNode, value);
		}
		else{
			node.right = recAdd(node.right, newNode, value);
		}
		//Check to see why its producing an error
		//System.out.print("Error" + value + node.isGreaterThan(value) + "\n");
		return node;
	}

	public Node recFind(Node node, String value){
		if(node == null){
			return node;
		}
		// try using isEqual
		else if(node.id.equals(value)){
			return node;
		}
		else if(node.isGreaterThan(value)){
			return recFind(node.left, value);
		}
		else{
			return recFind(node.right, value);
		}
	}

	public void deleteNode(Node node, String value){
		Node parent = node;
		if(node.isEqual(value)){
			if(node.left != null){
				node = node.left;	
			}
			else if(node.right != null){
				node = node.right;
			}
			else{
				node = null;
			}
		}
		else{
			while(!(node.isEqual(value))){
				if(node.isLessThan(value)){
					parent = node;
					node = node.right;
				}
				else{
					parent = node;
					node = node.left;
				}
			}

			if(node.left == null && node.right == null){
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
	}

	public void recTraverse(Node node){
		if(node != null){
			this.recTraverse(node.left);
			System.out.print(node);
			this.recTraverse(node.right);
		}
	}

}
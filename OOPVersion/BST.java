public abstract class BST{

	public abstract void add(Node node);

	public abstract Node find(String value);

	public abstract void delete(String value);

	public abstract void traverse();

	public Node recAdd(Node node, Node newNode, String value, boolean isNumber){
		if(node == null){
			return newNode;
		}
		if(node.isGreaterThan(value, isNumber)){
			node.setLeft(recAdd(node.getLeft(isNumber), newNode, value, isNumber), isNumber);
		}
		else{
			node.setRight(recAdd(node.getRight(isNumber), newNode, value, isNumber), isNumber);
		}
		//Check to see why its producing an error
		//System.out.print("Error" + value + node.isGreaterThan(value) + "\n");
		return node;
	}

	public Node recFind(Node node, String value, boolean isNumber){
		if(node == null){
			return node;
		}
		// try using isEqual
		else if(node.isEqual(value, isNumber)){
			return node;
		}
		else if(node.isGreaterThan(value, isNumber)){
			return recFind(node.getLeft(isNumber), value, isNumber);
		}
		else{
			return recFind(node.getRight(isNumber), value, isNumber);
		}
	}

	public Node recDelete(Node node, String value, boolean isNumber){
		if (node == null) {
            return null;
        }
     
        if (node.isEqual(value, isNumber)) {
            if (node.getLeft(isNumber) == null && node.getRight(isNumber) == null) {
                return null;
            }
            else if (node.getLeft(isNumber) == null ^ node.getRight(isNumber) == null) {
                if(node.getLeft(isNumber) == null){
                    return node.getRight(isNumber);
                }
                else{
                    return node.getLeft(isNumber);
                }
            }
            else{
                Node minNode = node.getRight(isNumber);
                Node parent = null;
                while(minNode != null){
                    parent = minNode;
                    minNode = minNode.getLeft(isNumber);
                }
                node.setName(parent.getName());
                node.setNumber(parent.getNumber());
                node.setAddress(parent.getAddress());
                node.setRight(recDelete(node.getRight(isNumber), node.getValue(isNumber), isNumber), isNumber);
                return node;
            }
        } 
        else if(node.isGreaterThan(value, isNumber)) {
            node.setLeft(recDelete(node.getLeft(isNumber), value, isNumber), isNumber);
            return node;
        }
        else{
            node.setRight(recDelete(node.getRight(isNumber), value, isNumber), isNumber);
            return node;
        }
    }

	public void recTraverse(Node node, boolean isNumber){
		if(node != null){
			this.recTraverse(node.getLeft(isNumber), isNumber);
			System.out.print(node);
			this.recTraverse(node.getRight(isNumber), isNumber);
		}
	}

}
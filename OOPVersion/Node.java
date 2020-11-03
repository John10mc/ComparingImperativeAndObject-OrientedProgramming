public class Node{
	String value;
	Node left;
	Node right;

	public Node(String value){
		this.value = value;
		left = null;
		right = null;
	}

	public boolean isGreaterThan(String value){
		if(this.value.compareTo(value) > 0){
			return true;
		}
		else{
			return false;
		}
	}

	public boolean isLessThan(String value){
		if(this.value.compareTo(value) < 0){
			return true;
		}
		else{
			return false;
		}
	}

	public boolean isEqual(String node){
		return value.equals(this.value);
	}
}

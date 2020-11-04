public class Node{
	String id;
	String name;
	String address;
	String number;
	Node left;
	Node right;

	public Node(String id, String name, String address, String number){
		this.id = id;
		this.name = name;
		this.address = address;
		this.number = number;
		left = null;
		right = null;
	}

	public boolean isGreaterThan(String value){
		if(this.id.compareTo(value) > 0){
			//System.out.println(this.id.compareTo(value) + " " + this.id + " " + value);
			return true;
		}
		else{
			return false;
		}
	}

	public boolean isLessThan(String value){
		if(this.id.compareTo(value) < 0){
			return true;
		}
		else{
			return false;
		}
	}

	public boolean isEqual(String value){
		return this.id.equals(value);
	}

	public String toString(){
		return "Name: " + this.name + "\n" + "Address:" + this.address  + "\n" + "Number:" + this.number + "\n\n";
	}
}

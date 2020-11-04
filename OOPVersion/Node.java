public class Node{
	String name;
	String address;
	String number;
	Node nameLeft;
	Node nameRight;
    Node numberLeft;
    Node numberRight;

	public Node(String name, String address, String number){
		this.name = name;
		this.address = address;
		this.number = number;
        nameLeft = null;
        nameRight = null;
        numberLeft = null;
        numberRight = null;
	}

    public void setName(String name){
        this.name = name;
    }

    public void setNumber(String address){
        this.number = number;
    }

    public void setAddress(String number){
        this.address = address;
    }

    public String getName(){
        return this.name;
    }

    public String getNumber(){
        return this.number;
    }

    public String getAddress(){
        return this.address;
    }

    public String getValue(boolean isNumber){
        if(isNumber){
            return this.number;
        }
        else{
            return this.name;
        }
    }

    public Node getLeft(boolean isNumber){
        if(isNumber){
            return this.numberLeft;
        }
        else{
            return this.nameLeft;
        }
    }

    public Node getRight(boolean isNumber){
        if(isNumber){
            return this.numberRight;
        }
        else{
            return this.nameRight;
        }
    }

    public void setLeft(Node node, boolean isNumber){
        if(isNumber){
            this.numberLeft = node;
        }
        else{
            this.nameLeft = node;
        }
    }

    public void setRight(Node node, boolean isNumber){
        if(isNumber){
            this.numberRight = node;
        }
        else{
            this.nameRight = node;
        }
    }

	public boolean isGreaterThan(String value, boolean isNumber){
        if(isNumber){
            return this.number.compareTo(value) < 0;
        }
        else{
            return this.name.compareTo(value) < 0;
        }
	}

	public boolean isEqual(String value, boolean isNumber){
		if(isNumber){
            return this.number.equals(value);
        }
        else{
            return this.name.equals(value);
        }
    }

	public String toString(){
		return "Name: " + this.name + "\n" + "Address:" + this.address  + "\n" + "Number:" + this.number + "\n\n";
	}
}

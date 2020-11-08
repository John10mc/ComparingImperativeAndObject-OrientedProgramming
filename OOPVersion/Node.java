public class Node{
    private String name;
    private String address;
    private String number;

    public Node(String name, String address, String number){
        this.name = name;
        this.address = address;
        this.number = number;
    }

    public String getName(){
        return this.name;
    }

    public String getAddress(){
        return this.address;
    }

    public String getNumber(){
        return this.number;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setNumber(){
        this.number = number;
    }

    public String toString(){
        return this.name + ", " + this.address + ", " + this.number;
    }
}
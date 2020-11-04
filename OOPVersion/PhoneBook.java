import java.io.File; 
import java.util.Scanner;
import java.io.FileNotFoundException;

public class PhoneBook{
	private static BST numberTree;
	private static BST nameTree;

	public PhoneBook(){
		numberTree = new NumberBST();
		nameTree = new NameBST();
	}

	public static void add(String record, BST numberTree, BST nameTree){
		String[] s = record.split(", ");
		String name = s[0];
		String address = s[1];
		String number = s[2];
        Node node = new Node(name, address, number);
		numberTree.add(node);
		nameTree.add(node);
		System.out.println("Inserted record: " + node);
	}

	public static void delete(String value, BST numberTree, BST nameTree){
		try{
			Integer.parseInt(value.substring(0, 1));
			Node record = numberTree.find(value);
			System.out.println(record);
			nameTree.delete(record.name);
			numberTree.delete(value);
		} 
		catch(NumberFormatException error){
			Node record = nameTree.find(value);
			numberTree.delete(record.number);
			nameTree.delete(value);
		}
        System.out.println("Deleted record: " + record);
	}
	public static void find(String value, BST numberTree, BST nameTree){
		try{
			Integer.parseInt(value.substring(0, 1));
			System.out.print(numberTree.find(value));

		} 
		catch(NumberFormatException error){
			System.out.print(nameTree.find(value));
		}
	}

	public static void print(BST nameTree){
		numberTree.traverse();
	}

	public static void main(String[] args){
		PhoneBook phoneBook = new PhoneBook();

		File file = new File("./data.txt");
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException error){
			System.out.println("File not found");
		}

		while(scanner.hasNextLine()) {
			String record = scanner.nextLine();
			add(record, numberTree, nameTree);
		}
		scanner.close();

	    scanner = new Scanner(System.in);
	    System.out.print("\nType add followed by name, address, number to add a record\ne.g add James Toscano, 38 Carolyns Circle Dallas, 214-7462-112\n\n" +
	    	"Type delete followed by a name or number to delete that record\ne.g delete James Toscano\n\n" + 
	    	"Type find followed by a name or a number to find that record\ne.g James Toscano\n\n" +
	    	"Type print to list all records\n\n" +
	    	"Type quit to stop the program\n");
	    boolean keepGoing = true;
	    String command;
		while(keepGoing){
		    command = scanner.nextLine();
    		String[] s = command.split(" ");
			String action = s[0];
		    if(action.equals("add")){
		    	//System.out.println("Test");
		    	add(command.substring(4, command.length()), numberTree, nameTree);
		    }
		    else if(action.equals("delete")){
		    	delete(command.substring(7, command.length()), numberTree, nameTree);
		    }
		    else if(action.equals("find")){
		    	find(command.substring(5, command.length()), numberTree, nameTree);
		    }
		    else if(action.equals("print")){
		    	print(nameTree);
		    }
		    else if(action.equals("quit")){
		    	keepGoing = false;
		    }
		    else{
		    	System.out.println("Command not found. Try again.");
		    }
		}
	}
}
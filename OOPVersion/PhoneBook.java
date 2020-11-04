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
		String[] s = record.split(",");
		String name = s[0];
		String address = s[1];
		String number = s[2];
		numberTree.add(new Node(number, name, address, number));
		nameTree.add(new Node(name, name, address, number));
		System.out.println("Inserted record: " + record);
	}

	public static void delete(String value, BST numberTree, BST nameTree){
		try{
			Integer.parseInt(value.substring(0));
			Node record = numberTree.find(value);
			//System.out.println(record);
			nameTree.delete(record.name);
			numberTree.delete(value);

		} 
		catch(NumberFormatException error){
			Node record = nameTree.find(value);
			//System.out.println(record);
			//numberTree.delete(record.number);
			nameTree.delete(value);
		}
	}
	//// Add look up ///

	public static void print(BST nameTree){
		nameTree.traverse();
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
			//System.out.println("Inserted record: " + data);
		}
		scanner.close();

	    scanner = new Scanner(System.in);
	    System.out.print("\nType add followed by name, address, number to add a record\ne.g add James Toscano, 38 Carolyns Circle Dallas, 214-7462-112\n\n" +
	    	"Type delete followed by a name or number to delete that record\ne.g delete James Toscano\n\n" + 
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
		    else if(action.equals("print")){
		    	print(nameTree);
		    }
		    else if(action.equals("quit")){
		    	keepGoing = false;
		    }
		}
	}
}
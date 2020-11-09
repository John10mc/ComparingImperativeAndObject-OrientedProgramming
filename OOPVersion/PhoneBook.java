import java.io.File; 
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.lang.String;

public class PhoneBook{

    public static void main(String[] args){
        PhoneBookBST bst = new PhoneBookBST();

        // open the data file
        File file = new File("../data.txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException error){
            System.out.println("File not found");
        }

        // read line by line of the file, split it up into name, address, number and create a new node with them
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] splitLine = line.split(", ");
            String name = splitLine[0];
            String address = splitLine[1];
            String number = splitLine[2].trim();
            Node newNode = new Node(name, address, number);

            // add the new node to the trees
            bst.add(newNode);
        }

        // test to see if the records were added properply
        System.out.printf("\n");
        bst.traverse(bst.nameTree);
        System.out.printf("\n");
        bst.traverse(bst.numberTree);

        // test to see if a value can be found in the trees
        System.out.printf("\n");
        bst.find("Max");
        System.out.printf("\n");
        bst.find("305-896-0661");
        System.out.printf("\n");
        bst.find("Brian Ruble");
        System.out.printf("\n");

        bst.traverse(bst.nameTree);
        System.out.printf("\n");

        // test to see if names can be deleted from the trees
        bst.delete("Brian Ruble");
        System.out.printf("\n");
        bst.traverse(bst.nameTree);
        System.out.printf("\n");
        bst.traverse(bst.numberTree);
        System.out.printf("\n");

        // test what happens when you try to delete a value thats not in the tree
        bst.delete("Brian Ruble");
        System.out.printf("\n");

        bst.traverse(bst.numberTree);
        System.out.printf("\n");

        // test to see if numbers can be deleted from the trees
        bst.delete("305-896-0661");
        System.out.printf("\n");
        bst.traverse(bst.nameTree);
        System.out.printf("\n");
        bst.traverse(bst.numberTree);
        System.out.printf("\n");

        bst.delete("305-896-0661");
        System.out.printf("\n");

    }
}
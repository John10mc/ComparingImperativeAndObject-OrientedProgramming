import java.io.File; 
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.lang.String;

public class PhoneBook{

    public static void main(String[] args){
        BST bst = new BST();
        File file = new File("../data.txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException error){
            System.out.println("File not found");
        }

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] splitLine = line.split(", ");
            String name = splitLine[0];
            String address = splitLine[1];
            String number = splitLine[2].trim();
            Node newNode = new Node(name, address, number);
            bst.add(newNode);
        }

        System.out.printf("\n");
        bst.traverse(bst.nameTree);
        System.out.printf("\n");
        bst.traverse(bst.numberTree);

        System.out.printf("\n");
        bst.find("Max");
        System.out.printf("\n");
        bst.find("305-896-0661");
        System.out.printf("\n");
        bst.find("Brian Ruble");
        System.out.printf("\n");

        bst.traverse(bst.nameTree);
        System.out.printf("\n");

        bst.delete("Brian Ruble");
        System.out.printf("\n");
        bst.traverse(bst.nameTree);
        System.out.printf("\n");
        bst.traverse(bst.numberTree);
        System.out.printf("\n");

        bst.delete("Brian Ruble");
        System.out.printf("\n");

        bst.traverse(bst.numberTree);
        System.out.printf("\n");

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
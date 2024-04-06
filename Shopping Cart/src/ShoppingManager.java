import java.util.ArrayList;
import java.util.Scanner;

interface ShoppingManager {  // Defining an interface named ShoppingManager
    void addProduct(Scanner input, ArrayList <Product> item);   // Method to add a product to the shopping list
    void deleteProduct(Scanner input, ArrayList <Product> item);  // Method to delete a product from the shopping list
    void printList(ArrayList <Product> item);   // Method to print the list of products in the shopping list
    void saveFile();   // Method to save the shopping list to a file (implementation not provided here)
}

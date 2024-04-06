import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class WestminsterShoppingManager implements  ShoppingManager{
    static ArrayList <Product> productList = new ArrayList<>();  // Lists to store products
     static ArrayList <User> userList = new ArrayList<>();   // Lists to store users

    public static ArrayList<Product> getProductList() {
        return productList;
    }


    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        WestminsterShoppingManager manager = new WestminsterShoppingManager();

        while (true){

            // Displaying menu options
            System.out.println("\n---  Choose a action you wish to perform ---\n");
            System.out.println("Enter number 1 to Add a new product");
            System.out.println("Enter number 2 to Delete a product");
            System.out.println("Enter number 3 to Print the list of the products");
            System.out.println("Enter number 4 to Save in a file");
            System.out.println("Enter number 5 to load from file");
            System.out.println("Enter number 6 to open User GUI");
            System.out.println("Enter number 999 to exit");
            System.out.println();

            int userInput;

            while (true){
                try {
                    System.out.print("Enter here the action you want to perform :  ");
                    userInput = input.nextInt();
                    System.out.println();
                    break;

                }
                catch (InputMismatchException e){
                    input.nextLine();   // To clear the input stored in the scanner
                    System.out.println(">> Invalid input, Please enter a Number from the menu\n");
                }

            }

            switch (userInput){
                case 1:
                    manager.addProduct(input, productList);
                    break;

                case 2:
                    manager.deleteProduct(input, productList);
                    break;

                case 3:
                    manager.printList(productList);
                    break;

                case 4:
                    manager.saveFile();
                    break;

                case 5:
                    manager.loadFile();
                    break;

                case 6:
                    manager.openGUI();
                    break;

                case 999:
                    System.out.println("Program ended.");
                    return;

                default:
                    System.out.println(">> Please select one from the menu\n");
                    break;
            }
        }
    }

    /**
     * Displays a simple console-based GUI for user registration and login.
     *
     */
    public void openGUI(){

        int option;

        Scanner userInput = new Scanner(System.in);
        while (true) {

            try {
                System.out.print("Press 1 to Register and 2 to Login --->");
                option = userInput.nextInt();
                if(option > 0 && option < 3){
                    break;
                }
                else {
                    System.out.println("Please enter a valid input range\n");
                }
            }catch (Exception e){
                System.out.println("Invalid input\n");
                userInput.nextLine();
            }
        }

        switch (option) {

            case 1:
                System.out.print("Enter a new Username : ");
                String userName = userInput.next();
                System.out.println("Enter a new Password : ");
                String userPassword = userInput.next();

                int count = 0;

                for (User value : userList) {
                    if (value.getUsername().equals(userName)) {
                        System.out.println("User account already exists...");
                        count = 1;
                    }
                }
                if (count != 1) {
                    User user = new User(userName, userPassword);
                    userList.add(user);
                    LayoutPanel GUIObject = new LayoutPanel(productList, user, true);  //Checks if the entered username already exists; if not, creates a new User object
                }
                break;


            case 2:
                System.out.print("Enter your Username : ");
                String userName2 = userInput.next();
                System.out.println("Enter your Password : ");
                String userPassword2 = userInput.next();

                int count2 = 0;

                for (User user : userList) {
                    if (user.getUsername().equals(userName2) && user.getPassword().equals(userPassword2)) {  //Checks if the provided username and password match the credentials of a User object
                        LayoutPanel GUIObject = new LayoutPanel(productList, user, false);
                        count2 = 1;
                    }
                }
                if (count2 != 1){
                    System.out.println("Incorrect Username or Password!");
                }
                break;
        }
    }


    /**
     * Adds a new product to the provided list based on user input.
     *
     * @param input The Scanner object for user input.
     * @param list  The ArrayList to which the new product will be added.
     */
    public void addProduct(Scanner input, ArrayList <Product> list){

        ArrayList <String> idList = new ArrayList<>();  // List to store existing product IDs for validation
        int userOption;
        int stock;
        double cost;

        for (Product product: list){
            idList.add(product.getProductID());
        }

        if(list.size() <= 50) {   // Check if the product list has available space

            String itemID;

            while (true) {
                while (true) {  // Validate user's choice of product type
                    try {
                        System.out.print("""
                        Select the type of product
                        1) Electronics
                        2) Clothing
                        What is the type of product you want to add:""");

                        userOption = input.nextInt();
                        System.out.println();
                        break;
                    }
                    catch (Exception e){
                        System.out.println("\n>>> Invalid input, Please enter only number!\n");
                        input.nextLine();
                    }

                }

                if (userOption == 1 || userOption == 2) {   // Validate and gather product details
                    while (true) {
                        System.out.print("Enter the Product ID: ");
                        itemID = input.next();

                        if (idList.contains(itemID)) {
                            System.out.println("Product ID already present.\n");
                        }
                        else {
                            break;
                        }
                    }

                    System.out.print("Enter the Product Name: ");
                    String itemName = input.next();

                    while (true){  // Validate and gather stock quantity
                        try {
                            System.out.print("Enter the number of stocks you want to add: ");
                            stock = input.nextInt();
                            if(stock>0) {
                                break;
                            }
                            else {
                                System.out.println("\n>>> Please enter a stock number above zero (0) to add!\n");
                            }
                        }catch (Exception e){
                            System.out.println("\n>>> Please enter the number of stocks in number!\n");
                            input.nextLine();
                        }
                    }

                    while (true){  // Validate and gather product cost
                        try {
                            System.out.print("Enter the price of the product: ");
                            cost = input.nextDouble();
                            if(cost > 0){
                                break;
                            }
                            else {
                                System.out.println("\n>>> Please enter a valid cost for the product above zero (0)!\n");
                            }
                        }catch (Exception e){
                            System.out.println("\n>>> Please enter the cost of product in digits!\n");
                            input.nextLine();
                        }
                    }

                    if (userOption == 1) {

                        System.out.print("Enter the brand Name: ");
                        String brandName = input.next();

                        input.nextLine();
                        System.out.print("Warranty period: ");
                        String warranty = input.nextLine();

                        Product product = new Electronics(itemID, itemName, stock, cost, brandName, warranty);
                        list.add(product);

                    } else {

                        System.out.print("Enter the size of the cloth: ");
                        String size = input.next();

                        System.out.print("Enter the colour of the cloth: ");
                        String colour = input.next();

                        Product product = new Clothing(itemID, itemName, stock, cost, size, colour);
                        list.add(product);
                    }
                    System.out.println("\nThe product was successfully added.");
                    break;
                }
                else {
                    input.nextLine();
                    System.out.println(">>> Invalid input");
                    System.out.println("Select the correct product type from the below menu\n");
                }
            }

        }
        else {
            System.out.println("System is full, can't add more products.");
        }
    }


    /**
     * Deletes a product from the provided list based on the specified Product ID.
     * Displays the remaining number of products in the system after the deletion.
     *
     * @param input The Scanner object for user input.
     * @param list  The ArrayList from which the product will be deleted.
     */
    public void deleteProduct(Scanner input, ArrayList <Product> list){

        System.out.print("Enter the Product ID you want to delete: ");
        String delID = input.next();
        boolean isNotFound = true;

        for (Product item: list){   // Iterate through the list to find and delete the product with the specified ID
            if(delID.equals(item.getProductID())){
                list.remove(item);
                isNotFound = false;
                System.out.println("\nProduct Deleted!");
                if (item.getClass() == Electronics.class){
                    System.out.println("Type of product deleted: Electronics");
                }
                else {
                    System.out.println("Type of product deleted: Clothing");
                }

                break;
            }
        }

        if (isNotFound){
            System.out.println("\nThe product with the entered ID is not found.");
        }

        System.out.println("Remaining number of the products in the system : " + list.size());  // Display the remaining number of products in the system
    }

    /**
     * Prints the list of products in a sorted order, if available.
     * If the list is empty, notifies the user that no products have been added.
     *
     * @param list The ArrayList of products to be printed.
     */
    public void printList(ArrayList <Product> list){

        ArrayList <Product> copyOfList = new ArrayList<>(list);  // Create a copy of the list to avoid modifying the original during sorting
        Collections.sort(copyOfList);

        System.out.println("Products added ----> \n");

        if (copyOfList.size() != 0) {  // Check if the sorted list is not empty
            for (Product product : copyOfList) {

                System.out.println(product);
                System.out.println("----------------------------------");
            }
        }
        else{
            System.out.println("No products added");
        }

    }

    /**
     * Overrides the saveFile method from the ShoppingManager interface to save the product list to a file.
     *
     * @throws Exception If an unexpected error occurs during the file-saving process.
     */
    @Override
    public void saveFile(){
        try{
            ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream("productFile.txt")); //ObjectOutputStream for serializing the productList
            writer.writeObject(productList);   // Serialize and write the productList to the file
            writer.close();
            System.out.println("Products are saved in a file successfully.");
        }
        catch (Exception e){
            System.out.println("Unexpected error occurred");
        }
    }

    /**
     * Loads the product list from a file named "productFile.txt" into the system.
     *
     * @throws Exception If an error occurs during the file-loading process.
     */
    public void loadFile(){
        try{
            ObjectInputStream loader = new ObjectInputStream(new FileInputStream("productFile.txt")); // Create ObjectInputStream for deserializing the object from the file
            productList = (ArrayList<Product>) loader.readObject();
            System.out.println("Product List loaded into the system successfully");
            loader.close();
        }
        catch (Exception e){
            System.out.println("Error occurred!");
        }
    }

}

import java.io.Serializable;

abstract class Product implements Comparable <Product>, Serializable {

    private String productID;     //Unique identifier for the product
    private String productName;   //Name of the product
    private int itemsInStock;     // Number of items in stock
    private double price;         // Price of the product
    private int productQuantity;  // Product quantity in an order

    /**
     * Constructor method to create a Product object with specified attributes.
     *
     * @param productID     Unique identifier for the product.
     * @param productName   Name of the product.
     * @param itemsInStock  Number of items in stock.
     * @param price         Price of the product.
     */
    public Product(String productID, String productName, int itemsInStock, double price) {
        this.productID = productID;
        this.productName = productName;
        this.itemsInStock = itemsInStock;
        this.price = price;
    }

    /**
     * Retrieves the product ID for the product.
     *
     * @return The product ID as a String.
     */
    public String getProductID() {
        return productID;
    }

    /**
     * Sets the product ID for the product.
     *
     * @param productID The new product ID to be set.
     */
    public void setProductID(String productID) {
        this.productID = productID;
    }

    /**
     * Retrieves the name of the product.
     *
     * @return The product name as a String.
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Sets the name of the product.
     *
     * @param productName The new product name to be set.
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * Retrieves the number of items currently in stock for the product.
     *
     * @return The number of items in stock as an integer.
     */
    public int getItemsInStock() {
        return itemsInStock;
    }

    /**
     * Sets the number of items currently in stock for the product.
     *
     * @param itemsInStock The new number of items in stock to be set.
     */
    public void setItemsInStock(int itemsInStock) {
        this.itemsInStock = itemsInStock;
    }

    /**
     * Retrieves the price of the product.
     *
     * @return The price of the product as a double.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the product.
     *
     * @param price The new price to be set for the product.
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * Retrieves the quantity of the product in an order.
     *
     * @return The product quantity as an integer.
     */
    public int getProductQuantity() {

        return productQuantity;
    }

    /**
     * Sets the quantity of the product in an order.
     *
     * @param productQuantity The new quantity to be set for the product in an order.
     */
    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    /**
     * Calculates and retrieves the total purchasing cost for the product based on the current quantity and price.
     *
     * @return The total purchasing cost as a double.
     */
    public double getPurchasingCost() {
        return this.productQuantity * this.getPrice();
    }


    /**
     * Generates a string representation of the product, including its type, ID, name, stock quantity, and price.
     *
     * @return A formatted string containing product information.
     */
    @Override
    public String toString() {
        return "Product Type: " + this.getClass().getSimpleName() + "\n" +
                "Product ID: " + getProductID() + "\n" +
                "Product Name: " + getProductName() + "\n" +
                "Number of Stocks: " + getItemsInStock() + "\n" +
                "Product Cost: " + getPrice() + "\n";
    }

    /**
     * Compares this product with another product based on their product IDs to sort in alphabetical order.
     *
     * @param item Another product to compare with.
     * @return A negative integer, zero, or a positive integer, as this product's ID is less than, equal to,
       or greater than the specified product's ID.
     */
    @Override
    public int compareTo(Product item){
        return this.productID.compareTo(item.productID);
    }
}


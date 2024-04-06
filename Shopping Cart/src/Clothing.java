public class Clothing extends Product{
    private String size;     // Size of the clothing product
    private String colour;   // Colour of the clothing product


    /**
     * Constructor method to create a new Clothing object with the specified attributes.
     *
     * @param productID      The unique identifier for the clothing product.
     * @param productName    The name of the clothing product.
     * @param itemsInStock   The quantity of the clothing product available in stock.
     * @param price          The price of the clothing product.
     * @param size           The size of the clothing product.
     * @param colour         The colour of the clothing product.
     */
    public Clothing(String productID, String productName, int itemsInStock, double price, String size, String colour){
        super(productID, productName, itemsInStock, price);
        this.size = size;
        this.colour = colour;
    }

    /**
     * Retrieves the size of the clothing product.
     *
     * @return The size of the clothing product.
     */
    public String getSize() {
        return size;
    }

    /**
     * Sets the size of the clothing product.
     *
     * @param size The new size to be set for the clothing product.
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * Retrieves the colour of the clothing product.
     *
     * @return The colour of the clothing product.
     */
    public String getColour() {
        return colour;
    }

    /**
     * Sets the colour of the clothing product.
     *
     * @param colour The new colour to be set for the clothing product.
     */
    public void setColour(String colour) {
        this.colour = colour;
    }

    /**
     * Generates a formatted string representation of the Clothing object.
     * Overrides the toString method in the superclass (Product).
     *
     * @return A string containing information about the clothing product, including its size and colour.
     *
     */
    @Override
    public String toString() {
        return super.toString() +
                "Product Size: " + getSize() + "\n" +
                "Product Colour: " + getColour() + "\n";
    }

}

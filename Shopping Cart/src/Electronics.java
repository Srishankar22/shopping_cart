public class Electronics extends Product {

    private String brand;             //Brand of the electronic product
    private String warrantyPeriod;    //Warranty period for the electronic product

    /**
     * Constructor method for creating an electronics instance with the given parameter values.
     *
     * @param productID       The unique identifier for the electronic product.
     * @param productName     The name of the electronic product.
     * @param itemsInStock    The quantity of the electronic product in stock.
     * @param price           The price of the electronic product.
     * @param brand           The brand of the electronic product.
     * @param warrantyPeriod  The warranty period of the electronic product.
     */
    public Electronics(String productID, String productName, int itemsInStock, double price, String brand, String warrantyPeriod){
        super(productID, productName, itemsInStock, price);
        this.brand =  brand;
        this.warrantyPeriod = warrantyPeriod;
    }

    /**
     * Getter method for retrieving the brand of the electronic product.
     *
     * @return The brand of the electronic product.
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Setter method for setting the brand of the electronic product.
     *
     * @param brand The brand to set for the electronic product.
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Getter method for retrieving the warranty period of the electronic product.
     *
     * @return The warranty period of the electronic product.
     */
    public String getWarrantyPeriod() {
        return warrantyPeriod;
    }

    /**
     * Setter method for setting the warranty period of the electronic product.
     *
     * @param warrantyPeriod The warranty period to set for the electronic product.
     */
    public void setWarrantyPeriod(String warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    /**
     * Overrides the toString method to provide a formatted string representation of the `Electronics` object.
     *
     * @return A formatted string containing information about the electronic product.
     */
    @Override
    public String toString() {
        return super.toString() +
                "Product Brand: " + getBrand() + "\n" +
                "Warranty Period: " + getWarrantyPeriod() + "\n";
    }

}

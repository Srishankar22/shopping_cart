import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;


public class CartTableModel extends AbstractTableModel {

    private ArrayList <Product> cartList;  // List of products in the shopping cart

    private ShoppingCart shoppingCart;  // Reference to the shopping cart object
    private final String[] columnNames = {"Product", "Quantity", "Price"};   // Column names for the table


    /**
     * The constructor for CartTableModel initializes the table model with a provided list of products representing the shopping cart.
     *
     * @param cartList List of products to be displayed in the table.
     */
    public CartTableModel(ArrayList <Product> cartList){
        this.cartList = cartList;
    }

    /**
     * Returns the number of rows in the table, which is equal to the number of products in the cart.
     *
     * @return The number of rows in the table.
     */
    @Override
    public int getRowCount() {
        return cartList.size();
    }

    /**
     * Returns the number of columns in the table.
     *
     * @return The number of columns in the table.
     */
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    /**
     * Returns the value to be displayed at the specified row and column in the table.
     *
     * @param row The row index.
     * @param col The column index.
     * @return The value to be displayed at the specified cell.
     */
    @Override
    public Object getValueAt(int row, int col) {
        Product product = cartList.get(row);
        switch (col) {
            case 0:
                if(product.getClass() == Electronics.class) {
                    return product.getProductID() + " " + product.getProductName() + " " + ((Electronics) product).getBrand() + " " + ((Electronics) product).getWarrantyPeriod();
                }
                else{
                    return product.getProductID() + " " + product.getProductName() + " " + ((Clothing) product).getSize() + " " + ((Clothing) product).getColour();
                }
            case 1:
                return product.getProductQuantity();
            case 2:
                return product.getPurchasingCost() +" (\u00a3)";
            default:
                return null;
        }

    }

    /**
     * Returns the name of the specified column.
     *
     * @param column The column index.
     * @return The name of the column.
     */
    @Override
    public String getColumnName(int column) {

        return columnNames[column];
    }


}

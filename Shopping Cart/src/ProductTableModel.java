import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ProductTableModel extends AbstractTableModel {

    private List<Product> productList; // List to store Product objects
    private final String[] columnNames = {"Product ID", "Name", "Category", "Price", "Info"};  // Array of column names for the table

    /**
     * Constructor for the ProductTableModel class.
     *
     * @param productList List of Product objects to initialize the table model with.
     * The table will be populated with data from this list.
     */
    public ProductTableModel(List<Product> productList) {

        this.productList = productList;
    }


    /**
     * Returns the number of rows in the table.
     *
     * @return The number of rows, which is determined by the size of the productList.
     */
    @Override
    public int getRowCount() {
        return productList.size();
    }

    /**
     * Returns the number of columns in the table.
     *
     * @return The number of columns, which is determined by the length of the columnNames array.
     */
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    /**
     * Returns the value at the specified row and column in the table.
     *
     * @param rowIndex    The index of the row for which to retrieve the value.
     * @param columnIndex The index of the column for which to retrieve the value.
     * @return The value at the specified cell in the table, determined by the row and column indices.
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Product product = productList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return product.getProductID();
            case 1:
                return product.getProductName();
            case 2:
                if (product.getClass() == Electronics.class){
                    return "Electronics";
                }
                else {
                    return "Clothing";
                }
            case 3:
                return product.getPrice();
            case 4:
                if (product.getClass() == Electronics.class){
                    String brand = ((Electronics) product).getBrand();
                    String warranty = ((Electronics) product).getWarrantyPeriod();
                    return brand + " , " + warranty;
                }

                else {
                    String size = ((Clothing) product).getSize();
                    String color = ((Clothing) product).getColour();
                    return size + " , " + color;
                }
            default:
                return null;
        }
    }

    /**
     * Returns the name of the specified column in the table.
     *
     * @param column The index of the column for which to retrieve the name.
     * @return The name of the specified column, determined by the columnNames array.
     */
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    /**
     * Sets the table model with a new list of products and notifies the table that the data has changed.
     * This method replaces the existing data in the table model with the new list of products.
     *
     * @param products The ArrayList of Product objects to update the table model with.
     */
    public void setTable(ArrayList<Product> products){
        productList = products;
        fireTableDataChanged();
    }

}

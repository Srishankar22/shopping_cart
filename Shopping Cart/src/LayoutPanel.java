import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class LayoutPanel extends JFrame {
    private ProductTableModel dataModel;
    private ArrayList <Product> productList;

    private JComboBox dropMenu;

    private JTextArea textArea;

    private JTable productTable;

    private JButton shoppingCart;

    private boolean firstUser;

    private User user;
    private JButton addToCartButton;
    private CartPanel frame2;

    /**
     * This constructor initializes a graphical user interface (GUI) panel for the Westminster Shopping Centre.
     *
     * @param initialProductList The initial list of products.
     * @param user The user associated with the panel.
     * @param newUser Flag indicating if it's a new user.
     */
    public LayoutPanel(ArrayList<Product> initialProductList, User user, Boolean newUser) {

        setTitle("Westminster Shopping Centre");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(1000,600);


        productList = WestminsterShoppingManager.getProductList();
        Collections.sort(productList);
        setLayout(new GridBagLayout());  // Set the layout manager for the frame

        GridBagConstraints Up = new GridBagConstraints();
        Up.gridx = 0;
        Up.gridy = 0;
        Up.weighty = 0.2;
        Up.fill = GridBagConstraints.BOTH;
        Up.weightx = 5;

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        JPanel centerPanel = new JPanel();

        centerPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        centerPanel.add(new JLabel("Select Product Category  "));
        String[] categories = {"All", "Electronics", "Clothing"};

        // Create and configure the drop-down menu
        dropMenu = new JComboBox(categories);
        dropMenu.addActionListener(new comboBoxSelection());
        centerPanel.add(dropMenu);
        topPanel.add(centerPanel, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel();
        shoppingCart = new  JButton("Shopping Cart");
        shoppingCart.addActionListener(new cartSelection());
        rightPanel.add(shoppingCart);

        firstUser =  newUser;
        this.user = user;
        productList = initialProductList;

        topPanel.add(rightPanel, BorderLayout.EAST);
        add(topPanel , Up);

        GridBagConstraints Center1 = new GridBagConstraints();
        Center1.gridx = 0;
        Center1.gridy = 1;
        Center1.weightx = 1.0;
        Center1.weighty = 1.0;
        Center1.fill = GridBagConstraints.BOTH;


        productTable = new JTable();  // Create and configure the product table
        productTable.setDefaultRenderer(Object.class, new productAvailability());
        // Set up table selection listener
        TableSelection tableSelector = new TableSelection();
        ListSelectionModel listSelectionModel = productTable.getSelectionModel();
        listSelectionModel.addListSelectionListener(tableSelector);


        dataModel =  new ProductTableModel(productList);
        productTable.setModel(dataModel);
        productTable.setGridColor(Color.BLACK);

        JScrollPane scrollPane = new JScrollPane(productTable);
        add(scrollPane, Center1);


        JPanel mid = new JPanel();
        textArea = new JTextArea();
        textArea.setPreferredSize(new Dimension(900, 1200));

        GridBagConstraints Center2 = new GridBagConstraints();
        Center2.gridx = 0;
        Center2.gridy = 2;
        Center2.weightx = 1.0;
        Center2.weighty = 0.2;
        Center2.fill = GridBagConstraints.BOTH;
        mid.add(textArea);
        add(mid ,Center2);

        JPanel bottom = new JPanel();
        bottom.setLayout(new GridBagLayout());

        GridBagConstraints Bottom = new GridBagConstraints();
        Bottom.gridx = 0;
        Bottom.gridy = 3;
        Bottom.weightx = 0.0;
        Bottom.weighty = 0.0;
        Bottom.anchor = GridBagConstraints.CENTER;

        // Create and configure the "Add to Shopping Cart" button
        addToCartButton = new JButton("Add to Shopping Cart");
        addToCartButton.addActionListener(new AddToCartAction());
        bottom.add(addToCartButton, Bottom);
        add(bottom, Bottom);

        setVisible(true);  // Make the frame visible
    }


    /**
     * ActionListener implementation for handling JComboBox (drop-down menu) selection events.
     * Updates the displayed product table based on the selected category.
     *
     * @param 'e' The ActionEvent triggering the method.
     */
    private class comboBoxSelection implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String selected = (String) dropMenu.getSelectedItem();

            // Lists to filter products based on category
            ArrayList<Product> filterListElectronics = new ArrayList<Product>();
            ArrayList<Product> filterListClothing = new ArrayList<Product>();

            // Update the displayed table based on the selected category
            if (selected.equals("All")) {
                // Sort the entire product list based on product ID
                Collections.sort(productList);
                dataModel.setTable(productList);
            } else if (selected.equals("Electronics")) {
                for (Product items : productList) {
                    if (items instanceof Electronics) {
                        filterListElectronics.add(items);
                    }
                }
                // Sort the filtered list based on product ID
                Collections.sort(filterListElectronics);
                dataModel.setTable(filterListElectronics);
            } else {
                for (Product items : productList) {
                    if (items instanceof Clothing) {
                        filterListClothing.add(items);
                    }
                }
                // Sort the filtered list based on product ID
                Collections.sort(filterListClothing);
                dataModel.setTable(filterListClothing);
            }
        }
    }


    /**
     * ListSelectionListener implementation for handling JTable selection events.
     * Updates the text area with details of the selected product.
     *
     * @param 'event' The ListSelectionEvent triggering the method.
     */
    private class TableSelection implements ListSelectionListener {

        Product productSelected;
        @Override
        public void valueChanged(ListSelectionEvent event) {

            textArea.setText(""); // Clear the text area
            if (!event.getValueIsAdjusting()) {
                int selectedRow = productTable.getSelectedRow();

                if (selectedRow >= 0 && selectedRow < productTable.getRowCount()) {
                    String selectedItem = (String) productTable.getValueAt(selectedRow, 0);

                    ArrayList<Product> filteredList;
                    String selectedCategory = (String) dropMenu.getSelectedItem();
                    if (selectedCategory.equals("All")) {
                        filteredList = productList;
                    } else if (selectedCategory.equals("Electronics")) {
                        filteredList = new ArrayList<>();
                        for (Product item : productList) {
                            if (item instanceof Electronics) {
                                filteredList.add(item);
                            }
                        }
                    } else {
                        filteredList = new ArrayList<>();
                        for (Product item : productList) {
                            if (item instanceof Clothing) {
                                filteredList.add(item);
                            }
                        }
                    }

                    for (Product product : filteredList) {  // Iterate through the filtered list to find the selected product
                        if (product.getProductID().equals(selectedItem)) {
                            productSelected = product;
                            if (product.getClass() == Electronics.class) {
                                textArea.append("Selected Product - Details\n\nProduct Id: " + product.getProductID() +
                                        "\n\nCategory: Electronics\n\nName: " + product.getProductName() + "\n\nBrand: " +
                                        ((Electronics) product).getBrand() + "\n\nWarranty Period: " +
                                        ((Electronics) product).getWarrantyPeriod() +
                                        "\n\nItems Available: " + product.getItemsInStock());
                            } else {
                                textArea.append("Selected Product - Details\n\nProduct Id: " + product.getProductID() +
                                        "\n\nCategory: Clothing\n\nName: " + product.getProductName() + "\n\nSize: " +
                                        ((Clothing) product).getSize() + "\n\nWarranty Period: " +
                                        ((Clothing) product).getColour() +
                                        "\n\nItems Available: " + product.getItemsInStock());
                            }
                        }
                    }
                }
            }
        }
    }


    /**
     * ActionListener implementation for handling the "Add to Shopping Cart" button click event.
     * Adds the selected product to the user's shopping cart if a user and shopping cart are available.
     *
     * @param 'e' The ActionEvent triggering the method.
     */
    private class AddToCartAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            int selectedRow = productTable.getSelectedRow();
            Product selectedProduct = productList.get(selectedRow);

            if (user != null && user.getShoppingCart() != null){
                user.getShoppingCart().addtocart(selectedProduct);
            }
        }
    }


    /**
     * ActionListener implementation for handling the "Shopping Cart" button click event.
     * Opens a new CartPanel, allowing the user to view and manage the contents of their shopping cart.
     *
     * @param 'e' The ActionEvent triggering the method.
     */
    private class cartSelection implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){

            frame2 = new CartPanel(user, firstUser);
        }
    }


    /**
     * Custom table cell renderer class for product availability highlighting.
     *
     * @param 'table'     The JTable component being rendered.
     * @param 'value'      The value of the cell being rendered.
     * @param 'isSelected' True if the cell is selected, false otherwise.
     * @param 'hasFocus'   True if the cell has focus, false otherwise.
     * @param 'row'        The row index of the cell being rendered.
     * @param 'column'     The column index of the cell being rendered.
     * @return The rendered Component.
     */
    private class productAvailability extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            String productId = (String) table.getValueAt(row,0);

            int quantity = 0;

            for(Product product: productList){
                if(product.getProductID().equals(productId)){
                    quantity = product.getItemsInStock();
                    break;
                }
            }

            if(quantity < 3){   // Highlight the cell background based on the quantity of items in stock
                Color color = Color.RED;
                component.setBackground(color);
            } else{
                component.setBackground(table.getBackground()); // Use the default background color of the table
            }

            return component;
        }

    }


}

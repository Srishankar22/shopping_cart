import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CartPanel extends JFrame {

    ArrayList <Product> cartList;  // Represents the list of products in the shopping cart.
    boolean firstBuy;  // Indicates whether this is the user's first purchase.
    JLabel label01, label02, label03, label04, label05, label06, label07, label08; // JLabels for displaying various information related to the shopping cart and bill summary.
    ShoppingCart cart ;  // Represents the shopping cart associated with the user.
    User user;  // Represents the user using the shopping cart.
    private static JTable cartTable;  // Static JTable for displaying the contents of the shopping cart


    /**
     * Constructor for the CartPanel class, representing the shopping cart GUI.
     *
     * @param user   The User object associated with the shopping cart.
     * @param firstPurchase  A boolean indicating if this is the user's first purchase.
     */
    public CartPanel(User user, Boolean firstPurchase) {

        setLayout(new GridLayout(2, 1));

        setTitle("Shopping Cart");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(700, 400);
        setVisible(true);

        firstBuy = firstPurchase;
        this.user = user;
        cart = user.getShoppingCart();
        cartList = cart.getCartList();

        JPanel panel1 = new JPanel(new BorderLayout());

        CartTableModel model = new CartTableModel(cartList);
        cartTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(cartTable);

        panel1.add(scrollPane, BorderLayout.CENTER);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(4,2));

        label01 = new JLabel("Total");
        label01.setHorizontalAlignment(SwingConstants.RIGHT);
        label03 = new JLabel("First Purchase Discount (10%)");
        label03.setHorizontalAlignment(SwingConstants.RIGHT);
        label05 = new JLabel("Three Items in same Category Discount (20%)");
        label05.setHorizontalAlignment(SwingConstants.RIGHT);
        label07 = new JLabel("Final Total");
        label07.setHorizontalAlignment(SwingConstants.RIGHT);

        label02 = new JLabel();
        label02.setHorizontalAlignment(SwingConstants.CENTER);
        label04 = new JLabel();
        label04.setHorizontalAlignment(SwingConstants.CENTER);
        label06 = new JLabel();
        label06.setHorizontalAlignment(SwingConstants.CENTER);
        label08 = new JLabel();
        label08.setHorizontalAlignment(SwingConstants.CENTER);

        panel2.add(label01);
        panel2.add(label02);
        panel2.add(label03);
        panel2.add(label04);
        panel2.add(label05);
        panel2.add(label06);
        panel2.add(label07);
        panel2.add(label08);

        add(panel1);
        add(panel2);

        this.UpdateBill();

    }

    /**
     * Updates the bill summary labels based on the current contents of the shopping cart.
     * Calculates total cost, applies discounts (if applicable), and updates relevant labels.
     */
    public void UpdateBill(){

        double total = user.getShoppingCart().calculateTotalCost() ;

        label02.setText(user.getShoppingCart().calculateTotalCost() + "  \u00A3");

        if(firstBuy){
            label04.setText(user.getShoppingCart().calculateTotalCost() * 0.1 + "  \u00A3");
            total -= user.getShoppingCart().calculateTotalCost() * 0.1;
        }
        else{
            label04.setText("0.0" + "  \u00A3");
        }

        int electronicsCount = 0;
        int clothingCount = 0;

        for(Product items: cartList){

            if(items instanceof Electronics){
                electronicsCount += items.getProductQuantity();
            }
            else{
                clothingCount += items.getProductQuantity();
            }
        }

        if(electronicsCount >= 3 || clothingCount >=3){
            label06.setText(user.getShoppingCart().calculateTotalCost() * 0.2 + "  \u00A3");
            total -= user.getShoppingCart().calculateTotalCost() * 0.2;
        }
        else{
            label06.setText("0.0" + "  \u00A3");
        }

        label08.setText(total +  "  \u00A3");
    }
}

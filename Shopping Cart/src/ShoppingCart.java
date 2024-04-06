import java.util.ArrayList;

public class ShoppingCart {

    ArrayList <Product> cart = new ArrayList<>();

    public ShoppingCart(){

        cart =  new ArrayList<>();
    }

    public void addtocart(Product pro) {
        boolean productExists = false;
        for (Product product : cart) {
            if (product.getProductID().equals(pro.getProductID())) {
                product.setProductQuantity(product.getProductQuantity() + 1);
                productExists = true;
                break;
            }
        }
        if (!productExists) {
            pro.setProductQuantity(1);
            cart.add(pro);
        }
    }


    public double calculateTotalCost() {
        double cost = 0;
        for (Product product : cart) {
            cost += product.getPurchasingCost();
        }
        return cost;
    }

    public ArrayList<Product> getCartList() {
        return cart;
    }

}

package marcelmax.shoppingcart.model;

public class CartItem implements ViewType {

    /**
     * Model class for the cart items
     * necessary, because of the single adapter
     */

    private Product product;


    public CartItem(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "product=" + product +
                '}';
    }

    @Override
    public int getType() {
        return ViewType.CART_TYPE;
    }


}

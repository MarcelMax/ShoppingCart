package marcelmax.shoppingcart.model;

public interface ViewType {

    /**
     * all models that are being displayed in a recyclerview have to implement this.
     * they each get a specific id tied to them so the adapter can set the view
     * accordingly
     */
    int PRODUCT_TYPE = 1;
    int ADDRESS_TYPE = 2;
    int CART_TYPE = 3;

    int getType();

}

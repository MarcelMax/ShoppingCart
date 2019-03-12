package marcelmax.shoppingcart.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CartItem implements Parcelable, ViewType {

    /**
     * Model class for the cart items
     * necessary, because of the single adapter
     */

    private Product product;

    public final static Parcelable.Creator<Product> CREATOR = new Creator<Product>() {

        @SuppressWarnings({
                "unchecked"
        })
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        public Product[] newArray(int size) {
            return (new Product[size]);
        }

    };

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
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeValue(product);
    }

    @Override
    public int getType() {
        return ViewType.CART_TYPE;
    }

}

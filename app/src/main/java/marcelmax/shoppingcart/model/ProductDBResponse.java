package marcelmax.shoppingcart.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductDBResponse implements Parcelable {

    @SerializedName("product")
    @Expose
    private List<Product> product = null;
    public final static Parcelable.Creator<ProductDBResponse> CREATOR = new Creator<ProductDBResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ProductDBResponse createFromParcel(Parcel in) {
            return new ProductDBResponse(in);
        }

        public ProductDBResponse[] newArray(int size) {
            return (new ProductDBResponse[size]);
        }

    };

    protected ProductDBResponse(Parcel in) {
        in.readList(this.product, (marcelmax.shoppingcart.model.Product.class.getClassLoader()));
    }

    public ProductDBResponse() {
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(product);
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return "ProductDBResponse{" +
                "product=" + product +
                '}' + "\n";
    }
}

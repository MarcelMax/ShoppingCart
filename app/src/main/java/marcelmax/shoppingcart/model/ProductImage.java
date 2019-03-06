package marcelmax.shoppingcart.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductImage implements Parcelable {

    @SerializedName("preview_images")
    @Expose
    private String previewImages;
    public final static Parcelable.Creator<ProductImage> CREATOR = new Creator<ProductImage>() {

        @SuppressWarnings({
                "unchecked"
        })
        public ProductImage createFromParcel(Parcel in) {
            return new ProductImage(in);
        }

        public ProductImage[] newArray(int size) {
            return (new ProductImage[size]);
        }

    };

    protected ProductImage(Parcel in) {
        this.previewImages = ((String) in.readValue((String.class.getClassLoader())));
    }

    public ProductImage() {
    }

    public String getPreviewImages() {
        return previewImages;
    }

    public void setPreviewImages(String previewImages) {
        this.previewImages = previewImages;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(previewImages);
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return "ProductImage{" +
                "previewImages='" + previewImages + '\'' + "\n" +
                '}';
    }
}

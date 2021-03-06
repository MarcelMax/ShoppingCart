package marcelmax.shoppingcart.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Product implements Parcelable, ViewType {
    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("product_description_short")
    @Expose
    private String productDescriptionShort;
    @SerializedName("product_description")
    @Expose
    private String productDescription;
    @SerializedName("product_rating")
    @Expose
    private Double productRating;
    @SerializedName("product_img")
    @Expose
    private String productImg;
    @SerializedName("product_date")
    @Expose
    private String productDate;
    @SerializedName("product_available")
    @Expose
    private Boolean productAvailable;
    @SerializedName("product_price")
    @Expose
    private Double productPrice;
    @SerializedName("product_currency")
    @Expose
    private String productCurrency;
    @SerializedName("product_reviewcount")
    @Expose
    private Integer productReviewcount;
    @SerializedName("product_quantity")
    @Expose
    private Integer productQuantity;
    @SerializedName("product_quantity_choosen")
    @Expose
    private Integer productQuantityChoosen;
    @SerializedName("product_images")
    @Expose
    private List<ProductImage> productImages = null;
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

    protected Product(Parcel in) {
        this.productId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.productName = ((String) in.readValue((String.class.getClassLoader())));
        this.productDescriptionShort = ((String) in.readValue((String.class.getClassLoader())));
        this.productDescription = ((String) in.readValue((String.class.getClassLoader())));
        this.productRating = ((Double) in.readValue((Double.class.getClassLoader())));
        this.productImg = ((String) in.readValue((String.class.getClassLoader())));
        this.productDate = ((String) in.readValue((String.class.getClassLoader())));
        this.productAvailable = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.productPrice = ((Double) in.readValue((Double.class.getClassLoader())));
        this.productCurrency = ((String) in.readValue((String.class.getClassLoader())));
        this.productReviewcount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.productQuantity = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.productQuantityChoosen = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.productImages, (marcelmax.shoppingcart.model.ProductImage.class.getClassLoader()));
    }

    public Product() {
    }

    /**
     *
     * @param productId = id of the product
     * @param productName = name of the product
     * @param productDescriptionShort = short description of the product to be displayed a preview
     * @param productDescription = the long product description that is displayed in the detail view
     * @param productRating = the products rating
     * @param productImg = the preview image
     * @param productDate = the deliverydate
     * @param productAvailable = the availability of the product //todo for now this does nothing but could later be a indicator for diffrent view types
     * @param productPrice = price of the product
     * @param productCurrency = the product currency eg. EUR
     * @param productReviewcount = the reviewcount of the product
     * @param productQuantity = the max. available quantity
     * @param productQuantityChoosen = the quantity the user chooses
     * @param productImages = more images of the product
     */
    public Product(Integer productId, String productName, String productDescriptionShort, String productDescription, Double productRating, String productImg, String productDate, Boolean productAvailable, Double productPrice, String productCurrency, Integer productReviewcount, Integer productQuantity, Integer productQuantityChoosen, List<ProductImage> productImages) {
        this.productId = productId;
        this.productName = productName;
        this.productDescriptionShort = productDescriptionShort;
        this.productDescription = productDescription;
        this.productRating = productRating;
        this.productImg = productImg;
        this.productDate = productDate;
        this.productAvailable = productAvailable;
        this.productPrice = productPrice;
        this.productCurrency = productCurrency;
        this.productReviewcount = productReviewcount;
        this.productQuantity = productQuantity;
        this.productQuantityChoosen = productQuantityChoosen;
        this.productImages = productImages;
    }

    public Product(Integer productId, String productName, String productDescriptionShort, String productDescription, Double productRating, String productImg, String productDate, Boolean productAvailable, Double productPrice, String productCurrency, Integer productReviewcount, Integer productQuantity, Integer productQuantityChoosen) {
        this.productId = productId;
        this.productName = productName;
        this.productDescriptionShort = productDescriptionShort;
        this.productDescription = productDescription;
        this.productRating = productRating;
        this.productImg = productImg;
        this.productDate = productDate;
        this.productAvailable = productAvailable;
        this.productPrice = productPrice;
        this.productCurrency = productCurrency;
        this.productReviewcount = productReviewcount;
        this.productQuantity = productQuantity;
        this.productQuantityChoosen = productQuantityChoosen;
    }

    public Product(String productName) {

        this.productName = productName;

    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescriptionShort() {
        return productDescriptionShort;
    }

    public void setProductDescriptionShort(String productDescriptionShort) {
        this.productDescriptionShort = productDescriptionShort;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Double getProductRating() {
        return productRating;
    }

    public void setProductRating(Double productRating) {
        this.productRating = productRating;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getProductDate() {
        return productDate;
    }

    public void setProductDate(String productDate) {
        this.productDate = productDate;
    }

    public Boolean getProductAvailable() {
        return productAvailable;
    }

    public void setProductAvailable(Boolean productAvailable) {
        this.productAvailable = productAvailable;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductCurrency() {
        return productCurrency;
    }

    public void setProductCurrency(String productCurrency) {
        this.productCurrency = productCurrency;
    }

    public Integer getProductReviewcount() {
        return productReviewcount;
    }

    public void setProductReviewcount(Integer productReviewcount) {
        this.productReviewcount = productReviewcount;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Integer getProductQuantityChoosen() {
        return productQuantityChoosen;
    }

    public void setProductQuantityChoosen(Integer productQuantityChoosen) {
        this.productQuantityChoosen = productQuantityChoosen;
    }

    public List<ProductImage> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<ProductImage> productImages) {
        this.productImages = productImages;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(productId);
        dest.writeValue(productName);
        dest.writeValue(productDescriptionShort);
        dest.writeValue(productDescription);
        dest.writeValue(productRating);
        dest.writeValue(productImg);
        dest.writeValue(productDate);
        dest.writeValue(productAvailable);
        dest.writeValue(productPrice);
        dest.writeValue(productCurrency);
        dest.writeValue(productReviewcount);
        dest.writeValue(productQuantity);
        dest.writeValue(productQuantityChoosen);
        dest.writeList(productImages);
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return "\n" +"Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productDescriptionShort='" + productDescriptionShort + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productRating=" + productRating +
                ", productImg='" + productImg + '\'' +
                ", productDate='" + productDate + '\'' +
                ", productAvailable=" + productAvailable +
                ", productPrice=" + productPrice +
                ", productCurrency='" + productCurrency + '\'' +
                ", productReviewcount=" + productReviewcount +
                ", productQuantity=" + productQuantity +
                ", productQuantityChoosen=" + productQuantityChoosen +
                ", productImages=" + productImages +
                '}';
    }

    @Override
    public int getType() {
        return ViewType.PRODUCT_TYPE;
    }

}

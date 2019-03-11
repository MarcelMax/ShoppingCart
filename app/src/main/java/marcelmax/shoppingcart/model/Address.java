package marcelmax.shoppingcart.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Address implements Parcelable, ViewType {

    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("streetName")
    @Expose
    private String streetName;
    @SerializedName("streetNumber")
    @Expose
    private String streetNumber;
    @SerializedName("postCode")
    @Expose
    private String postCode;
    @SerializedName("cityName")
    @Expose
    private String cityName;
    @SerializedName("emailAddress")
    @Expose
    private String emailAddress;
    @SerializedName("addressImage")
    @Expose
    private String addressImage;
    public final static Parcelable.Creator<Address> CREATOR = new Creator<Address>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Address createFromParcel(Parcel in) {
            return new Address(in);
        }

        public Address[] newArray(int size) {
            return (new Address[size]);
        }

    };

    protected Address(Parcel in) {
        this.firstName = ((String) in.readValue((String.class.getClassLoader())));
        this.lastName = ((String) in.readValue((String.class.getClassLoader())));
        this.streetName = ((String) in.readValue((String.class.getClassLoader())));
        this.streetNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.postCode = ((String) in.readValue((String.class.getClassLoader())));
        this.cityName = ((String) in.readValue((String.class.getClassLoader())));
        this.emailAddress = ((String) in.readValue((String.class.getClassLoader())));
        this.addressImage = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Address() {
    }

    public Address(String firstName, String lastName, String streetName, String streetNumber, String postCode, String cityName, String emailAddress, String addressImage) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.postCode = postCode;
        this.cityName = cityName;
        this.emailAddress = emailAddress;
        this.addressImage = addressImage;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getAddressImage() {
        return addressImage;
    }

    public void setAddressImage(String addressImage) {
        this.addressImage = addressImage;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(firstName);
        dest.writeValue(lastName);
        dest.writeValue(streetName);
        dest.writeValue(streetNumber);
        dest.writeValue(postCode);
        dest.writeValue(cityName);
        dest.writeValue(emailAddress);
        dest.writeValue(addressImage);
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return "Address{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", streetName='" + streetName + '\'' +
                ", streetNumber='" + streetNumber + '\'' +
                ", postCode='" + postCode + '\'' +
                ", cityName='" + cityName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", addressImage='" + addressImage + '\'' +
                '}';
    }

    @Override
    public int getType() {
        return ViewType.ADDRESS_TYPE;
    }

}

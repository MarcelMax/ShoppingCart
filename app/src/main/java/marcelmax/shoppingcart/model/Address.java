package marcelmax.shoppingcart.model;

public class Address {

    private String firstName;
    private String lastName;
    private String streetName;
    private String streetNumber;
    private String postCode;
    private String cityName;
    private String addressImage;

    public Address() {    }

    public Address(String firstName, String lastName, String streetName, String streetNumber, String postCode, String cityName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.postCode = postCode;
        this.cityName = cityName;
    }

    public Address(String firstName, String lastName, String streetName, String streetNumber, String postCode, String cityName, String addressImage) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.postCode = postCode;
        this.cityName = cityName;
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

    @Override
    public String toString() {
        return "Address{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", streetName='" + streetName + '\'' +
                ", streetNumber='" + streetNumber + '\'' +
                ", postCode='" + postCode + '\'' +
                ", cityName='" + cityName + '\'' +
                ", addressImage='" + addressImage + '\'' +
                '}';
    }

    public String getAddressImage() {
        return addressImage;
    }

    public void setAddressImage(String addressImage) {
        this.addressImage = addressImage;
    }

}

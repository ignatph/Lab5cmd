package body;

public class Address {
    private String street;
    private String zipCode;

    public Address(String street, String zipCode) {
        setStreet(street);
        setZipCode(zipCode);
    }

    public Address() {
    }


    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        if (street == null || street.trim().isEmpty()) {
            throw new IllegalArgumentException("Улица не может быть null или пустой.");
        }
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        if (zipCode != null && zipCode.length() < 6) {
            throw new IllegalArgumentException("Длина почтового индекса должна быть не меньше 6.");
        }
        this.zipCode = zipCode; // Может быть null
    }

    @Override
    public String toString() {
        return "Address: " +
                "street = " + street +
                ", zipCode = " + zipCode;
    }
}
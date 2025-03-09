package body;

public class Organization {
    private String fullName;
    private OrganizationType type;
    private Address officialAddress;

    // Конструктор с параметрами
    public Organization(String fullName, OrganizationType type, Address officialAddress) {
        setName(fullName);
        setType(type);
        setAddress(officialAddress);
    }

    public Organization() {
    }


    public String getFullName() {
        return fullName;
    }

    public void setName(String fullName) {
        this.fullName = fullName;
    }

    public OrganizationType getType() {
        return type;
    }

    public void setType(OrganizationType type) {
        this.type = type; // Может быть null
    }

    public Address getOfficialAddress() {
        return officialAddress;
    }

    public void setAddress(Address officialAddress) {
        this.officialAddress = officialAddress; // Может быть null
    }

    @Override
    public String toString() {
        return "Organization: " +
                "fullName = " + fullName +
                ", type = " + type +
                ", officialAddress = " + officialAddress;
    }
}
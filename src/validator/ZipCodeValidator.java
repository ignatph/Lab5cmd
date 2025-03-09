package validator;

public class ZipCodeValidator implements Validator {
    public static boolean validate(String zipCode) {
        return zipCode == null || zipCode.length() >= 6;
    }
}
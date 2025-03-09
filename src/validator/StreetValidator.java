package validator;

public class StreetValidator implements Validator {
    public static boolean validate(String street) {
        return street != null && !street.trim().isEmpty();
    }
}
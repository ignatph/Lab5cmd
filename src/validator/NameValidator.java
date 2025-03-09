package validator;

public class NameValidator implements Validator {
    public static boolean validate(String name) {
        return name != null && !name.trim().isEmpty();
    }
}

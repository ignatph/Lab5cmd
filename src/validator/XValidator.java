package validator;

public class XValidator implements Validator {
    public static boolean validate(Float x) {
        return x != null && x > -848;
    }
}
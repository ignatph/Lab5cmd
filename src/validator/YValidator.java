package validator;

public class YValidator implements Validator {
    public static boolean validate(Float y) {
        return y != null;
    }
}
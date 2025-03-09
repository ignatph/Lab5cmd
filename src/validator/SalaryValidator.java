package validator;

public class SalaryValidator implements Validator {
    public static boolean validate(Integer salary) {
        return salary != null && salary > 0;
    }
}
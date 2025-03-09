package validator;

/**
 * Common interface for all validators
 */
public interface Validator {

    static boolean validate(Object value) {
        return false;
    }
}

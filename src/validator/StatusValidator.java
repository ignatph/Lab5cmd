package validator;

import body.*;

public class StatusValidator implements Validator {
    public static boolean validate(Status status) {
        return status != null;
    }
}
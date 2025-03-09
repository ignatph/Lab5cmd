package validator;

import body.*;

public class OrganizationValidator implements Validator {
    public static boolean validate(OrganizationType type) {
        return type != null;
    }
}
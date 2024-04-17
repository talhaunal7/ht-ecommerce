package com.haratres.ecommerce.config;

import com.haratres.ecommerce.controller.request.SignUpRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class SignUpValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return SignUpRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        SignUpRequest request = (SignUpRequest) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.required", "email is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required", "password is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required", "name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "surname.required", "surname is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumber", "phoneNumber.required", "phoneNumber is required");


        if (!errors.hasFieldErrors()) {
            if (request.getName().length() < 3 || request.getName().length() > 20) {
                errors.rejectValue("name", "name.invalid", "name should be between 3 and 20 characters");
            }
            if (request.getSurname().length() < 3 || request.getSurname().length() > 20) {
                errors.rejectValue("surname", "surname.invalid", "surname should be between 3 and 20 characters");
            }
            if (request.getPassword().length() < 5 || request.getPassword().length() > 64) {
                errors.rejectValue("password", "password.invalid", "password should be between 5 and 64 characters");
            }
            if (request.getPhoneNumber().charAt(0) != '0') {
                errors.rejectValue("phoneNumber", "phoneNumber.invalid", "first digit of phone number should be 0");
            }
            if (request.getPhoneNumber().length() != 11) {
                errors.rejectValue("phoneNumber", "phoneNumber.invalid", "phone number should be 10 characters");
            }
        }


    }
}

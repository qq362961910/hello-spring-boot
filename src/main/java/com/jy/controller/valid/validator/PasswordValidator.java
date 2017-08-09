package com.jy.controller.valid.validator;

import com.jy.controller.valid.anno.Password;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    private Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{6,15}$");

    public PasswordValidator() {
    }

    public void initialize(Password cellphone) {
    }

    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return StringUtils.isEmpty(value)?false:this.pattern.matcher(value).find();
    }
}

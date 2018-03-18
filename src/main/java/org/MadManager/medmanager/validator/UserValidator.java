package org.MadManager.medmanager.validator;

import org.MadManager.medmanager.models.Users;
import org.MadManager.medmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by hiren.vaghasiya on 2/17/2018.
 */
@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass){
        return Users.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        Users user = (Users) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username","NotEmpty");
        if (user.getUsername().length() < 6 || user.getUsername().length() > 32 ){
            errors.rejectValue("username","Size.userForm.username");
        }
        if (userService.findByUsername(user.getUsername()) != null){
            errors.rejectValue("username","Duplicate.userForm.username");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password","NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() >32){
            errors.rejectValue("password","Size.userForm.password");
        }

        if (!user.getPassword().equals(user.getConfirmPassword())){
            errors.rejectValue("confirmPassword","Diff.userForm.confirmPassword");
        }
    }
}

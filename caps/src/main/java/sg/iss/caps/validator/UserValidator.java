package sg.iss.caps.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import sg.iss.caps.model.User;

public class UserValidator implements org.springframework.validation.Validator {

	public UserValidator() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		User user = (User) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "UserID", "ID cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "Password", "Password cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "FirstMidName", "First name cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "LastName", "Last name cannot be empty");
	    System.out.println(user.toString());

	}

}
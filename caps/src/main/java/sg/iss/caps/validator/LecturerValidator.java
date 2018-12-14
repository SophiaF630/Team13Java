package sg.iss.caps.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import sg.iss.caps.model.Lecturer;

public class LecturerValidator implements Validator {

	public LecturerValidator() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Lecturer.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Lecturer lecturer = (Lecturer) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lecturer_LecturerID", "ID cannot be empty");
		System.out.println(lecturer.toString());

	}

}
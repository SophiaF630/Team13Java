package sg.iss.caps.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.data.domain.Pageable;

import sg.iss.caps.services.StudentService;
import sg.iss.caps.validator.StudentValidator;
import sg.iss.caps.exception.StudentNotFound;
import sg.iss.caps.model.Course;
import sg.iss.caps.model.Student;
//import sg.iss.caps.repo.CoursePageRepository;
import sg.iss.caps.repo.CourseRepository;



@RequestMapping(value="/student")
@RestController
@Controller
public class StudentController {
	
	@RequestMapping(value="/index")
	public String index() {
		
		return "Hello World!";
	}
	
	@Autowired
	StudentService sService;


//	@Autowired
//	private StudentValidator sValidator;

//	@InitBinder("student")
//	private void initDepartmentBinder(WebDataBinder binder) {
//		binder.addValidators(sValidator);
//	}

	//Student Info Part
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listAll() {
		ModelAndView mav = new ModelAndView("StudentCRUD");
		ArrayList<Student> students = sService.findAllStudents();
		mav.addObject("students", students);
		return mav;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView newStudentPage() {
		ModelAndView mav = new ModelAndView("StudentFormNew", "student", new Student());
		return mav;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView createNewStudent(@ModelAttribute @Valid Student student, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		if (result.hasErrors())
			return new ModelAndView("StudentFormNew");
		ModelAndView mav = new ModelAndView();

		sService.createStudent(student);
		//String message = "New student " + student.getNric() + " was successfully created.";
		mav.setViewName("redirect:/student/list");
		return mav;
	}

	@RequestMapping(value = "/edit/{sid}", method = RequestMethod.GET)
	public ModelAndView editStudentPage(@PathVariable String sid) {
		ModelAndView mav = new ModelAndView("StudentFormEdit");
		mav.addObject("student", sService.findStudent(sid));
		return mav;
	}

	@RequestMapping(value = "/edit/{sid}", method = RequestMethod.POST)
	public ModelAndView editStudent(@ModelAttribute @Valid Student student, @PathVariable String sid,
			BindingResult result, final RedirectAttributes redirectAttributes) throws StudentNotFound {
		System.out.println("student"+student.toString());
		if (result.hasErrors())
			return new ModelAndView("StudentFormEdit");
		sService.updateStudent(student);
		ModelAndView mav = new ModelAndView("redirect:/student/list");
		String message = "Student" + student.getStudentID() + " was successfully updated.";
		redirectAttributes.addFlashAttribute("message", message);
		return mav;
	}

	@RequestMapping(value = "/delete/{sid}", method = RequestMethod.GET)
	public ModelAndView deleteStudent(@PathVariable String sid, final RedirectAttributes redirectAttributes)
			throws StudentNotFound {
		Student student = sService.findStudent(sid);
		sService.removeStudent(student);
		ModelAndView mav = new ModelAndView("redirect:/student/list");
		String message = "The student " + student.getStudentID() + " was successfully deleted.";
		redirectAttributes.addFlashAttribute("message", message);
		return mav;
	}
	
	//Course Info Part
	//List all course details
	@RequestMapping(value = "/coursedetails", method = RequestMethod.GET)
	public ModelAndView listAllStudentsCourseDetails() {
		ModelAndView mav = new ModelAndView("StudentCourseDetails");
		ArrayList<Course> courses = sService.findAllStudentsCourseDetails();
		mav.addObject("courses", courses);
		return mav;
	}
		
	//List selected course
	@RequestMapping(value = "/selectedcourse", method = RequestMethod.GET)
	public ModelAndView listAllSelectedCourse() {
		ModelAndView mav = new ModelAndView("SelectCourse");
		ArrayList<Course> courses = sService.findAllSelectedCourse("ISS");		
		mav.addObject("courses", courses);
		return mav;
	}
	
	@RequestMapping(value = "/courses/{sid}", method = RequestMethod.GET)
	public ModelAndView listStudentsCourses(@PathVariable String sid) {
		ModelAndView mav = new ModelAndView("StudentCourseList");
		ArrayList<Course> courses = sService.findCurrentCoursesByStudentID(sid);
		mav.addObject("courses", courses);
		String names = "Current Class";
		mav.addObject("name" ,names);
		return mav;
	}

}

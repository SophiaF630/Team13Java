package sg.iss.caps.controllers;

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sg.iss.caps.exception.CourseNotFound;
import sg.iss.caps.exception.StudentNotFound;
import sg.iss.caps.model.Course;
import sg.iss.caps.model.Student;
import sg.iss.caps.model.User;
import sg.iss.caps.services.CourseService;
import sg.iss.caps.services.StudentService;
import sg.iss.caps.services.UserService;

@RequestMapping(value="/admin")
@RestController
@Controller
public class AdminControllerCommon {
	@RequestMapping(value="/index")
	public String index() {
		
		return "Hello World!";
	}
	@Autowired
	StudentService sService;
	@Autowired
	CourseService cService;
	@Autowired
	UserService uService;
	@RequestMapping(value = "/home")
	public ModelAndView home() {
		ModelAndView mav = new ModelAndView("AdminHome");
		return mav;
	}//we need to update the view
	
	
	
	//this part is acadamicmanage
	@RequestMapping(value = "/academictime", method = RequestMethod.GET)
	public ModelAndView academicmanage() {
		ModelAndView mav = new ModelAndView("AcademicManage");
		return mav;
	}//we need to update the view by yiran
	
	
	
	
	
	
	//this parts starts students
	@RequestMapping(value = "/student/list", method = RequestMethod.GET)
	public ModelAndView listAll() {
		ModelAndView mav = new ModelAndView("StudentList");
		ArrayList<Student> students = sService.findAllStudents();
		ArrayList<User> users = new ArrayList<User>();
		for (Student student : students) {
			users.add(uService.findUserByID(student.getStudentID()));
		}
		mav.addObject("students", students);
		mav.addObject("Users",users);
		return mav;
	}//we need to update the view
	//this part need to know what to show

	@RequestMapping(value = "/student/create", method = RequestMethod.GET)
	public ModelAndView newStudentPage() {
		ModelAndView mav = new ModelAndView("StudentFormNew", "student", new Student());
		return mav;
	}//we need to update the view
	//this part need to fix(fix failed;cannot add record)

	@RequestMapping(value = "/student/create", method = RequestMethod.POST)
	public ModelAndView createNewStudent(@ModelAttribute("student") Student student,@ModelAttribute("user") User user, /*BindingResult result,*/
			final RedirectAttributes redirectAttributes) {
		student.setStudentID(user.getUserID());
		user.setUserType("student");
		/*if (result.hasErrors()) {
			
			return new ModelAndView("StudentFormNew");}*/
		ModelAndView mav = new ModelAndView();
		uService.createUser(user);
		sService.createStudent(student);
		//String message = "New student " + student.getNric() + " was successfully created.";
		mav.setViewName("redirect:/admin/student/list");
		return mav;
	}

	@RequestMapping(value = "/student/edit/{sid}", method = RequestMethod.GET)
	public ModelAndView editStudentPage(@PathVariable String sid) {
		ModelAndView mav = new ModelAndView("StudentFormEdit");
		mav.addObject("student", sService.findStudent(sid));
		mav.addObject("user",uService.findUserByID(sid));
		return mav;
	}//we need to update the view
	//we need to fix

	@RequestMapping(value = "/student/edit/{sid}", method = RequestMethod.POST)
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

	@RequestMapping(value = "/student/delete/{sid}", method = RequestMethod.GET)
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
	
	@RequestMapping(value = "/student/courses/{sid}", method = RequestMethod.GET)
	public ModelAndView listStudentsCourses(@PathVariable String sid) {
		ModelAndView mav = new ModelAndView("StudentCourseList");
		ArrayList<Course> courses = sService.findCurrentCoursesByStudentID(sid);
		mav.addObject("courses", courses);
		int status = 1;
		mav.addObject("status" ,status);
		mav.addObject("sid",sid);
		return mav;
	}
	@RequestMapping(value = "/student/history/{sid}", method = RequestMethod.GET)
	public ModelAndView listStudentsHistoryCourses(@PathVariable String sid) {
		ModelAndView mav = new ModelAndView("StudentCourseList");
		ArrayList<Course> courses = sService.findHistoryCoursesByStudentID(sid);
		mav.addObject("courses", courses);
		int status = 2;
		mav.addObject("status" ,status);
		mav.addObject("sid",sid);
		return mav;
	}
	
	
	
	
	
	
	
	
	
	
	//this part starts course
	@RequestMapping(value = "/course/list", method = RequestMethod.GET)
	public ModelAndView listALLCourses() {
		ModelAndView mav = new ModelAndView("CourseList");
		ArrayList<Course> courses = cService.findAllCourse();
		courses = cService.selectCurrentCourse(courses);
		mav.addObject("courses", courses);
		return mav;
	}
	@RequestMapping(value = "/course/create", method = RequestMethod.GET)
	public ModelAndView newCoursePage() {
		ModelAndView mav = new ModelAndView("CourseFormNew", "course", new Course());
		return mav;
	}

	@RequestMapping(value = "/course/create", method = RequestMethod.POST)
	public ModelAndView createNewCourse(@ModelAttribute @Valid Course course, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		if (result.hasErrors())
			return new ModelAndView("CourseFormNew");
		ModelAndView mav = new ModelAndView();

		cService.createCourse(course);
		//String message = "New student " + student.getNric() + " was successfully created.";
		mav.setViewName("redirect:/admin/course/list");
		return mav;
	}

	@RequestMapping(value = "/course/edit/{cIn}", method = RequestMethod.GET)
	public ModelAndView editCoursePage(@PathVariable String cIn) {
		ModelAndView mav = new ModelAndView("CourseFormEdit");
		mav.addObject("course", cService.findCourseByID(cIn));
		return mav;
	}

	@RequestMapping(value = "/course/edit/{cIn}", method = RequestMethod.POST)
	public ModelAndView editCourse(@ModelAttribute @Valid Course course, @PathVariable String cIn,
			BindingResult result, final RedirectAttributes redirectAttributes) throws StudentNotFound {
		System.out.println("course"+course.toString());
		if (result.hasErrors())
			return new ModelAndView("CourseFormEdit");
		cService.updateCourse(course);
		ModelAndView mav = new ModelAndView("redirect:/course/list");
		String message = "Course" + course.getCourseID() + " was successfully updated.";
		redirectAttributes.addFlashAttribute("message", message);
		return mav;
	}

	@RequestMapping(value = "/course/delete/{cIn}", method = RequestMethod.GET)
	public ModelAndView deleteCourse(@PathVariable String cIn, final RedirectAttributes redirectAttributes)
			throws CourseNotFound {
		Course course = cService.findCourseByID(cIn);
		cService.removeCourse(course);
		ModelAndView mav = new ModelAndView("redirect:/course/list");
		String message = "The course " + course.getCourseIndex() + " was successfully deleted.";
		redirectAttributes.addFlashAttribute("message", message);
		return mav;
	}
	@RequestMapping(value = "/course/students/{cid}", method = RequestMethod.GET)
	public ModelAndView listCoursesStudents(@PathVariable String cid) {
		ModelAndView mav = new ModelAndView("CourseStudentList");
		ArrayList<Student> students = cService.findCurrentStudentByCourse(cid);
		mav.addObject("student", students);
		String names = "Current Class";
		mav.addObject("name" ,names);
		String cID = cid;
		mav.addObject(cID);
		return mav;
	}
}

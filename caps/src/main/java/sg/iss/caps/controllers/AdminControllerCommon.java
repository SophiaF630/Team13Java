package sg.iss.caps.controllers;

import java.util.ArrayList;
import java.util.Calendar;

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
import sg.iss.caps.model.Studentcourse;
import sg.iss.caps.model.User;
import sg.iss.caps.services.CourseService;
import sg.iss.caps.services.StudentService;
import sg.iss.caps.services.StudentcourseService;
import sg.iss.caps.services.UserService;

@RequestMapping(value = "/admin")
@RestController
@Controller
public class AdminControllerCommon {
	@RequestMapping(value = "/index")
	public String index() {

		return "Hello World!";
	}

	@Autowired
	StudentService sService;
	@Autowired
	CourseService cService;
	@Autowired
	UserService uService;
	@Autowired
	StudentcourseService scService;

	@RequestMapping(value = "/home")
	public ModelAndView home() {
		ModelAndView mav = new ModelAndView("AdminHome");
		return mav;
	}// we need to update the view

	// this part is acadamicmanage
	@RequestMapping(value = "/academictime", method = RequestMethod.GET)
	public ModelAndView academicmanage() {
		ModelAndView mav = new ModelAndView("AcademicManage");
		return mav;
	}// we need to update the view by yiran

	// this parts starts students
	@RequestMapping(value = "/student/list", method = RequestMethod.GET)
	public ModelAndView listAll() {
		ModelAndView mav = new ModelAndView("StudentList");
		ArrayList<Student> students = sService.findAllStudents();
		ArrayList<User> users = new ArrayList<User>();
		for (Student student : students) {
			users.add(uService.findUserByID(student.getStudentID()));
		}
		mav.addObject("students", students);
		mav.addObject("Users", users);
		return mav;
	}// we need to update the view
		// this part need to know what to show

	@RequestMapping(value = "/student/create", method = RequestMethod.GET)
	public ModelAndView newStudentPage() {
		ModelAndView mav = new ModelAndView("StudentFormNew");
		//Auto generate StudentID
		Student student = new Student();
		student.setStudentID(newStudentID("Student"));
		mav.addObject("student", student);
		
		return mav;
	}// we need to update the view
		// this part need to fix(fix failed;cannot add record)

	@RequestMapping(value = "/student/create", method = RequestMethod.POST)
	public ModelAndView createNewStudent(@ModelAttribute Student student,
			@ModelAttribute User user, /*BindingResult result,*/ final RedirectAttributes redirectAttributes) {

		user.setUserID(newStudentID("Student"));
		user.setUserType("Student");
		
		/*
		 * if (result.hasErrors()) {
		 * 
		 * return new ModelAndView("StudentFormNew");}
		 */
		ModelAndView mav = new ModelAndView();				
		uService.createUser(user);
		Student s = new Student();
		s.setStudentID(newStudentID("Student"));
		s.setEnrollmentDate(student.getEnrollmentDate());
		sService.createStudent(s);
		// String message = "New student " + student.getNric() + " was successfully
		// created.";
		mav.setViewName("redirect:/admin/student/list");
		return mav;
	}
	
	

	// Generate UserID, use userType as input parameter
	public String newStudentID(String userType) {
		ArrayList<User> user = uService.findAllUserByType(userType);
		User lastUser = user.get(user.size() - 1);
		String lastUserID = lastUser.getUserID();
		int lastUserID_digit = 0;
		String newUserID_digit="";
		String newUserID ="";
		switch(userType) {
		case "Admin":
			lastUserID_digit = Integer.parseInt(lastUserID.substring(1, 8));
			newUserID_digit = String.format("%7s", Integer.toString(lastUserID_digit + 1)).replace(' ', '0');
			newUserID = "A" + newUserID_digit;
			break;	
		case "Lecturer":
			lastUserID_digit = Integer.parseInt(lastUserID.substring(1, 8));
			newUserID_digit = String.format("%7s", Integer.toString(lastUserID_digit + 1)).replace(' ', '0');
			newUserID = "L" + newUserID_digit;
		case "Student":
			lastUserID_digit = Integer.parseInt(lastUserID.substring(3, 8));
			newUserID_digit = String.format("%5s", Integer.toString(lastUserID_digit + 1)).replace(' ', '0');
			int currentYear = Calendar.getInstance().get(Calendar.YEAR);
			newUserID = "S" + Integer.toString(currentYear).substring(2, 4) + newUserID_digit;
		}	
		return newUserID;
	}

	@RequestMapping(value = "/student/edit/{sid}", method = RequestMethod.GET)
	public ModelAndView editStudentPage(@PathVariable String sid) {
		ModelAndView mav = new ModelAndView("StudentFormEdit");
		mav.addObject("student", sService.findStudent(sid));
		mav.addObject("user", uService.findUserByID(sid));
		return mav;
	}// we need to update the view

	@RequestMapping(value = "/student/edit/{sid}", method = RequestMethod.POST)
	public ModelAndView editStudent(@ModelAttribute @Valid Student student, @ModelAttribute @Valid User user,
			@PathVariable String sid, BindingResult result, final RedirectAttributes redirectAttributes)
			throws StudentNotFound {
		System.out.println("student" + student.toString());
		if (result.hasErrors())
			return new ModelAndView("StudentFormEdit");

//		student.setStudentID(student.getStudentID());
//		user.setUserID(student.getStudentID());
		Student s = new Student();
		// User u = new User();
		s.setStudentID(sid);
		s.setEnrollmentDate(student.getEnrollmentDate());
		user.setUserID(sid);
		user.setUserType("Student");
		// u.setPhone(user.getPhone());
		// sService.updateStudent(s);
		uService.updateUser(user);
		ModelAndView mav = new ModelAndView("redirect:/admin/student/list");
		String message = "Student" + student.getStudentID() + " was successfully updated.";
		redirectAttributes.addFlashAttribute("message", message);
		return mav;
	}

	@RequestMapping(value = "/student/delete/{sid}", method = RequestMethod.GET)
	public ModelAndView deleteStudent(@PathVariable String sid, final RedirectAttributes redirectAttributes)
			throws StudentNotFound {
		Student student = sService.findByStudentID(sid);
		User user = uService.findUserByID(sid);
		ArrayList<Studentcourse> studentcourse = scService.findStudentCourseByStudentId(sid);
		ModelAndView mav = new ModelAndView();
		if (studentcourse.isEmpty()) {
			sService.removeStudent(student);
			uService.removeUser(user);
			mav = new ModelAndView("redirect:/admin/student/list");
			String message = "The student " + student.getStudentID() + " was successfully deleted.";
			redirectAttributes.addFlashAttribute("message", message);
		} else {
			mav = new ModelAndView("redirect:/admin/student/list");
			String message = "The student " + student.getStudentID() + " cannot be deleted.";
			redirectAttributes.addFlashAttribute("message", message);
		}

		return mav;
	}

	// Course Info Part
	// List all course details
	/*@RequestMapping(value = "/coursedetails", method = RequestMethod.GET)
	public ModelAndView listAllStudentsCourseDetails() {
		ModelAndView mav = new ModelAndView("StudentCourseDetails");
		ArrayList<Course> courses = sService.findAllStudentsCourseDetails();
		mav.addObject("courses", courses);
		return mav;
	}
*/
	/*
	 * //List selected course
	 * 
	 * @RequestMapping(value = "/selectedcourse", method = RequestMethod.GET) public
	 * ModelAndView listAllSelectedCourse() { ModelAndView mav = new
	 * ModelAndView("SelectCourse"); ArrayList<Course> courses =
	 * sService.findAllSelectedCourse("ISS"); mav.addObject("courses", courses);
	 * return mav; }
	 */

	//Show student current enrollment
	@RequestMapping(value = "/student/courses/{sid}", method = RequestMethod.GET)
	public ModelAndView listStudentsCourses(@PathVariable String sid) {
		ModelAndView mav = new ModelAndView("StudentCourseList");
		ArrayList<Studentcourse> studentcourse_enrolled = sService.findAllStudentCourseByStatus("Enrolled", sid);
		ArrayList<Studentcourse> studentcourse_pending = sService.findAllStudentCourseByStatus("Pending", sid);
		ArrayList<Studentcourse> allstudentcourse = new ArrayList<Studentcourse>();
		allstudentcourse.addAll(studentcourse_enrolled);
		allstudentcourse.addAll(studentcourse_pending);
		ArrayList<Studentcourse> studentcourse = new ArrayList<Studentcourse>();
		for (Studentcourse sc: allstudentcourse) {
			if(sc.getCourse().getEndDate().after(Calendar.getInstance().getTime()))
				studentcourse.add(sc);			
		}
		mav.addObject("studentcourse", studentcourse);
		int status = 1;
		mav.addObject("status", status);
		mav.addObject("sid", sid);
		return mav;
	}
	
	//Show student historical enrollment
	@RequestMapping(value = "/student/history/{sid}", method = RequestMethod.GET)
	public ModelAndView listStudentsHistoryCourses(@PathVariable String sid) {
		ModelAndView mav = new ModelAndView("StudentCourseList");

		ArrayList<Studentcourse> studentcourse_enrolled = sService.findAllStudentCourseByStatus("Enrolled", sid);
		ArrayList<Studentcourse> studentcourse_pending = sService.findAllStudentCourseByStatus("Pending", sid);
		ArrayList<Studentcourse> studentcourse_finished = sService.findAllStudentCourseByStatus("Finished", sid);
		ArrayList<Studentcourse> studentcourse_cancelled = sService.findAllStudentCourseByStatus("Cancelled", sid);
		ArrayList<Studentcourse> studentcourse_rejected = sService.findAllStudentCourseByStatus("Rejected", sid);
		ArrayList<Studentcourse> studentcourse = new ArrayList<Studentcourse>();
		studentcourse.addAll(studentcourse_enrolled);
		studentcourse.addAll(studentcourse_pending);
		studentcourse.addAll(studentcourse_finished);
		studentcourse.addAll(studentcourse_cancelled);
		studentcourse.addAll(studentcourse_rejected);
		mav.addObject("studentcourse", studentcourse);
		int status = 2;
		mav.addObject("status", status);
		mav.addObject("sid", sid);
		return mav;
	}
	
	//Reject student from course
	@RequestMapping(value = "/student/rejectcourse/{cIn}&{sid}", method = RequestMethod.GET)
	public ModelAndView rejectStudentCourse(@PathVariable String sid, @PathVariable String cIn, final RedirectAttributes redirectAttributes) {
		ModelAndView mav = new ModelAndView();
		ArrayList<Studentcourse> studentcourseList = scService.findStudentCourseByStudentId(sid);
		Studentcourse studentcourse = new Studentcourse();
		for (Studentcourse sc:studentcourseList) {
			if (sc.getCourse().getCourseIndex().equals(cIn)) {
				sc.setId(sc.getId());
				sc.setStatus("Rejected");
				sc.setEnrollTime(Calendar.getInstance().getTime());
				scService.updateStudentCourse(sc);
			}
		}	
		
		mav = new ModelAndView("redirect:/admin/student/courses/{sid}");
		return mav;
	}
	
	//Approve student to course
		@RequestMapping(value = "/student/approvecourse/{cIn}&{sid}", method = RequestMethod.GET)
		public ModelAndView approveStudentCourse(@PathVariable String sid, @PathVariable String cIn, final RedirectAttributes redirectAttributes) {
			ModelAndView mav = new ModelAndView();
			ArrayList<Studentcourse> studentcourseList = scService.findStudentCourseByStudentId(sid);
			Studentcourse studentcourse = new Studentcourse();
			for (Studentcourse sc:studentcourseList) {
				if (sc.getCourse().getCourseIndex().equals(cIn)) {
					sc.setId(sc.getId());
					sc.setStatus("Enrolled");
					sc.setEnrollTime(Calendar.getInstance().getTime());
					scService.updateStudentCourse(sc);
				}
			}	
			
			mav = new ModelAndView("redirect:/admin/student/courses/{sid}");
			return mav;
		}
		
	
	/*//Show student historical enrollment
	@RequestMapping(value = "/student/history/{sid}", method = RequestMethod.GET)
	public ModelAndView listStudentsHistoryCourses(@PathVariable String sid) {
		ModelAndView mav = new ModelAndView("StudentCourseList");
		ArrayList<Course> courses = sService.findHistoryCoursesByStudentID(sid);
		mav.addObject("courses", courses);
		int status = 2;
		mav.addObject("status", status);
		mav.addObject("sid", sid);
		return mav;
	}
	
	//Remove student from course
	@RequestMapping(value = "/student/removecourse/{cIn}&{sid}", method = RequestMethod.GET)
	public ModelAndView removeStudentFromCourse(@PathVariable String sid, @PathVariable String cIn, final RedirectAttributes redirectAttributes) {
		ModelAndView mav = new ModelAndView();
		ArrayList<Studentcourse> studentcourseList = scService.findStudentCourseByStudentId(sid);
		Studentcourse studentcourse = new Studentcourse();
		for (Studentcourse sc:studentcourseList) {
			if (sc.getCourse().getCourseIndex() == cIn) {
				sc.setStatus("Cancelled");
				scService.updateStudentCourse(sc);
			}
		}	
		
		mav = new ModelAndView("redirect:/admin/student/courses/{sid}");
		return mav;
	}
	*/
	

	// this part starts course
	
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
		// String message = "New student " + student.getNric() + " was successfully
		// created.";
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
	public ModelAndView editCourse(@ModelAttribute @Valid Course course, @PathVariable String cIn, BindingResult result,
			final RedirectAttributes redirectAttributes) throws StudentNotFound {
		System.out.println("course" + course.toString());
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
		mav.addObject("name", names);
		String cID = cid;
		mav.addObject(cID);
		return mav;
	}
}

package sg.iss.caps.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
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

import antlr.collections.List;
import sg.iss.caps.services.StudentService;
import sg.iss.caps.services.StudentcourseService;
import sg.iss.caps.exception.StudentNotFound;
import sg.iss.caps.model.Course;
import sg.iss.caps.model.Student;
import sg.iss.caps.model.StudentCourseRegisterDto;
import sg.iss.caps.model.StudentGrade;
import sg.iss.caps.model.Studentcourse;
import sg.iss.caps.model.StudentcoursePK;

import sg.iss.caps.services.EmailServiceImpl;

@RequestMapping(value = "/student")
@RestController
@Controller
public class StudentController {

	@RequestMapping(value = "/index")
	public String index() {

		return "Hello World!";
	}

	@Autowired
	StudentService sService;
	@Autowired
	StudentcourseService scService;
	@Autowired
	EmailServiceImpl emailService;

	// @Autowired
	// private StudentValidator sValidator;

	// @InitBinder("student")
	// private void initDepartmentBinder(WebDataBinder binder) {
	// binder.addValidators(sValidator);
	// }

	// Student Info Part
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
		// String message = "New student " + student.getNric() + " was successfully
		// created.";
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
		System.out.println("student" + student.toString());
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

	// Course Info Part
	// List all course details
	@RequestMapping(value = "/coursedetails", method = RequestMethod.GET)
	public ModelAndView listAllStudentsCourseDetails() {
		ModelAndView mav = new ModelAndView("StudentCourseDetails");
		ArrayList<Course> courses = sService.findAllCurrentSemesterCourse(Calendar.getInstance().getTime());
		ArrayList<Course> selectedcourse_history = sService.findHistoryCoursesByStudentID("S1800001");
		ArrayList<Course> unavaCourses = new ArrayList<Course>();
		//ArrayList<Course> avaCourses = sService.findAllCurrentSemesterCourse(Calendar.getInstance().getTime());
		ArrayList<Course> avaCourses = new ArrayList<Course>();
		for (Course c : courses) {
			for (Course sch : selectedcourse_history) {
				if (c.getCourseID().equals(sch.getCourseID())) {
					unavaCourses.add(c);
				}
			}
		}
		for (Course c : courses) {
			if (!unavaCourses.contains(c))
				avaCourses.add(c);
		}
		unavaCourses  = new ArrayList<Course>(new HashSet<Course>(unavaCourses));
		avaCourses  = new ArrayList<Course>(new HashSet<Course>(avaCourses));
		
		mav.addObject("courses", courses);
		mav.addObject("avaCourses", avaCourses);
		mav.addObject("unavaCourses", unavaCourses);
		return mav;
	}

	// Select course
	@RequestMapping(value = "/coursedetails", method = RequestMethod.POST)
	public ModelAndView selectCourse(@ModelAttribute StudentCourseRegisterDto studentCourseRegisterDto) {
		ModelAndView mav = new ModelAndView();
		// get form data
		System.out.println(studentCourseRegisterDto.getStudentId());
		System.out.println(String.join(", ", studentCourseRegisterDto.getCourseIndexes()));

		// prepare new page model data
		// UserSession us = (UserSession) session.getAttribute("USERSESSION");
		Studentcourse sc = new Studentcourse();
		Student student = sService.findByStudentID(studentCourseRegisterDto.getStudentId());
		String[] selectedCourses = studentCourseRegisterDto.getCourseIndexes();

		for (String courseIndex : selectedCourses) {
			StudentcoursePK spk = scService.findStudentcoursePK("S1800001", courseIndex);
			Course course = null;
			// sc.setCourse(course);
			// sc.setStudent(student);
			sc.setId(spk);
			sc.setCAGrade(-1);
			sc.setExamGrade(-1);
			sc.setEnrollTime(Calendar.getInstance().getTime());
			sc.setStatus("OnPlan");

			scService.createStudentCourse(sc);
		}
		mav.setViewName("redirect:/student/preview");
		return mav;
	}

	// Submit selection
	@RequestMapping(value = "/preview", method = RequestMethod.POST)
	public ModelAndView submitSelection(@ModelAttribute Studentcourse studentcourse) {

		String studentId = "S1800001";
		//get to emails
		String[] to = {"feng.yuxi@u.nus.edu", "e0338082@u.nus.edu", "chenguowei1991@gmail.com"};
		String subject = "Enrollment Confirmation";
		String body = "Hi Applicant Name! Congratulations! Your registration is confirmed! You have successfully completed "
				+ "registration. Below is a list of the details of the course you have registered : If you have further queries or need clarification, "
				+ "you may reach us at our official email at SA47Team13@gmail.com or call us at 62314231. Thank you";
		ArrayList<Studentcourse> sc_OnPlan = sService.findAllStudentCourseByStatus("OnPlan", studentId);
		for (Studentcourse sc : sc_OnPlan) {
			if (scService.getStudentCount(sc.getCourse().getCourseIndex()) >= sc.getCourse().getClassSize()) {
				sc.setId(sc.getId());
				sc.setStatus("Pending");
				sc.setEnrollTime(Calendar.getInstance().getTime());
			} else {
				sc.setId(sc.getId());
				sc.setStatus("Enrolled");
				sc.setEnrollTime(Calendar.getInstance().getTime());
			}
			scService.updateStudentCourse(sc);
		}
		try {
			emailService.setMailServerProperties();
			emailService.createEmailMessage(to, subject, body);
			emailService.sendEmail();
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView("redirect:/student/selectedcourse");
		return mav;

	}

	// List selected course
	@RequestMapping(value = "/selectedcourse", method = RequestMethod.GET)
	public ModelAndView listAllSelectedCourse() {
		String studentId = "S1800001";
		ModelAndView mav = new ModelAndView("SelectedCourseView");
		ArrayList<Studentcourse> studentcourse_enrolled = sService.findAllStudentCourseByStatus("Enrolled", "S1800001");
		ArrayList<Studentcourse> studentcourse_pending = sService.findAllStudentCourseByStatus("Pending", "S1800001");
		ArrayList<Studentcourse> studentcourse_cancelled = sService.findAllStudentCourseByStatus("Cancelled",
				"S1800001");
		ArrayList<Studentcourse> studentcourse = new ArrayList<Studentcourse>();
		studentcourse.addAll(studentcourse_enrolled);
		studentcourse.addAll(studentcourse_pending);
		studentcourse.addAll(studentcourse_cancelled);
		mav.addObject("studentcourse", studentcourse);
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

	// Selected Course Preview
	@RequestMapping(value = "/preview", method = RequestMethod.GET)
	public ModelAndView selectedCoursePreview() {
		ModelAndView mav = new ModelAndView("SelectedCoursePreview");
		ArrayList<Studentcourse> studentcourse_onPlan = sService.findAllStudentCourseByStatus("OnPlan", "S1800001");
		ArrayList<Studentcourse> studentcourse_enrolled = sService.findAllStudentCourseByStatus("Enrolled", "S1800001");
		ArrayList<Studentcourse> studentcourse = new ArrayList<Studentcourse>();
		studentcourse.addAll(studentcourse_onPlan);
		studentcourse.addAll(studentcourse_enrolled);
		Map<String, Boolean> collisions = findCourseCollisions(studentcourse);
		mav.addObject("studentcourse", studentcourse);
		mav.addObject("collisions", collisions);
		return mav;
	}

	private Map<String, Boolean> findCourseCollisions(ArrayList<Studentcourse> studentCourses) {
		Map<String, Integer> courseSchedule = new HashMap<String, Integer>();
		Map<String, Boolean> collisions = new HashMap<String, Boolean>();
		if (studentCourses != null) {
			for (Studentcourse st : studentCourses) {
				String[] courseTimeSlots = st.getCourse().getLectureSchedule().split(",");
				for (String slot : courseTimeSlots) {
					if (!courseSchedule.containsKey(slot)) {
						courseSchedule.put(slot, 1);

					} else {
						int totalCourseAtSameSlot = courseSchedule.get(slot) + 1;
						courseSchedule.put(slot, totalCourseAtSameSlot);
					}
				}
			}
			Set<String> keys = courseSchedule.keySet();
			String[] allSlots = keys.toArray(new String[keys.size()]);
			for (String slot : allSlots) {
				if (courseSchedule.get(slot) > 1) {
					collisions.put(slot, true);
					System.out.print("Collission: " + slot);
				} else {
					collisions.put(slot, false);
					System.out.print("Not Collission: " + slot);
				}
			}
		}
		return collisions;
	}

	// View Grade
	@RequestMapping(value = "/grade", method = RequestMethod.GET)
	public ModelAndView studentViewGrade() {
		ModelAndView mav = new ModelAndView("StudentViewGrade");
		ArrayList<Studentcourse> studentcourse = sService.studentViewGrade("S1800001", "Finished");
		StudentGrade sg = new StudentGrade();
		sg.setGPA(scService.calculateStudentGPA("S1800001"));
		sg.setCGPA(scService.calculateStudentCGPA("S1800001"));

		String gpa = Double.toString(sg.getGPA());
		String cgpa = Double.toString(sg.getCGPA());
		mav.addObject("studentcourse", studentcourse);
		mav.addObject("gpa", gpa);
		mav.addObject("cgpa", cgpa);
		return mav;
	}

}

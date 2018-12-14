package sg.iss.caps.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpSession;
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
	

	// List all course details
	@RequestMapping(value = "/coursedetails/{sid}", method = RequestMethod.GET)
	public ModelAndView listAllStudentsCourseDetails(@PathVariable String sid, HttpSession session) {
		ModelAndView mav = new ModelAndView("StudentCourseDetails");
		UserSession us = (UserSession) session.getAttribute("USERSESSION");
		String studentID = sid;
		ArrayList<Course> courses = sService.findAllCurrentSemesterCourse(Calendar.getInstance().getTime());
		ArrayList<Course> selectedcourse_history = sService.findHistoryCoursesByStudentID(sid);
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
		
		mav.addObject("studentID", studentID);
		mav.addObject("courses", courses);
		mav.addObject("avaCourses", avaCourses);
		mav.addObject("unavaCourses", unavaCourses);
		
		if (us==null) {
			return new ModelAndView("redirect:/login/fail");
		}
		return us.checkUserType("Student", mav);
	}

	@RequestMapping(value = "/coursedetailsjump", method = RequestMethod.GET)
	public ModelAndView coursedetailsjump(HttpSession session) {
		UserSession us = (UserSession) session.getAttribute("USERSESSION");
		if(us==null) {String url = "redirect:/student/coursedetails/"+"S1800001";}
		String sid = us.getUser().getUserID();
		String url = "redirect:/student/coursedetails/"+sid;
		return new ModelAndView(url);
	}
	
	// Select course
	@RequestMapping(value = "/coursedetails/{sid}", method = RequestMethod.POST)
	public ModelAndView selectCourse(@ModelAttribute StudentCourseRegisterDto studentCourseRegisterDto, @PathVariable String sid, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		String studentID = sid;
		UserSession us = (UserSession) session.getAttribute("USERSESSION");
		// get form data
		System.out.println(studentCourseRegisterDto.getStudentId());
		System.out.println(String.join(", ", studentCourseRegisterDto.getCourseIndexes()));

		// prepare new page model data
		// UserSession us = (UserSession) session.getAttribute("USERSESSION");
		Studentcourse sc = new Studentcourse();
		Student student = sService.findByStudentID(studentCourseRegisterDto.getStudentId());
		String[] selectedCourses = studentCourseRegisterDto.getCourseIndexes();
		for (String courseIndex : selectedCourses) {
			StudentcoursePK spk = scService.findStudentcoursePK(sid, courseIndex);
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
		mav.setViewName("redirect:/student/preview/{sid}");
		mav.addObject("studentID", studentID);
		if (us==null) {
			return new ModelAndView("redirect:/login/fail");
		}
		return us.checkUserType("Student", mav);

	}
	
	@RequestMapping(value = "/previewjump", method = RequestMethod.GET)
	public ModelAndView previewjump(HttpSession session) {
		UserSession us = (UserSession) session.getAttribute("USERSESSION");
		String sid = us.getUser().getUserID();
		String url = "redirect:/student/preview/"+sid;
		return new ModelAndView(url);
	}
	// Selected Course Preview
		@RequestMapping(value = "/preview/{sid}", method = RequestMethod.GET)
		public ModelAndView selectedCoursePreview(@PathVariable String sid, HttpSession session) {
			ModelAndView mav = new ModelAndView("SelectedCoursePreview");
			String studentID = sid;
			UserSession us = (UserSession) session.getAttribute("USERSESSION");
			ArrayList<Studentcourse> studentcourse_onPlan = sService.findAllStudentCourseByStatus("OnPlan", sid);
			ArrayList<Studentcourse> studentcourse_enrolled = sService.findAllStudentCourseByStatus("Enrolled", sid);
			ArrayList<Studentcourse> studentcourse = new ArrayList<Studentcourse>();
			Boolean hasCollision = false;
			studentcourse.addAll(studentcourse_onPlan);
			studentcourse.addAll(studentcourse_enrolled);
			
			Map<String, Boolean> collisions = findCourseCollisions(studentcourse);
			if(collisions.keySet().size()>0) {
				hasCollision = true;
			}
			mav.addObject("studentcourse", studentcourse);
			mav.addObject("studentID", studentID);
			mav.addObject("collisions", collisions);
			mav.addObject("hasCollision", hasCollision);
			
			if (us==null) {
				return new ModelAndView("redirect:/login/fail");
			}
			return us.checkUserType("Student", mav);

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
		
	
	//Delete course from preview
	@RequestMapping(value = "/delete/{sid}/{courseIndex}", method = RequestMethod.GET)
	public ModelAndView deleteStudentCourse(@PathVariable String sid, @PathVariable String courseIndex, final RedirectAttributes redirectAttributes, HttpSession session)
			throws StudentNotFound {
		UserSession us = (UserSession) session.getAttribute("USERSESSION");
		String studentID = sid;
		ArrayList<Studentcourse> studentCourses = scService.findStudentCourseByStudentId(sid);
 		System.out.println(sid);
 		System.out.println(studentCourses.size());
 		for (Studentcourse sc : studentCourses) {
 			if(sc.getCourse().getCourseIndex().equals(courseIndex)) {
 				System.out.println("TRY DELETING: " + sc.getCourse().getCourseIndex());
 				scService.deleteStudentCourse(sc);
 				break;
 			}
 			System.out.println(sc.getCourse().getCourseIndex());
 		}
		ModelAndView mav = new ModelAndView("redirect:/student/preview/{sid}");
		mav.addObject("studentID", studentID);
		String message = "The course: " + courseIndex + " was successfully deleted.";
		redirectAttributes.addFlashAttribute("message", message);
		if (us==null) {
			return new ModelAndView("redirect:/login/fail");
		}
		return us.checkUserType("Student", mav);
	}
	
	// Submit selection
	@RequestMapping(value = "/preview/{sid}", method = RequestMethod.POST)
	public ModelAndView submitSelection(@ModelAttribute Studentcourse studentcourse, @PathVariable String sid, HttpSession session) {
		UserSession us = (UserSession) session.getAttribute("USERSESSION");
		String studentID = sid;
		//get to emails
		String[] to = {"feng.yuxi@u.nus.edu", "e0338082@u.nus.edu", "chenguowei1991@gmail.com"};
		String subject = "Enrollment Confirmation";
		String body = "Hi Applicant Name! Congratulations! Your registration is confirmed! You have successfully completed "
				+ "registration. Below is a list of the details of the course you have registered : If you have further queries or need clarification, "
				+ "you may reach us at our official email at SA47Team13@gmail.com or call us at 62314231. Thank you";
		ArrayList<Studentcourse> sc_OnPlan = sService.findAllStudentCourseByStatus("OnPlan", sid);
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
		ModelAndView mav = new ModelAndView("redirect:/student/selectedcourse/{sid}");
		mav.addObject("studentID", studentID);
		
		if (us==null) {
			return new ModelAndView("redirect:/login/fail");
		}
		return us.checkUserType("Student", mav);

	}

	

	@RequestMapping(value = "/selectedcoursejump", method = RequestMethod.GET)
	public ModelAndView selectedcoursejump(HttpSession session) {
		UserSession us = (UserSession) session.getAttribute("USERSESSION");
		String sid = us.getUser().getUserID();
		String url = "redirect:/student/selectedcourse/"+sid;
		return new ModelAndView(url);
	}
	// List selected course
		@RequestMapping(value = "/selectedcourse/{sid}", method = RequestMethod.GET)
		public ModelAndView listAllSelectedCourse(@PathVariable String sid, HttpSession session) {		
			String studentID = sid;
			ModelAndView mav = new ModelAndView("SelectedCourseView");
			UserSession us = (UserSession) session.getAttribute("USERSESSION");
			ArrayList<Studentcourse> studentcourse_enrolled = sService.findAllStudentCourseByStatus("Enrolled", sid);
			ArrayList<Studentcourse> studentcourse_pending = sService.findAllStudentCourseByStatus("Pending", sid);
			ArrayList<Studentcourse> studentcourse_cancelled = sService.findAllStudentCourseByStatus("Cancelled",
					sid);
			ArrayList<Studentcourse> studentcourse = new ArrayList<Studentcourse>();
			studentcourse.addAll(studentcourse_enrolled);
			studentcourse.addAll(studentcourse_pending);
			studentcourse.addAll(studentcourse_cancelled);
			mav.addObject("studentcourse", studentcourse);
			mav.addObject("studentID", studentID);
			
			if (us==null) {
				return new ModelAndView("redirect:/login/fail");
			}
			return us.checkUserType("Student", mav);
		}

		@RequestMapping(value = "/courses/{sid}", method = RequestMethod.GET)
		public ModelAndView listStudentsCourses(@PathVariable String sid, HttpSession session) {
			ModelAndView mav = new ModelAndView("StudentCourseList");
			String studentID = sid;
			UserSession us = (UserSession) session.getAttribute("USERSESSION");
			ArrayList<Course> courses = sService.findCurrentCoursesByStudentID(sid);
			mav.addObject("courses", courses);
			String names = "Current Class";
			mav.addObject("name" ,names);
			mav.addObject("studentID", studentID);
			if (us==null) {
				return new ModelAndView("redirect:/login/fail");
			}
			return us.checkUserType("Student", mav);
		}

		@RequestMapping(value = "/gradejump", method = RequestMethod.GET)
		public ModelAndView gradejump(HttpSession session) {
			UserSession us = (UserSession) session.getAttribute("USERSESSION");
			String sid = us.getUser().getUserID();
			String url = "redirect:/student/grade/"+sid;
			return new ModelAndView(url);
		}
	// View Grade
	@RequestMapping(value = "/grade/{sid}", method = RequestMethod.GET)
	public ModelAndView studentViewGrade(@PathVariable String sid, HttpSession session) {
		ModelAndView mav = new ModelAndView("StudentViewGrade");
		String studentID = sid;
		UserSession us = (UserSession) session.getAttribute("USERSESSION");
		ArrayList<Studentcourse> studentcourse = sService.studentViewGrade(sid, "Finished");
		StudentGrade sg = new StudentGrade();
		sg.setGPA(scService.calculateStudentGPA(sid));
		sg.setCGPA(scService.calculateStudentCGPA(sid));

		String gpa = Double.toString(sg.getGPA());
		String cgpa = Double.toString(sg.getCGPA());
		mav.addObject("studentcourse", studentcourse);
		mav.addObject("gpa", gpa);
		mav.addObject("cgpa", cgpa);
		mav.addObject("studentID", studentID);
		if (us==null) {
			return new ModelAndView("redirect:/login/fail");
		}
		return us.checkUserType("Student", mav);
	}

}

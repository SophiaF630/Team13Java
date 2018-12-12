package sg.iss.caps.controllers;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import sg.iss.caps.model.Student;

@RequestMapping(value="/admin")
@RestController
@Controller
public class AdminControllerCommon {
	@RequestMapping(value="/index")
	public String index() {
		
		return "Hello World!";
	}
	@RequestMapping(value = "/home")
	public ModelAndView home() {
		ModelAndView mav = new ModelAndView("AdminHome");
		return mav;
	}
	@RequestMapping(value = "/academictime", method = RequestMethod.GET)
	public ModelAndView academicmanage() {
		ModelAndView mav = new ModelAndView("AcademicManage");
		return mav;
	}
}

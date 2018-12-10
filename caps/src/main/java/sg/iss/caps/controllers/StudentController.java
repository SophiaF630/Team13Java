package sg.iss.caps.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RequestMapping(value="/home")
@RestController
public class StudentController {
	
	@RequestMapping(value="/index")
	public String index() {
		
		return "Hello World!";
	}
}

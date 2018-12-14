package sg.iss.caps.controllers;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.context.annotation.ScopedProxyMode;

import sg.iss.caps.model.User;
import sg.iss.caps.services.UserService;
@RequestMapping(value = "/login")
@Controller
public class UserController {
	@Autowired
	UserService uService;
	
	@RequestMapping(value = "/index",method = RequestMethod.GET)
	public ModelAndView loginStart() {
		ModelAndView mav = new ModelAndView("Login");
		return mav;
	}
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ModelAndView loginResponse(@ModelAttribute("username")String username,@ModelAttribute("password")String password, HttpSession session, BindingResult result ) {
		ModelAndView mav = new ModelAndView("Login");
		if (result.hasErrors())
			return new ModelAndView("redirect:/lecture/index");
		UserSession us = new UserSession();
		if (username != null && password != null) {
			User u = uService.authenticate(username, password);
			if(u == null)
				return new ModelAndView("redirect:/login/fail");
			us.setUser(u);
			// PUT CODE FOR SETTING SESSION ID
			us.setSessionId(session.getId());
			mav = new ModelAndView("redirect:/login/fail");
			switch(u.getUserType()) {
			case "Admin":
				us.setUserType("Admin");
				session.setAttribute("USERSESSION", us);
				return new ModelAndView("redirect:/admin/home");
			case "Lecturer":
				us.setUserType("Lecturer");
				session.setAttribute("USERSESSION", us);
				return new ModelAndView("redirect:/lecturer/index");
			case "Student":
				us.setUserType("Student");
				session.setAttribute("USERSESSION", us);
				return new ModelAndView("redirect:/student/coursedetails/"+us.getUser().getUserID());
			default:
				us = null;
				session.setAttribute("USERSESSION", us);
				return new ModelAndView("redirect:/login/fail");
			}
		}
		return mav;
	}
	@RequestMapping(value = "/fail")
	public ModelAndView loginFail() {
		ModelAndView mav = new ModelAndView("LoginError");
		return mav;
	}
	@RequestMapping(value = "/exit")
	public ModelAndView logout(HttpSession session) {
		ModelAndView mav = new ModelAndView("Logout");
		UserSession us = (UserSession) session.getAttribute("USERSESSION");
		us = null;
		session.setAttribute("USERSESSION", us);
		return mav;
	}
/*	 
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
        String errorMessage = null;
        if(error != null) {
            errorMessage = "Username or Password is incorrect !!";
        }
        if(logout != null) {
            errorMessage = "You have been successfully logged out !!";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "Login";
    }
  
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){   
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout=true";
    }*/
    
}

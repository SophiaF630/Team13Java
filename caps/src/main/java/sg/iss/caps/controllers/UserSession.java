package sg.iss.caps.controllers;

import java.util.ArrayList;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import sg.iss.caps.model.User;


@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class UserSession {
	
	private String sessionId = null;
	private User user = null;
	private String userType = null;
	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public UserSession() {
		super();
	}

	public UserSession(String sessionId, User user) {
		super();
		this.sessionId = sessionId;
		this.user = user;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sessionId == null) ? 0 : sessionId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserSession other = (UserSession) obj;
		if (sessionId == null) {
			if (other.sessionId != null)
				return false;
		} else if (!sessionId.equals(other.sessionId))
			return false;
		return true;
	}
	
/*	public boolean checkUser(String UserType) 
	{
		if (user == null) {
			return false;
		}
		try {
			if(user.getUserType() == UserType) {
				return true;
			}
			else
				return false;
		}catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}*/
	
	public ModelAndView checkUserType(String UserType,ModelAndView mav) {
		if (userType == UserType) {
			return mav;
		}
		else {
			return new ModelAndView("Login");
		}
		
	}
	


}

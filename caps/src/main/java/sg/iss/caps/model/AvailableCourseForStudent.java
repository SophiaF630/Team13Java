package sg.iss.caps.model;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AvailableCourseForStudent {
	
	private ArrayList<Course> selectedCourse;

	public AvailableCourseForStudent() {
		super();
		// TODO Auto-generated constructor stub
	}

//	public SelectedCourse(ArrayList<Course> selectedCourse) {
//		super();
//		this.selectedCourse = selectedCourse;
//	}

	public ArrayList<Course> getSelectedCourse() {
		return selectedCourse;
	}

	public void setSelectedCourse(ArrayList<Course> selectedCourse) {
		this.selectedCourse = selectedCourse;
	}
	
	

}

import Controller.add.AddController;
import Controller.grade.GradeController;
import dto.add.AddDTO;
import dto.user.UserDTO;

public class MainLee2 {

	public static void main(String[] args) {

//		AddController.addEditor();
		
		UserDTO userDTO = new UserDTO(4, "박명수", 0, "입문자");
		GradeController.gradeUpdate(userDTO, 3600);

	}
}

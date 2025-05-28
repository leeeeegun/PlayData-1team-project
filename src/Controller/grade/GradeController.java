package Controller.grade;

import dao.grade.GradeDao;
import dto.user.UserDTO;

public class GradeController {
	public static void gradeUpdate(UserDTO userDTO, int total) {
		GradeDao dao = new GradeDao();
		String oldGrade = userDTO.getGrade();
		String newGrade = null;

		if (total >= 3000) {
			newGrade = "마스터";
		} else if (total >= 1200) {
			newGrade = "연구자";
		} else if (total >= 300) {
			newGrade = "수강생";
		} else {
			newGrade = "입문자";
		}
		
		if(!oldGrade.equals(newGrade)) {
			dao.Grade(userDTO, newGrade);
			
			System.out.println("🎉 축하합니다, " + userDTO.getName() + "님" );
			System.out.println("등급이 '" + oldGrade + "'에서" + " ▶️ '" + newGrade + "'로 승급되셨습니다!'");
			
		}

		System.out.println("\n회원명: " + userDTO.getName());
		System.out.println("수강 시간: " + total + "분");
		System.out.println("현재 등급: " + userDTO.getGrade());

	}
}

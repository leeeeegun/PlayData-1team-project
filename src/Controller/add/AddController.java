package Controller.add;

import java.util.Random;

import dao.add.AddDao;
import dto.add.AddDTO;
import dto.user.UserDTO;

public class AddController {
	
	public static void addEditor() {
		AddDao dao = new AddDao();
		Random random = new Random();
		int num = random.nextInt(3) + 1;
			
		AddDTO add = dao.select(num);
		
		System.out.println(add.getContent());
		System.out.println(add.getTitle());
	}
}
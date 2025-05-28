package Controller.uploading;

import java.sql.SQLException;
import java.util.Scanner;

import Controller.landing.LandingView;
import dao.uploading.UploadingDAO;
import dto.uploading.UploadingDTO;
import dto.user.UserDTO;

public class UploadingController {

	    
	public static void uploadInsert(UserDTO userDTO) throws SQLException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        UploadingDAO dao = new UploadingDAO();
        System.out.println("===== 강 의 업 로 드 =====");
        System.out.print("제목 : ");
        String title = sc.next();
        System.out.print("내용 : ");
        String description = sc.next();
        System.out.print("금액 : ");
        int price = sc.nextInt();
        sc.nextLine();
        System.out.print(" 카테고리 : ");
        String category = sc.next();
    
        UploadingDTO upload = new UploadingDTO(title,description, userDTO.getId(), price,category);

        dao.uploadInsert(upload, userDTO);

        LandingView.LandingLogin(userDTO);
    }
	
}

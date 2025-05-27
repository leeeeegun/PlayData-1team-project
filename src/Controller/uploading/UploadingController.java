package Controller.uploading;

import java.util.Scanner;
import dao.uploading.UploadingDAO;
import dto.uploading.UploadingDTO;

public class UploadingController {
	 Scanner sc = new Scanner(System.in);
	 UploadingDAO dao = new UploadingDAO();
	    
	public void uploadInsert() {
    System.out.println("===== 강 의 업 로 드 =====");
    System.out.print("제목 : ");
    String title = sc.next();
    System.out.print("내용 : ");
    String description = sc.next();
    System.out.print("작성자 : ");
    int instructor_id = sc.nextInt();
    System.out.print("금액 : ");
    int price = sc.nextInt();
    System.out.print(" 카테고리 : ");
    String category = sc.next();
    
    UploadingDTO upload = new UploadingDTO(title,description,instructor_id,price,category);
}
	
}

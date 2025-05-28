package Controller.userinfo;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import Controller.landing.LandingView;
import dao.userinfo.UserInfoDao;
import dto.user.UserDTO;
import dto.userinfo.UserInfoDTO;

public class UserInfoController {
	//마이페이지
	public static void myPage(UserDTO userDTO) throws SQLException, InterruptedException {
		
        String loggedInMenu1 = "============== 마이페이지 ==============";
        String loggedInMenu2 = "1. 회원 정보 보기\n2. 회원 정보 수정\n3. 회원 정보 삭제\n0. 메인 화면으로 이동"
        								+"\n======================================\n메뉴 번호를 입력해주세요 :";
        

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println(loggedInMenu1);
            System.out.println(loggedInMenu2);
            int choice = sc.nextInt();
            switch (choice) {
                case 1: viewUserInfo(userDTO); break;
                case 2: editUserInfo(userDTO); break;
                case 3: deleteUserInfo(userDTO); break;
                case 0:
					LandingView.LandingLogin(userDTO); break;
                default:
                    System.out.println("잘못된 번호를 입력하셨습니다. 다시 입력해주세요.");
            }
        }
    }
	
	
	//회원정보 보기
	public static void viewUserInfo(UserDTO userDTO) {
		UserInfoDao userDao = new UserInfoDao();
		
		ArrayList<UserInfoDTO> list = userDao.getUserInfo(userDTO);
		for(UserInfoDTO userInfo : list) {
			System.out.println("===================================");
			System.out.println("[" + userInfo.getName() + "님 정보]");
			System.out.println("아이디: "+userInfo.getLogin_id());
			System.out.println("성명: "+userInfo.getName());
			System.out.println("생일: "+userInfo.getBirth_date());
			System.out.println("휴대전화: "+userInfo.getPhone());
			System.out.println("포인트:"+userInfo.getMoney());
			System.out.println("등급: "+userInfo.getGrade());
			System.out.println("강의 누적시간: "+userInfo.getTotal_watch_time());
			
		}
	}
	//회원정보수정
	 private static void editUserInfo(UserDTO userDTO) {
	        Scanner sc = new Scanner(System.in);
	        UserInfoDao userDao = new UserInfoDao();
	        System.out.println("수정할 회원님의 정보를 입력해주세요.");
//	        System.out.print("고유번호: ");
//	        int id = sc.nextInt();
	        System.out.print("수정할 비밀번호: ");
	        String newPass = sc.next();
	        System.out.print("수정할 성명: ");
	        String newName = sc.next();
//	        System.out.print("생년월일(xxxx-xx-xx): ");
//	        String newBirth_date = sc.next();

	        System.out.print("수정할 생년월일(xxxx-xx-xx): ");
	        String newBirth_date = sc.next();
	        LocalDate newBirthDate = LocalDate.parse(newBirth_date);
	        
	        System.out.print("수정할 휴대전화: ");
	        String newPhone = sc.next();

		 System.out.println("!!!!!!!");
	        
	        UserInfoDTO userInfo = new UserInfoDTO(newPass, newName, newBirthDate, newPhone);

		 System.out.println("!!!!!!!");
			int result = userDao.updateUserInfo(userInfo);


			if(result>=1) {
				System.out.println("회원 정보를 수정하는데 성공하였습니다.");
			}else {
				System.out.println("회원 정보를 수정하는데 실패하였습니다. 다시 시도해 주세요!");
			}

	    }
	 //회원탈퇴
	 public static void deleteUserInfo(UserDTO userDTO){
		 	Scanner sc = new Scanner(System.in);
	        UserInfoDao userDao = new UserInfoDao();
//			System.out.print("삭제할id:");
//			int id = sc.nextInt();
			int result = userDao.deleteUserInfo(userDTO);
			if(result>=1) {
				System.out.println("회원 탈퇴하는데 성공하였습니다. 프로그램을 종료합니다.");
	            System.exit(0);
			} else {
				System.out.println("회원 탈퇴하는데 실패하였습니다. 다시 시도해 주세요!");
			}
		}
	

}

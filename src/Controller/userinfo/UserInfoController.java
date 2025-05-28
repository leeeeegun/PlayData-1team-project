package Controller.userinfo;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import dao.userinfo.UserInfoDao;
import dto.userinfo.UserInfoDTO;

public class UserInfoController {
	//마이페이지
	public static void myPage(String login_id, String password) {
		
        String loggedInMenu1 = "============== 마이페이지 ==============";
        String loggedInMenu2 = "1. 회원 정보 보기\n2.회원 정보 수정\n3.회원 정보 삭제\n0. 메인 화면으로 이동"
        								+"\n======================================\n메뉴 번호를 입력해주세요 :";
        

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println(loggedInMenu1);
            System.out.println(loggedInMenu2);
            int choice = sc.nextInt();
            switch (choice) {
                case 1: viewUserInfo(login_id, password); break;
                case 2: editUserInfo(); break;
                case 3: deleteUserInfo(); break;
                case 0: return;
                default:
                    System.out.println("잘못된 번호를 입력하셨습니다. 다시 입력해주세요.");
            }
        }
    }
	
	
	//회원정보 보기
	public static void viewUserInfo(String login_id, String password) {//로그인을 해야 정보를 볼 수 있어서 매개변수에 아이디와 패스워드를 넣었다
		UserInfoDao userDao = new UserInfoDao();
		
		ArrayList<UserInfoDTO> list = userDao.getUserInfo(login_id, password);
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
	 private static void editUserInfo() {
	        Scanner sc = new Scanner(System.in);
	        UserInfoDao userDao = new UserInfoDao();
	        System.out.println("수정할 회원님의 정보를 입력해주세요.");
	        System.out.print("고유번호: ");
	        int id = sc.nextInt();
	        System.out.print("비밀번호: ");
	        String newPass = sc.next();
	        System.out.print("성명: ");
	        String newName = sc.next();
	        System.out.print("생년월일(xxxx-xx-xx): ");
	        String newBirth_date = sc.next();

	        LocalDate newBirthDate = LocalDate.parse(newBirth_date);
	        
	        System.out.print("휴대전화: ");
	        String newPhone = sc.next();
	        
	        UserInfoDTO userInfo = new UserInfoDTO(id, newPass, newName, newBirthDate, newPhone);
			//메소드의 실행 결과를 이용해서 각각 다른 작업을 처리
			int result = userDao.updateUserInfo(userInfo);
			if(result>=1) {
				System.out.println("회원 정보를 수정하는데 성공하였습니다.");
			}else {
				System.out.println("회원 정보를 수정하는데 실패하였습니다. 다시 시도해 주세요!");
			}

	    }
	 //회원탈퇴
	 public static void deleteUserInfo(){
		 	Scanner sc = new Scanner(System.in);
	        UserInfoDao userDao = new UserInfoDao();
			System.out.print("삭제할id:");
			int id = sc.nextInt();
			int result = userDao.deleteUserInfo(id);
			if(result>=1) {
				System.out.println("회원 탈퇴하는데 성공하였습니다. 프로그램을 종료합니다.");
	            System.exit(0);
			} else {
				System.out.println("회원 탈퇴하는데 실패하였습니다. 다시 시도해 주세요!");
			}
		}
	

}

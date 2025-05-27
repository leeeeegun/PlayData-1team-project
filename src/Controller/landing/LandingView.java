package Controller.landing;

import Controller.lecture.LectureController;
import Controller.login.LoginView;
import dto.user.UserDTO;

import java.sql.SQLException;
import java.util.Scanner;

public class LandingView {

    public static void LandingNotLogin() throws SQLException {
        Scanner sc = new Scanner(System.in);
        LectureController lectureController = new LectureController();

        System.out.println("======================================");
        System.out.println();
        System.out.println("                 플레인프런              ");
        System.out.println();
        System.out.println();
        System.out.println(" 1. 로그인         ");
        System.out.println(" 2. 강의 둘러보기          ");
        System.out.println(" 3. 종료          ");
        System.out.println();
        System.out.println();
        System.out.print(" 선택 :           ");
        int choice = sc.nextInt();
        System.out.println();
        System.out.println();
        System.out.println("======================================");

        if(choice == 1){
            LoginView.login();
        } else if(choice == 2){
            lectureController.LectureCategory();
        } else{
            System.out.println("======================================");
            System.out.println("        이용해주셔서 감사합니다.       ");
            System.out.println("======================================");
            System.exit(0);
        }

    }

    public static void LandingLogin(UserDTO userDTO) throws SQLException {
        LectureController lectureController = new LectureController();
        Scanner sc = new Scanner(System.in);
        System.out.println("======================================");
        System.out.println();
        System.out.println("            플레인프런          ");
        System.out.println();
        System.out.println(" 포인트 : "+userDTO.getMoney()+"   등급 : "+userDTO.getGrade()+"    "+userDTO.getName()+"님");
        System.out.println();
        System.out.println();
        System.out.println(" 1. 로그아웃         ");
        System.out.println(" 2. 내 강의            ");
        System.out.println(" 3. 강의 둘러보기           ");
        System.out.println(" 4. 강의 올리기          ");
        System.out.println(" 5. 마이페이지          ");
        System.out.println(" 6. 포인트 충전          ");
        System.out.println(" 7. 나가기          ");
        System.out.println();
        System.out.print(" 선택 :           ");
        int choice = sc.nextInt();
        System.out.println();
        System.out.println();
        System.out.println("======================================");


        switch (choice){
            case 1:
                LandingNotLogin();
                break;
            case 2:

                break;
            case 3:
                lectureController.LectureCategory(userDTO);
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                System.out.println("======================================");
                System.out.println("        이용해주셔서 감사합니다.       ");
                System.out.println("======================================");
                System.exit(0);
                break;
        }

    }



}

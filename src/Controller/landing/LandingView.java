package Controller.landing;

import Controller.lecture.LectureController;
import Controller.login.LoginView;
import Controller.payment.PointController;
import Controller.qna.QnaController;
import Controller.user.UserController;
import Controller.userinfo.UserInfoController;
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
        System.out.println(" 0. 회원가입         ");
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
        } else if(choice == 0){
            LoginView.join();
        } else{
            System.out.println("======================================");
            System.out.println("        이용해주셔서 감사합니다.       ");
            System.out.println("======================================");
            System.exit(0);
        }

    }

    public static void LandingLogin(UserDTO userDTO) throws SQLException, InterruptedException {
        LectureController lectureController = new LectureController();
        Scanner sc = new Scanner(System.in);
        System.out.println("======================================");
        System.out.println();
        System.out.println("            플레인프런          ");
        System.out.println();
        System.out.println(" 포인트 : "+userDTO.getMoney()+"   등급 : "+userDTO.getGrade()+"    "+userDTO.getName()+"님");
        System.out.println(" id : "+userDTO.getId());
        System.out.println();
        System.out.println();
        System.out.println(" 1. 로그아웃         ");
        System.out.println(" 2. 내 강의            ");
        System.out.println(" 3. 강의 둘러보기           ");
        System.out.println(" 4. 강의 올리기          ");
        System.out.println(" 5. 마이페이지          ");
        System.out.println(" 6. 포인트 충전          ");
        System.out.println(" 7. 강의 추천          ");
        System.out.println(" 8. Q & A          ");
        System.out.println(" 9. 찜한 강의          ");
        System.out.println(" 10. 나가기          ");
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
                UserController.userLecture(userDTO);
                break;
            case 3:
                lectureController.LectureCategory(userDTO);
                break;
            case 4:
                break;
            case 5:
                UserInfoController.myPage(userDTO);
                break;
            case 6:
                PointController.PointAdd(userDTO);
                break;
            case 7:

                break;
            case 8:
                QnaController.QnaMain(userDTO);
                break;
            case 9:
                LectureController.favoriteLectures(userDTO);
                break;
            case 10:
                System.out.println("======================================");
                System.out.println("        이용해주셔서 감사합니다.       ");
                System.out.println("======================================");
                System.exit(0);
                break;

        }

    }



}

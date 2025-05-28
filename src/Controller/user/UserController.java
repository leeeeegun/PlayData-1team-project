package Controller.user;

import Controller.landing.LandingView;
import Controller.lecture.LectureController;
import Controller.rating.RatingView;
import dao.user.UserLecturesDao;
import dto.lecture.LectureDTO;
import dto.user.UserDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserController {

    public static void userLecture(UserDTO userDTO) throws SQLException, InterruptedException {
        Scanner sc = new Scanner(System.in);

        UserLecturesDao userLecturesDao = new UserLecturesDao();
        ArrayList<LectureDTO> lecturesByUserId = userLecturesDao.getLecturesByUserId(userDTO);

        System.out.println("=================================");
        System.out.println("             수강 목록              ");
        System.out.println();
        System.out.println();

        if (lecturesByUserId.isEmpty()) {
            System.out.println("             비어 있음              ");
            System.out.println();
            System.out.println();
            System.out.println("       확인 [엔터(Enter)]    ");
            System.out.println();
            System.out.println("==============================");
            String input = sc.nextLine();

            if (input.isEmpty()) {
                LandingView.LandingLogin(userDTO);
            } else {
                System.out.println("잘못된 입력입니다. 프로그램을 종료합니다.");
            }
        } else {
            for (LectureDTO lectureDTO : lecturesByUserId) {
                if(lectureDTO.isEnd()){
                    System.out.println("\uD83C\uDF89"+lectureDTO.getId() + ". " +
                            lectureDTO.getTitle() + " ");
                } else{
                    System.out.println(lectureDTO.getId() + ". " +
                            lectureDTO.getTitle() + " ");
                }

            }

        }
        System.out.println();
        System.out.println();
        System.out.println("    1. 수강하기 2.  평점등록   3. 뒤로가기");
        System.out.println();
        System.out.println();
        System.out.print(" 입력 : ");
        int input = sc.nextInt();
        System.out.println();
        if (input == 1) {
            System.out.println("=================================");
            System.out.println("        수강할 강의 번호 입력    ");
            System.out.println();
            System.out.print("번호 입력 : ");
            int num = sc.nextInt();
            System.out.println();
            System.out.println("=================================");

            LectureController.lectureWatch(userDTO, num);


        } else if(input == 2){
//            System.out.println("=================================");
//            System.out.println();
//            System.out.println("        평가할 강의 번호 입력          ");
//            System.out.println();
//            System.out.println();
//            System.out.println(" 번호 입력 : ");
//            int num = sc.nextInt();
            RatingView.insertRating(userDTO);
//            System.out.println();
//            System.out.println("=================================");
        } else if (input == 3) {
            LandingView.LandingLogin(userDTO);
        } else{
            System.out.println("=================================");
            System.out.println();
            System.out.println("잘못 입력하셨습니다.");
            System.out.println();
            System.out.println("=================================");

            userLecture(userDTO);
        }

        System.out.println("=================================");




    }



}

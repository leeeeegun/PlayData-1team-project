package Controller.lecture;

import Controller.landing.LandingView;
import dao.lecture.LectureDao;
import dto.lecture.LectureDTO;
import dto.user.UserDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;


public class LectureController {

    public void LectureCategory(UserDTO userDTO) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("======================================");
        System.out.println();
        System.out.println();
        System.out.println("0. 검색 하기");
        System.out.println("1. 프로그래밍");
        System.out.println("2. 데이터 분석");
        System.out.println("3. 디자인");
        System.out.println("4. 프로젝트 관리");
        System.out.println("5. IT 인프라 및 보안");
        System.out.println("6. 소프트 스킬 및 커뮤니케이션");
        System.out.println("7. 뒤로 가기");
        System.out.println();
        System.out.println();
        System.out.print(" 선택 :           ");
        System.out.println();
        System.out.println();
        int choice = sc.nextInt();
        System.out.println("======================================");

        switch (choice){
            case 0:
                break;
            case 1:
                getLectures("프로그래밍", userDTO);
                break;
            case 2:
                getLectures("데이터 분석", userDTO);
                break;
            case 3:
                getLectures("디자인", userDTO);
                break;
            case 4:
                getLectures("IT 인프라 및 보안", userDTO);
                break;
            case 5:
                getLectures("프로젝트 관리", userDTO);
                break;
            case 6:
                getLectures("소프트 스킬 및 커뮤니케이션", userDTO);
                break;
            case 7:
                if(userDTO.getIsLogin()){
                    LandingView.LandingLogin(userDTO);
                } else{
                    LandingView.LandingNotLogin();
                }
                break;
        }

    }

    public void LectureCategory() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("======================================");
        System.out.println();
        System.out.println();
        System.out.println("1. 프로그래밍");
        System.out.println("2. 데이터 분석");
        System.out.println("3. 디자인");
        System.out.println("4. 프로젝트 관리");
        System.out.println("5. IT 인프라 및 보안");
        System.out.println("6. 소프트 스킬 및 커뮤니케이션");
        System.out.println("7. 뒤로 가기");
        System.out.println();
        System.out.println();
        System.out.print(" 선택 :           ");
        int choice = sc.nextInt();
        System.out.println();
        System.out.println();
        System.out.println("======================================");

        switch (choice){
            case 1:
                getLectures("프로그래밍");
                break;
            case 2:
                getLectures("데이터 분석");
                break;
            case 3:
                getLectures("디자인");
                break;
            case 4:
                getLectures("IT 인프라 및 보안");
                break;
            case 5:
                getLectures("프로젝트 관리");
                break;
            case 6:
                getLectures("소프트 스킬 및 커뮤니케이션");
                break;
            case 7:
                LandingView.LandingNotLogin();
                break;
        }

    }



    public void getLectures(String category) throws SQLException {
        int i = 1;
        Scanner sc = new Scanner(System.in);
        LectureDao lectureDao = new LectureDao();
        ArrayList<LectureDTO> lecturesByCategory =
                lectureDao.getLecturesByCategory(category);
        System.out.println("======================================");
        System.out.println();
        System.out.println();
        for (LectureDTO lectureDTO : lecturesByCategory) {
            System.out.println(i+". " + lectureDTO.getTitle() + "  [ 평점 : "+ lectureDTO.getFavorite_count() + " ]");
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println("뒤로 가기 :  엔터 [ Enter ]");
        System.out.println();
        System.out.println("======================================");

        String input = sc.nextLine();

        if (input.isEmpty()) {
            LandingView.LandingNotLogin();
        } else {
            System.out.println("잘못된 입력입니다. 프로그램을 종료합니다.");
        }

    }
    public void getLectures(String category, UserDTO userDTO) throws SQLException {
        int i = 1;
        Scanner sc = new Scanner(System.in);
        LectureDao lectureDao = new LectureDao();
        ArrayList<LectureDTO> lecturesByCategory =
                lectureDao.getLecturesByCategory(category);
        System.out.println("======================================");
        System.out.println();
        System.out.println();
        for (LectureDTO lectureDTO : lecturesByCategory) {
            System.out.println(i+". " + lectureDTO.getTitle() +"   가격   " + lectureDTO.getPrice()+   "  [ 평점 : "+ lectureDTO.getFavorite_count() + " ]");
            System.out.println();
            i++;
        }
        System.out.println();
        System.out.print(" 구매 :           ");
        int choice = sc.nextInt();
        System.out.println();
        System.out.println();
        System.out.println("뒤로 가기 :  엔터 [ Enter ]");
        System.out.println();
        System.out.println("======================================");

        String input = sc.nextLine();

        if (input.isEmpty()) {
            LandingView.LandingLogin(userDTO);
        } else {
            System.out.println("잘못된 입력입니다. 프로그램을 종료합니다.");
        }

    }


}

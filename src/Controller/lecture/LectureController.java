package Controller.lecture;

import Controller.landing.LandingView;
import Controller.login.LoginView;
import Controller.user.UserController;
import dao.lecture.LectureDao;
import dao.user.UserLecturesDao;
import dto.lecture.LectureDTO;
import dto.lecture.LectureDurationDTO;
import dto.user.UserDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Controller.searching.SearchingController.searchingController;


public class LectureController {

    public void LectureCategory(UserDTO userDTO) throws SQLException, InterruptedException {
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
                searchingController(userDTO);
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
        int choice = sc.nextInt();
        System.out.println();
        System.out.println();
        System.out.println("======================================");

        switch (choice){
            case 0:
                searchingController();
                break;
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
    public void getLectures(String category, UserDTO userDTO) throws SQLException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        LectureDao lectureDao = new LectureDao();
        ArrayList<LectureDTO> lecturesByCategory =
                lectureDao.getLecturesByCategory(category);
        System.out.println("======================================");
        System.out.println();
        System.out.println();

        for (LectureDTO lectureDTO : lecturesByCategory) {
            System.out.println(lectureDTO.getId() + ". " + lectureDTO.getTitle() + "   가격   " + lectureDTO.getPrice() + "  [ 평점 : " + lectureDTO.getFavorite_count() + " ]");
            System.out.println();
        }

        System.out.println();
        System.out.print("       1. 구매        2. 찜하기        3. 뒤로가기           ");
        System.out.println();
        System.out.println();
        System.out.print(" 입력 : ");
        int choice = sc.nextInt();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("======================================");

        String input = sc.nextLine();

        if (choice == 3) {
            LandingView.LandingLogin(userDTO);
        } else if(choice == 2) {

            System.out.println();
            System.out.println();
            System.out.print("강의 번호 입력 : ");
            int num = sc.nextInt();
            System.out.println();
            int result = lectureDao.favoriteAdd(num, userDTO.getId());
            if(result != 1){
                System.out.println("등록 실패");
                LandingView.LandingLogin(userDTO);
            } else{
                System.out.println("======================================");
                System.out.println();
                System.out.println("찜하기 되었습니다.");
                System.out.println();
                System.out.println("======================================");

                LandingView.LandingLogin(userDTO);
            }

        } else if (choice == 1) {

        } else {
            System.out.println();
            System.out.println("======================================");
            System.out.println("잘못된 입력 입니다.");
            System.out.println("======================================");
            System.out.println();

            getLectures(category, userDTO);

        }

    }

    public static void favoriteLectures(UserDTO userDTO) throws SQLException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        LectureDao lectureDao = new LectureDao();
        List<LectureDTO> favoriteLecturesByUserId = lectureDao.getFavoriteLecturesByUserId(userDTO.getId());
        System.out.println("===================================");
        System.out.println("            찜한 강의 목록            ");
        System.out.println();
        if (favoriteLecturesByUserId.isEmpty()) {
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
            for (LectureDTO lectureDTO : favoriteLecturesByUserId) {
                System.out.println(lectureDTO.getId() + ". " + lectureDTO.getTitle() + "   가격 - " + lectureDTO.getPrice());
            }

            System.out.println();
            System.out.println();
            System.out.println("   1. 구매 하기     2. 찜하기 취소      3. 뒤로    ");
            System.out.println();
            System.out.println();
            System.out.print(" 입력 : ");
            int input = sc.nextInt();

            if (input == 1) {
                System.out.println("구매하기 구현");
            } else if (input == 2) {
                System.out.println();
                System.out.println();
                System.out.print("강의 번호 입력 : ");
                int num = sc.nextInt();
                boolean result = lectureDao.deleteFavoriteLecture(num, userDTO.getId());

                if (result) {
                    System.out.println("===================================");
                    System.out.println();
                    System.out.println("찜하기가 취소 되었습니다.");
                    System.out.println();
                    System.out.println("===================================");

                    favoriteLectures(userDTO);

                }

            } else if (input == 3) {
                LandingView.LandingLogin(userDTO);
            } else {
                System.out.println("잘못된 입력 입니다.");
                LandingView.LandingLogin(userDTO);
            }


            System.out.println("===================================");

        }

    }


    public static void lectureWatch(UserDTO userDTO, int lecId) throws SQLException, InterruptedException {
        UserLecturesDao userLecturesDao = new UserLecturesDao();
        LectureDao lectureDao = new LectureDao();
        LectureDurationDTO userLectureDuration = lectureDao.getUserLectureDuration(lecId, userDTO.getId());
        Scanner sc = new Scanner(System.in);

        System.out.println();
        System.out.println("강의 시청 시작하려면 엔터를 누르세요...");
        sc.nextLine();
        long startTime = System.currentTimeMillis();
        System.out.println();
        System.out.println("강의 시청 시작!");
        System.out.println();
        System.out.println(userLectureDuration.getDescription());

        System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
        System.out.println("▓                                    ▓");
        System.out.println("▓             🎥 강의  🎥             ▓");
        System.out.println("▓                                    ▓");
        System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");

        System.out.println();

        Thread animationThread = new Thread(() -> {
            int dotCount = 0;
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    dotCount = (dotCount % 3) + 1;
                    String dots = ".".repeat(dotCount);
                    System.out.print("\r강의 재생 중" + dots + "   ");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {

            }
        });

        System.out.println("강의 시청을 종료하려면 엔터를 누르세요...");
        System.out.println();
        System.out.println();

        animationThread.start();
        sc.nextLine();
        animationThread.interrupt();
        animationThread.join();
        long endTime = System.currentTimeMillis();
        long durationMillis = endTime - startTime;
        long durationSeconds = durationMillis / 1000;

        System.out.println();
        System.out.println();
        System.out.println("===========================================");
        System.out.println();
        System.out.println("\n강의 시청 종료!");
        System.out.println("\n" + durationSeconds +"초 동안 강의 시청");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("===========================================");
        System.out.println();
        System.out.println();
        System.out.println("                 확인 [Enter]         ");
        System.out.println();
        System.out.println();

        String input = sc.nextLine();

        if (input.isEmpty()) {
            lectureDao.updateUserLectureDuration(lecId, userDTO.getId(), durationSeconds);
            userLecturesDao.updateUserLectureDuration(userDTO.getId(), durationSeconds);

            UserController.userLecture(userDTO);
        } else {
            System.out.println("잘못된 입력입니다. 프로그램을 종료합니다.");
        }





    }






}

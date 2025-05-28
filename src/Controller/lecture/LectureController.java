package Controller.lecture;

import Controller.landing.LandingView;
import Controller.login.LoginView;
import Controller.payment.PaymentView;
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
            PaymentView.paymentSystem(userDTO);
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

                PaymentView.paymentSystem(userDTO);
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

        int userDuration = userLectureDuration.getUserDuration();
        int totalDuration = userLectureDuration.getTotalDuration();

        if (userDuration > 0) {
            System.out.println();
            System.out.println();
            System.out.println("     이어서 시청하시겠습니까?");
            System.out.println();
            System.out.println("     현재 시청 시간: " + userDuration + "초");
            System.out.println("     총 시청 길이: " + totalDuration + "초");
            System.out.println();
            System.out.println();
            System.out.println("     1. Yes      2. No");
            System.out.print("선택: ");
            int input = sc.nextInt();
            sc.nextLine();
            if (input != 1) {
                userDuration = 0;
            }
        }

        System.out.println();
        System.out.println("강의 시청을 시작하려면 [Enter] 키를 누르세요...");
        sc.nextLine();

        long startTime = System.currentTimeMillis();
        System.out.println("\n강의 시청 시작!\n");
        System.out.println(userLectureDuration.getDescription());

        System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
        System.out.println("▓                                    ▓");
        System.out.println("▓             🎥 강의  🎥             ▓");
        System.out.println("▓                                    ▓");
        System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n");
        System.out.println();

        final boolean[] autoEnded = {false};
        final int startUserDuration = userDuration;
        final long startTimeFinal = startTime;

        Thread animationThread = new Thread(() -> {
            int dotCount = 0;
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    long elapsedSeconds = (System.currentTimeMillis() - startTimeFinal) / 1000 + startUserDuration;
                    if (elapsedSeconds >= totalDuration) {
                        System.out.println();
                        System.out.print("\r강의가 끝났습니다! 🎉              \n");
                        System.out.println();
                        System.out.println("계속 하실려면 [Enter] ");
                        System.out.println();
                        autoEnded[0] = true;
                        break;
                    }
                    dotCount = (dotCount % 3) + 1;
                    String dots = ".".repeat(dotCount);
                    System.out.print("\r강의 재생 중" + dots + "   ");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ignored) {}
        });

        System.out.println();
        System.out.println("강의 시청을 종료하려면 [Enter] 키를 누르세요...\n");
        System.out.println();

        animationThread.start();

        while (true) {

            if (sc.hasNextLine()) {
                sc.nextLine();
                break;
            }
            long elapsedSeconds = (System.currentTimeMillis() - startTime) / 1000 + userDuration;
            if (elapsedSeconds >= totalDuration) {
                break;
            }
            Thread.sleep(200);
        }

        animationThread.interrupt();
        animationThread.join();

        long endTime = System.currentTimeMillis();
        long watchedNow = (endTime - startTime) / 1000;
        long newUserDuration = userDuration + watchedNow;

        if (newUserDuration > totalDuration) {
            newUserDuration = totalDuration;
        }

        System.out.println("\n\n===========================================");
        System.out.println();
        System.out.println();
        System.out.println("\n강의 시청 종료!");
        System.out.println();
        System.out.println("\n총 " + watchedNow + "초 동안 시청했습니다.");
        System.out.println();
        System.out.println("\n누적 시청 시간: " + newUserDuration + "초 / " + totalDuration + "초");
        System.out.println();
        System.out.println();
        System.out.println("\n===========================================\n");
        System.out.println("                 확인 [Enter]");
        sc.nextLine();

        if (newUserDuration >= totalDuration) {
            System.out.println();
            System.out.println("🎉 완강을 축하합니다! 기록을 완료 처리합니다.");
            System.out.println();
            lectureDao.markLectureCompleted(lecId, userDTO.getId(),totalDuration);
            userLecturesDao.markLectureCompleted(userDTO.getId(), lecId);
        } else {
            lectureDao.updateUserLectureDuration(lecId, userDTO.getId(), (int) newUserDuration);
            userLecturesDao.updateUserLectureDuration(userDTO.getId(), (int) newUserDuration);
        }



        if (autoEnded[0]) {
            System.out.println("✔ 강의를 끝까지 시청했습니다!");
        } else {
            System.out.println("⚠ 강의가 끝나기 전에 종료했습니다.");
        }

        UserController.userLecture(userDTO);
    }



    public static void myAuthorLectures(UserDTO userDTO) throws SQLException, InterruptedException {

        LectureDao lectureDao = new LectureDao();
        Scanner sc = new Scanner(System.in);
        List<LectureDTO> lecturesByUserId = lectureDao.getLecturesByUserId(userDTO.getId());

        System.out.println("===================================");
        System.out.println("           내가올린 강의 목록            ");
        System.out.println();
        if (lecturesByUserId.isEmpty()) {
            System.out.println("             비어 있음              ");

        } else {
            for (LectureDTO lectureDTO : lecturesByUserId) {
                System.out.println(lectureDTO.getId() + ". " + lectureDTO.getTitle() + "   가격 - " + lectureDTO.getPrice());
            }

            System.out.println();
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



        }

    }




}

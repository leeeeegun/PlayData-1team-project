package Controller.admin;

import Controller.landing.LandingView;
import dao.admin.AdminDAO;
import dao.qna.QnaDao;
import dto.admin.DashboardStats;
import dto.qna.QnaSummaryDTO;
import dto.user.UserDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminController {


    public static void dashboard() throws SQLException {
        AdminDAO dao = new AdminDAO();
        DashboardStats stats = dao.getDashboardStats();

        if (stats == null) {
            System.out.println("대시보드 정보를 불러오지 못했습니다.");
            return;
        }

        System.out.println("========================================");
        System.out.println("              📊 DASHBOARD              ");
        System.out.println("========================================");
        System.out.printf("👤 총 사용자 수     : %d명\n", stats.getUserCount());
        System.out.printf("🎓 업로드 강의 수  : %d개\n", stats.getLectureCount());
        System.out.printf("⏱️ 총 시청 시간    : %d분\n", stats.getTotalWatchTime() / 60);
        System.out.printf("🔐 오늘 로그인 수  : %d명\n", stats.getTodayLoginCount());
        System.out.printf("📮 미답변 Q&A 수   : %d개\n", stats.getUnansweredQnaCount());
        System.out.printf("💰 총 결제 금액    : %,d원\n", stats.getTotalSpent());
        System.out.println("========================================");


        LandingView.LandingAdmin();

    }

//    public static void QnaAnswer(){
//        Scanner sc = new Scanner(System.in);
//        QnaDao qnaDao = new QnaDao();
//        List<QnaSummaryDTO> qnaSummariesByUserId = new ArrayList<>();
//
//
//
//
//        System.out.println("====================================");
//        System.out.println();
//        System.out.println("               Q & A                ");
//        System.out.println();
//        System.out.println();
//        if (qnaSummariesByUserId.isEmpty()) {
//            System.out.println("             비어 있음              ");
//            System.out.println();
//            System.out.println();
//            System.out.println("       확인 [엔터(Enter)]    ");
//            System.out.println();
//            System.out.println("==============================");
//            String input = sc.nextLine();
//
//            if (input.isEmpty()) {
//                LandingView.LandingLogin(userDTO);
//            } else {
//                System.out.println("잘못된 입력입니다. 프로그램을 종료합니다.");
//            }
//        } else {
//            for (QnaSummaryDTO qnaSummaryDTO : qnaSummariesByUserId) {
//                System.out.println(qnaSummaryDTO.getId() + ". " +
//                        qnaSummaryDTO.getTitle());
//                System.out.println();
//            }
//
//            System.out.println();
//            System.out.println();
//            System.out.println("        1. 상세보기         2. 뒤로가기 ");
//            System.out.println();
//            System.out.println("====================================");
//            System.out.println();
//            System.out.println();
//            System.out.print(" 입력 : ");
//            int input = sc.nextInt();
//
//
//            if(input == 1){
//                System.out.print(" 번호 입력 : ");
//                int number = sc.nextInt();
//                System.out.println();
//                QnaGet(userDTO, number, num);
//
//            } else if (input == 2) {
//                QnaMain(userDTO);
//            } else{
//                LandingView.LandingLogin(userDTO);
//            }
//
//
//        }
//    }




}

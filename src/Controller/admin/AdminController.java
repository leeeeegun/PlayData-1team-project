package Controller.admin;

import Controller.landing.LandingView;
import dao.admin.AdminDAO;
import dto.admin.DashboardStats;
import dto.user.UserDTO;

import java.sql.SQLException;

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




}

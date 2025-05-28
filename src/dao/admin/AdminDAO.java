package dao.admin;

import dto.admin.DashboardStats;
import util.DBUtill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {

    public static DashboardStats getDashboardStats() {
        String sql = """
        SELECT
            (SELECT COUNT(*) FROM user) AS user_count,
            (SELECT COUNT(*) FROM lectures) AS lecture_count,
            (SELECT IFNULL(SUM(total_watch_time), 0) FROM user) AS total_watch_time,
            (SELECT IFNULL(SUM(login_count), 0) FROM userLogin WHERE DATE(last_login) = CURDATE()) AS today_login_count,
            (SELECT COUNT(*) FROM qna WHERE is_answered = 0) AS unanswered_qna_count,
            (SELECT IFNULL(SUM(money), 0) FROM user) AS total_spent
        """;

        try (
                Connection con = DBUtill.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            if (rs.next()) {
                return new DashboardStats(
                        rs.getInt("user_count"),
                        rs.getInt("lecture_count"),
                        rs.getLong("total_watch_time"),
                        rs.getInt("today_login_count"),
                        rs.getInt("unanswered_qna_count"),
                        rs.getInt("total_spent")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("대시보드 정보를 불러오지 못했습니다.");
        }

        return null;
    }






}

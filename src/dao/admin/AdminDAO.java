package dao.admin;

import dto.admin.DashboardStats;
import util.DBUtill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {

    public static DashboardStats getDashboardStats() {
        String sql = "SELECT " +
                "(SELECT COUNT(*) FROM user) AS user_count, " +
                "(SELECT COUNT(*) FROM lectures) AS lecture_count, " +
                "(SELECT IFNULL(SUM(total_watch_time),0) FROM user) AS total_watch_time, " +
                "(SELECT IFNULL(SUM(login_count),0) FROM user_login WHERE login_date = CURDATE()) AS today_login_count, " +
                "(SELECT COUNT(*) FROM qna WHERE is_answered = 0) AS unanswered_qna_count, " +
                "(SELECT IFNULL(SUM(amount),0) FROM payment) AS total_spent";

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = DBUtill.getConnection();
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

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
        } finally {
            DBUtill.close(rs, stmt, con);
        }
        return null;
    }





}

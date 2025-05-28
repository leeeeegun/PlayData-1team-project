package dao.qna;

import dto.qna.QnaStatisticsDTO;
import dto.qna.QnaSummaryDTO;
import util.DBUtill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QnaDao {

    public List<QnaSummaryDTO> getQnaSummariesByUserIdToAnswered(int userId) throws SQLException {
        List<QnaSummaryDTO> qnaList = new ArrayList<>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql1 = """
        SELECT 
            q.title, 
            q.is_answered, 
            qu.has_viewed_answer
        FROM 
            qna_user qu
        JOIN 
            qna q ON qu.qna_id = q.id
        WHERE 
            qu.user_id = ? AND q.is_answered = ?
    """;



        try {
            con = DBUtill.getConnection();
            stmt = con.prepareStatement(sql1);
            stmt.setInt(1, userId);
            stmt.setInt(2, 1);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String title = rs.getString("title");
                boolean isAnswered = rs.getBoolean("is_answered");
                boolean hasViewed = rs.getBoolean("has_viewed_answer");
                String  content = rs.getString("content");
                String answer = rs.getString("answer");

                QnaSummaryDTO dto = new QnaSummaryDTO(title, isAnswered, hasViewed, content, answer);
                qnaList.add(dto);
            }

        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) {}
            if (stmt != null) try { stmt.close(); } catch (SQLException e) {}
            if (con != null) try { con.close(); } catch (SQLException e) {}
        }

        return qnaList;
    }

    public List<QnaSummaryDTO> getQnaSummariesByUserIdToNotAnswered(int userId) throws SQLException {
        List<QnaSummaryDTO> qnaList = new ArrayList<>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;



        String sql = """
        SELECT 
            q.title, 
            q.is_answered, 
            qu.has_viewed_answer
        FROM 
            qna_user qu
        JOIN 
            qna q ON qu.qna_id = q.id
        WHERE 
            qu.user_id = ? AND q.is_answered = ?
    """;



        try {
            con = DBUtill.getConnection();
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.setInt(1, 0);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String title = rs.getString("title");
                boolean isAnswered = rs.getBoolean("is_answered");
                boolean hasViewed = rs.getBoolean("has_viewed_answer");
                String  content = rs.getString("content");

                QnaSummaryDTO dto = new QnaSummaryDTO(title, isAnswered, hasViewed, content);
                qnaList.add(dto);
            }
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) {}
            if (stmt != null) try { stmt.close(); } catch (SQLException e) {}
            if (con != null) try { con.close(); } catch (SQLException e) {}
        }

        return qnaList;
    }


    public List<QnaSummaryDTO> getQnaSummariesByUserId(int userId) throws SQLException {
        List<QnaSummaryDTO> qnaList = new ArrayList<>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql1 = """
        SELECT 
            q.title, 
            q.is_answered, 
            qu.has_viewed_answer
        FROM 
            qna_user qu
        JOIN 
            qna q ON qu.qna_id = q.id
        WHERE 
            qu.user_id = ? AND q.is_answered = ?
    """;

        String sql2 = """
        SELECT 
            q.title, 
            q.is_answered, 
            qu.has_viewed_answer
        FROM 
            qna_user qu
        JOIN 
            qna q ON qu.qna_id = q.id
        WHERE 
            qu.user_id = ? AND q.is_answered = ?
    """;

        String sql3 = """
        SELECT 
            q.title, 
            q.is_answered, 
            qu.has_viewed_answer
        FROM 
            qna_user qu
        JOIN 
            qna q ON qu.qna_id = q.id
        WHERE 
            qu.user_id = ? AND q.is_answered = ? AND qu.has_viewed_answer =?
    """;

        try {
            con = DBUtill.getConnection();
            stmt = con.prepareStatement(sql1);
            stmt.setInt(1, userId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String title = rs.getString("title");
                boolean isAnswered = rs.getBoolean("is_answered");
                boolean hasViewed = rs.getBoolean("has_viewed_answer");

                QnaSummaryDTO dto = new QnaSummaryDTO(title, isAnswered, hasViewed);
                qnaList.add(dto);
            }
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) {}
            if (stmt != null) try { stmt.close(); } catch (SQLException e) {}
            if (con != null) try { con.close(); } catch (SQLException e) {}
        }

        return qnaList;
    }


    public QnaStatisticsDTO getQnaStatisticsByUserId(int userId) throws SQLException {
        QnaStatisticsDTO stats = new QnaStatisticsDTO();

        String sql = """
        SELECT 
            SUM(CASE WHEN q.is_answered = true THEN 1 ELSE 0 END) AS answered_count,
            SUM(CASE WHEN q.is_answered = false THEN 1 ELSE 0 END) AS unanswered_count,
            SUM(CASE WHEN q.is_answered = true AND qu.has_viewed_answer = false THEN 1 ELSE 0 END) AS new_answer_count
        FROM 
            qna_user qu
        JOIN 
            qna q ON qu.qna_id = q.id
        WHERE 
            qu.user_id = ?
    """;

        try (
                Connection con = DBUtill.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)
        ) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    stats.setAnsweredCount(rs.getInt("answered_count"));
                    stats.setUnansweredCount(rs.getInt("unanswered_count"));
                    stats.setNewAnswerCount(rs.getInt("new_answer_count"));
                    stats.setTotal(stats.getAnsweredCount() + stats.getUnansweredCount());
                }
            }
        }

        return stats;
    }


    





}

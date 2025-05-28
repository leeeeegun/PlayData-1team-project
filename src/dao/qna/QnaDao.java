package dao.qna;

import dto.qna.QnaStatisticsDTO;
import dto.qna.QnaSummaryDTO;
import util.DBUtill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QnaDao {

    public List<QnaSummaryDTO> getQnaSummariesByUserIdToAnswered(int userId) throws SQLException {
        List<QnaSummaryDTO> qnaList = new ArrayList<>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = """
        SELECT 
            q.id,
            q.title, 
            q.is_answered, 
            qu.has_viewed_answer,
            q.content,
            q.answer
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
            stmt.setInt(2, 1);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                boolean isAnswered = rs.getBoolean("is_answered");
                boolean hasViewed = rs.getBoolean("has_viewed_answer");
                String  content = rs.getString("content");
                String answer = rs.getString("answer");

                QnaSummaryDTO dto = new QnaSummaryDTO(id,title, isAnswered, hasViewed, content, answer);
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
            q.id,
            q.title, 
            q.is_answered, 
            qu.has_viewed_answer,
            q.content
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
            stmt.setInt(2, 0);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                boolean isAnswered = rs.getBoolean("is_answered");
                boolean hasViewed = rs.getBoolean("has_viewed_answer");
                String  content = rs.getString("content");

                QnaSummaryDTO dto = new QnaSummaryDTO(id,title, isAnswered, hasViewed, content);
                qnaList.add(dto);
            }
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) {}
            if (stmt != null) try { stmt.close(); } catch (SQLException e) {}
            if (con != null) try { con.close(); } catch (SQLException e) {}
        }

        return qnaList;
    }


    public List<QnaSummaryDTO> getQnaSummariesByUserIdNew(int userId) throws SQLException {
        List<QnaSummaryDTO> qnaList = new ArrayList<>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;


        String sql = """
        SELECT 
            q.id,
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
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.setInt(2, 1);
            stmt.setInt(3, 0);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                boolean isAnswered = rs.getBoolean("is_answered");
                boolean hasViewed = rs.getBoolean("has_viewed_answer");

                QnaSummaryDTO dto = new QnaSummaryDTO(id,title, isAnswered, hasViewed);
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


    public QnaSummaryDTO getQnaSummaryById(int id) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        QnaSummaryDTO dto = null;

        String sql = """
        SELECT 
            id,
            title,
            content,
            answer,
            is_answered
        FROM 
            qna
        WHERE 
            id = ?
    """;

        try {
            con = DBUtill.getConnection();
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int qnaId = rs.getInt("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String answer = rs.getString("answer");
                boolean isAnswered = rs.getBoolean("is_answered");

                // hasViewedAnswer는 이 쿼리에서 나오지 않으므로 false로 기본 설정
                dto = new QnaSummaryDTO(qnaId, title, isAnswered, false, content, answer);
            }
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) {}
            if (stmt != null) try { stmt.close(); } catch (SQLException e) {}
            if (con != null) try { con.close(); } catch (SQLException e) {}
        }

        return dto;
    }


    public boolean insertQna(String title, String content, int authorId) throws SQLException {
        Connection con = null;
        PreparedStatement stmtQna = null;
        PreparedStatement stmtQnaUser = null;
        ResultSet generatedKeys = null;

        String insertQnaSql = """
        INSERT INTO qna (title, content, author_id)
        VALUES (?, ?, ?)
    """;

        String insertQnaUserSql = """
        INSERT INTO qna_user (user_id, qna_id, has_viewed_answer)
        VALUES (?, ?, false)
    """;

        try {
            con = DBUtill.getConnection();
            con.setAutoCommit(false);

            stmtQna = con.prepareStatement(insertQnaSql, Statement.RETURN_GENERATED_KEYS);
            stmtQna.setString(1, title);
            stmtQna.setString(2, content);
            stmtQna.setInt(3, authorId);
            int affectedRows = stmtQna.executeUpdate();

            if (affectedRows == 0) {
                con.rollback();
                return false;
            }

            generatedKeys = stmtQna.getGeneratedKeys();
            if (!generatedKeys.next()) {
                con.rollback();
                return false;
            }

            int qnaId = generatedKeys.getInt(1);

            stmtQnaUser = con.prepareStatement(insertQnaUserSql);
            stmtQnaUser.setInt(1, authorId);
            stmtQnaUser.setInt(2, qnaId);
            stmtQnaUser.executeUpdate();

            con.commit();
            return true;

        } catch (SQLException e) {
            if (con != null) con.rollback();
            throw e;
        } finally {
            if (generatedKeys != null) try { generatedKeys.close(); } catch (SQLException e) {}
            if (stmtQnaUser != null) try { stmtQnaUser.close(); } catch (SQLException e) {}
            if (stmtQna != null) try { stmtQna.close(); } catch (SQLException e) {}
            if (con != null) try { con.setAutoCommit(true); con.close(); } catch (SQLException e) {}
        }
    }








}

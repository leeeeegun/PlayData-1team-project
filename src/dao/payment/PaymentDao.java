package dao.payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.payment.PaymentDTO;
import util.DBUtill;

public class PaymentDao {

	public int userMoney(int id) {
		String sql = "select money from user where id = ?";
		Connection con = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		int money = 0;
		try {
			con = DBUtill.getConnection();
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, id);
			rs = ptmt.executeQuery();
            if (rs.next()) {
                money =  rs.getInt("money");
            } 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				DBUtill.close(rs, ptmt, con);
		}
		return money;
	}
	public ArrayList<PaymentDTO> getLecturesPrice(int id) {
		String sql = "select title, price from lectures where id = ?";
		Connection con = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		ArrayList<PaymentDTO> paymentlist = new ArrayList<PaymentDTO>();
		try {
			con = DBUtill.getConnection();
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, id);
			rs = ptmt.executeQuery();
			while(rs.next()) {
				PaymentDTO payment = new PaymentDTO(rs.getString("title"), rs.getInt("price"));
				paymentlist.add(payment);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				DBUtill.close(rs, ptmt, con);
		}
		return paymentlist;
	}

	public Integer deductUserPointsAndGetBalanceAndInsertLecture(int userId, int amount, int lectureId) throws SQLException {
		String updateSql = """
        UPDATE user
        SET money = money - ?
        WHERE id = ? AND money >= ?
    """;

		String selectSql = "SELECT money FROM user WHERE id = ?";

		String insertLectureSql = """
        INSERT INTO user_lectures (user_id, lecture_id, purchased_at, total_duration, user_duration)
        VALUES (?, ?, NOW(), 20, 0)
    """;

		Connection con = null;
		PreparedStatement updateStmt = null;
		PreparedStatement selectStmt = null;
		PreparedStatement insertLectureStmt = null;

		try {
			con = DBUtill.getConnection();
			// 트랜잭션 시작
			con.setAutoCommit(false);

			// 1. 유저 포인트 차감
			updateStmt = con.prepareStatement(updateSql);
			updateStmt.setInt(1, amount);
			updateStmt.setInt(2, userId);
			updateStmt.setInt(3, amount);
			int rowsAffected = updateStmt.executeUpdate();

			if (rowsAffected == 0) {
				con.rollback();
				return null;
			}

			insertLectureStmt = con.prepareStatement(insertLectureSql);
			insertLectureStmt.setInt(1, userId);
			insertLectureStmt.setInt(2, lectureId);
			insertLectureStmt.executeUpdate();

			selectStmt = con.prepareStatement(selectSql);
			selectStmt.setInt(1, userId);
			ResultSet rs = selectStmt.executeQuery();
			Integer balance = null;
			if (rs.next()) {
				balance = rs.getInt("money");
			}

			con.commit();

			return balance;

		} catch (SQLException e) {
			if (con != null) {
				con.rollback();
			}
			e.printStackTrace();
			return null;

		} finally {
			if (updateStmt != null) updateStmt.close();
			if (insertLectureStmt != null) insertLectureStmt.close();
			if (selectStmt != null) selectStmt.close();
			if (con != null) {
				con.setAutoCommit(true);
				con.close();
			}
		}
	}




}

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

	public Integer deductUserPointsAndGetBalance(int userId, int amount) throws SQLException {
		String updateSql = """
        UPDATE user
        SET money = money - ?
        WHERE id = ? AND money >= ?
    """;

		String selectSql = "SELECT money FROM user WHERE id = ?";

		try (
				Connection con = DBUtill.getConnection();
				PreparedStatement updateStmt = con.prepareStatement(updateSql);
				PreparedStatement selectStmt = con.prepareStatement(selectSql)
		) {
			updateStmt.setInt(1, amount);
			updateStmt.setInt(2, userId);
			updateStmt.setInt(3, amount);
			int rowsAffected = updateStmt.executeUpdate();

			if (rowsAffected > 0) {
				selectStmt.setInt(1, userId);
				try (ResultSet rs = selectStmt.executeQuery()) {
					if (rs.next()) {
						return rs.getInt("money");
					}
				}
			}

			return null;
		}
	}



}

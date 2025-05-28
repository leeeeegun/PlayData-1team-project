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
	
}

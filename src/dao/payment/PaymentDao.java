package dao.payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}

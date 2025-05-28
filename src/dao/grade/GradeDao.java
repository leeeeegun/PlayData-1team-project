package dao.grade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dto.user.UserDTO;
import util.DBUtill;

public class GradeDao {
	public int Grade(UserDTO userDTO, String newGrade) {
		String sql = "update user set grade = ? where id = ?";

		Connection con = null;
		PreparedStatement ptmt = null;
		int result = 0;

		try {
			con = DBUtill.getConnection();
			ptmt = con.prepareStatement(sql);
			ptmt.setString(1, newGrade);
			ptmt.setInt(2, userDTO.getId());

			result = ptmt.executeUpdate();
			userDTO.setGrade(newGrade);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtill.close(null, ptmt, con);
		}
		return result;
	}
}

package dao.uploading;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.uploading.UploadingDTO;
import dto.user.UserDTO;
import util.DBUtill;

public class UploadingDAO {
	public int uploadInsert(UploadingDTO upload, UserDTO userdto) {
		String sql = "INSERT INTO lectures (title, description, instructor_id, price, category, created_at, updated_at) VALUES (?, ?, ?, ?, ?, sysdate(), sysdate())";

		Connection con = null;
		PreparedStatement stmt = null;
		int result = 0;

		try {
			con = DBUtill.getConnection();
			stmt = con.prepareStatement(sql);

			stmt.setString(1, upload.getTitle());
			stmt.setString(2, upload.getDescription());
			stmt.setInt(3, userdto.getId());
			stmt.setInt(4, upload.getPrice());
			stmt.setString(5, upload.getCategory());

			result = stmt.executeUpdate();
			System.out.println(result + "강의 등록 성공!");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtill.close(null, stmt, con);
		}
		return result;
	}
}




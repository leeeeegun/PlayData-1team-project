package dao.searching;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import dto.searching.SearchingDTO;
import util.DBUtill;


public class SearchingDao {
	public ArrayList<SearchingDTO> searchingTitle(String title){
		String sql = "SELECT * FROM lectures WHERE title IS NOT NULL AND TRIM(title) != '' AND title LIKE CONCAT('%', ?, '%')";
		Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
		ArrayList<SearchingDTO> searchlist = new ArrayList<SearchingDTO>();
		
		try {
			con = DBUtill.getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setString(1, title);
			rs = stmt.executeQuery();
			while(rs.next()) {
				SearchingDTO search = new SearchingDTO(rs.getInt(1),rs.getString(2),rs.getString(3),
						                               rs.getInt(4),rs.getInt(5),rs.getFloat(6),
						                               rs.getString(7),rs.getInt(8),rs.getDate(9),rs.getDate(10));
				searchlist.add(search);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtill.close(rs, stmt, con);
		}
		return searchlist;
		
		
	}
	
}

	
	


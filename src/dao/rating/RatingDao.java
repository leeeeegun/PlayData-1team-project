package dao.rating;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.rating.RatingDTO;
import util.DBUtill;

public class RatingDao {
	//평점을 넣는건데...
	public int ratingInsert (int lecture_id, float rating) {
		String sql = "insert into ratings (lecture_id, rating) values(?,?)";
		Connection con = null;
		PreparedStatement pmpt = null;
		int result = 0;
		try {
			con = DBUtill.getConnection();
			pmpt = con.prepareStatement(sql);
			pmpt.setInt(1, lecture_id);
			pmpt.setFloat(2, rating);
			result = pmpt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtill.close(null, pmpt, con);
		}
		return result;
	}
	public int ratingUpdate(int id, float rating) {
		Connection con = null;
		PreparedStatement ptmt = null;
		int result = 0;
		String sql = "update ratings set rating=? where id =? ";
		
		try {
			con = DBUtill.getConnection();
			ptmt = con.prepareStatement(sql);
			ptmt.setFloat(1, rating);
			ptmt.setInt(2, id);
			result = ptmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtill.close(null, ptmt, con);
		}
		return result;
	}
	public int ratingDelet(int id) {
		Connection con = null;
		PreparedStatement ptmt = null;
		int result = 0;
		String sql = "delete from ratings where id =? ";
		
		try {
			con = DBUtill.getConnection();
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, id);
			result = ptmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtill.close(null, ptmt, con);
		}
		return result;
	}
	public ArrayList<RatingDTO> findByAddrMenu(int lecture_id) {
		String sql = "select lecture_id, rating from ratings where lecture_id = ?";
		Connection con = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		ArrayList<RatingDTO> ratinglist = new ArrayList<RatingDTO>();
		try {
			con = DBUtill.getConnection();
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, lecture_id);
			rs = ptmt.executeQuery();
			while(rs.next()) {
				RatingDTO rating = new RatingDTO(rs.getInt("lecture_id"),rs.getFloat("rating"));
				ratinglist.add(rating);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtill.close(rs, ptmt, con);
		}
		return ratinglist;
		
	}
	
}

package dao.userinfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.user.UserDTO;
import dto.userinfo.UserInfoDTO;
import util.DBUtill;
import java.sql.Date;

public class UserInfoDao {
//	public UserInfoDao() {
//	}
	// 회원 정보 조회
    public ArrayList<UserInfoDTO> getUserInfo(UserDTO userDTO) {
    	Connection con = null;
        PreparedStatement ptmt = null;
        ResultSet rs = null;
        String sql = "select login_id, name, birth_date, phone, money, grade,"+
        											"total_watch_time from user where id = ?";
        ArrayList<UserInfoDTO> userInfolist = new ArrayList<UserInfoDTO>();
        try {
        	con = DBUtill.getConnection();
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, userDTO.getId());
			rs = ptmt.executeQuery();
            if (rs.next()) {
            	UserInfoDTO userInfo = new UserInfoDTO(rs.getString(1),
						 rs.getString(2), Date.valueOf(rs.getDate(3).toLocalDate()).toLocalDate(),
						 rs.getString(4), 
						rs.getInt(5),rs.getString(6),rs.getInt(7));
            	userInfolist.add(userInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	DBUtill.close(rs, ptmt, con);
        }
        return userInfolist;
    }
    //회원정보수정
    public int updateUserInfo(UserInfoDTO userDTO) { //UserInfoDTO userInfo

		System.out.println("디비 옴");
    	String sql = "update user set password=?, name=?, birth_date=?, phone=?"
    											+ " where id =? ";
    	UserInfoDTO userInfo = new UserInfoDTO();
		Connection con = null;
		PreparedStatement ptmt = null;
		int result = 0;
		try {
			con = DBUtill.getConnection();
			ptmt = con.prepareStatement(sql);
			ptmt.setString(1, userInfo.getPassword());
			ptmt.setString(2, userInfo.getName());
			ptmt.setDate(3, Date.valueOf(userInfo.getBirth_date()));
			ptmt.setString(4, userInfo.getPhone());
			ptmt.setInt(5, userDTO.getId());
			result = ptmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 자원반납
			DBUtill.close(null, ptmt, con);
		}
		return result;
	}
    public int deleteUserInfo(UserDTO userDTO) { 
		Connection con = null;
		PreparedStatement ptmt =  null;
		int result = 0;
		String sql = "delete from user where id = ?";
		
		try {
			con = DBUtill.getConnection();
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, userDTO.getId());
			result = ptmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtill.close(null, ptmt, con);
		}
		return result;
    }
}

package dao.login;

import dto.user.UserDTO;
import util.DBUtill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {

    public UserDTO Login(String id, String pass) throws SQLException {
        String sql = "SELECT * FROM user WHERE login_id = ? AND password = ?";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = DBUtill.getConnection();
            stmt = con.prepareStatement(sql);
            stmt.setString(1, id);
            stmt.setString(2, pass);
            rs = stmt.executeQuery();
            if (rs.next()) {

                UserDTO userDTO = new UserDTO(rs.getString("name"), rs.getString("money"),
                        rs.getString("grade"));

                return userDTO;  // 로그인 성공
            } else {
                return null; // 아이디 또는 비밀호 불일치
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null; // 예외 발생 시 반환
        } finally {
            DBUtill.close(rs, stmt, con);
        }
    }
}


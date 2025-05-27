package dao.login;

import util.DBUtill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {

    public String Login(String id, String pass) throws SQLException {
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
                String name =  rs.getString("name");
                return name;  // 로그인 성공
            } else {
                return "no"; // 아이디 또는 비밀호 불일치
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "p"; // 예외 발생 시 반환
        } finally {
            DBUtill.close(rs, stmt, con);
        }
    }
}


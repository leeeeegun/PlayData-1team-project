package dao.login;

import dto.login.JoinDTO;
import dto.user.UserDTO;
import util.DBUtill;

import java.sql.*;

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

                UserDTO userDTO = new UserDTO(rs.getInt("id"),rs.getString("name"), rs.getInt("money"),
                        rs.getString("grade"));

                return userDTO;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtill.close(rs, stmt, con);
        }
    }

    public int joinUser(JoinDTO user) {
        String sql = "INSERT INTO user (name, login_id, password, birth_date, phone) VALUES (?, ?, ?, ?, ?)";
        Connection con = null;
        PreparedStatement stmt = null;
        int result = 0;

        try {
            con = DBUtill.getConnection();
            stmt = con.prepareStatement(sql);

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getLoginId());
            stmt.setString(3, user.getPassword());
            stmt.setDate(4, Date.valueOf(user.getBirthDate()));
            stmt.setString(5, user.getPhone());

            result = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtill.close(null, stmt, con);
        }

        return result;
    }

}


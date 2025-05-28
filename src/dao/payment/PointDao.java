package dao.payment;

import dto.user.UserDTO;
import util.DBUtill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PointDao {
    public int PointAdd(UserDTO userDTO, int account) {
        Connection con = null;
        PreparedStatement ptmt = null;
        int result = 0;
        String sql = "update user set money=? where id =? ";

        try {
            con = DBUtill.getConnection();
            ptmt = con.prepareStatement(sql);
            ptmt.setInt(1, userDTO.getMoney()+account);
            ptmt.setInt(2, userDTO.getId());
            result = ptmt.executeUpdate();
            userDTO.setMoney(userDTO.getMoney()+account);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtill.close(null, ptmt, con);
        }
        return result;
    }




}

package dao.user;

import dto.lecture.LectureDTO;
import dto.searching.SearchingDTO;
import dto.user.UserDTO;
import util.DBUtill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserLectures {
    public ArrayList<LectureDTO> getLecturesByUserId(UserDTO userDTO){
        String sql = "SELECT l.*" +
                "FROM lectures l" +
                "JOIN user_lectures ul ON l.id = ul.lecture_id" +
                "WHERE ul.user_id = ?;";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<LectureDTO> lectureDTOS = new ArrayList<>();

        try {
            con = DBUtill.getConnection();
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, userDTO.getId());
            rs = stmt.executeQuery();
            while(rs.next()) {
                LectureDTO lectureDTO = new LectureDTO(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getInt("price"),
                        rs.getString("description"),
                        rs.getInt("favorite_count")
                );
                lectureDTOS.add(lectureDTO);
            }
            return lectureDTOS;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtill.close(rs, stmt, con);
        }


    }

}

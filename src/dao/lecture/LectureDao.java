package dao.lecture;

import dto.lecture.LectureDTO;
import dto.user.UserDTO;
import util.DBUtill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LectureDao {

    public ArrayList<LectureDTO> getLecturesByCategory(String category) throws SQLException {
        String sql = "SELECT * FROM lectures WHERE category = ?";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<LectureDTO> lectureDTOS = new ArrayList<>();

        try {
            con = DBUtill.getConnection();
            stmt = con.prepareStatement(sql);
            stmt.setString(1, category);
            rs = stmt.executeQuery();

            while (rs.next()) {
                LectureDTO lectureDTO = new LectureDTO(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getInt("price"),
                        rs.getString("description"),
                        rs.getInt("favorite_count")
                );
                lectureDTOS.add(lectureDTO);
            }
            return lectureDTOS; // 결과가 없으면 빈 리스트 반환
        } catch (SQLException e) {
            e.printStackTrace();
            return null; // 예외 발생 시 null 반환 (필요 시 커스텀 예외로 변경 가능)
        } finally {
            DBUtill.close(rs, stmt, con);
        }
    }



}

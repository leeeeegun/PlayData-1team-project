package dao.user;

import dto.lecture.LectureDTO;
import dto.user.UserDTO;
import util.DBUtill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserLecturesDao {
    public ArrayList<LectureDTO> getLecturesByUserId(UserDTO userDTO){
        String sql = "SELECT l.*, ul.is_completed " +
                "FROM lectures l " +
                "JOIN user_lectures ul ON l.id = ul.lecture_id " +
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
                        rs.getBoolean("is_completed"),
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

    public boolean deleteFavoriteLecture(int userId, int lectureId) throws SQLException {
        String sql = "DELETE FROM user_favorites_lectures WHERE user_id = ? AND lecture_id = ?";
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = DBUtill.getConnection();
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.setInt(2, lectureId);

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtill.close(null, stmt, con);
        }
    }

    public boolean updateUserLectureDuration(int userId, long durationSeconds) throws SQLException {
        String sql = "UPDATE user SET total_watch_time = total_watch_time + ? WHERE id = ? ";
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = DBUtill.getConnection();
            stmt = con.prepareStatement(sql);

            stmt.setLong(1, durationSeconds);
            stmt.setInt(2, userId);

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtill.close(null, stmt, con);
        }
    }
    public boolean markLectureCompleted(int userId, long durationSeconds) throws SQLException {
        String sql = "UPDATE user SET total_watch_time = total_watch_time + ? WHERE id = ? ";
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = DBUtill.getConnection();
            stmt = con.prepareStatement(sql);

            stmt.setLong(1, durationSeconds);
            stmt.setInt(2, userId);

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtill.close(null, stmt, con);
        }
    }


}

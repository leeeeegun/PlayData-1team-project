package dao.lecture;

import dto.lecture.LectureDTO;
import dto.lecture.LectureDurationDTO;
import dto.user.UserDTO;
import util.DBUtill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public int favoriteAdd(int lecID, int userId){
        String sql = "INSERT INTO user_favorites_lectures (user_id, lecture_id) VALUES (?, ?)";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int result = 0;

        try {
            con = DBUtill.getConnection();
            stmt = con.prepareStatement(sql);

            stmt.setInt(1, userId);
            stmt.setInt(2, lecID);


            result = stmt.executeUpdate();

            return result;
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtill.close(null, stmt, con);
        }
        return result;
    }

    public List<LectureDTO> getFavoriteLecturesByUserId(int userId) throws SQLException {
        List<LectureDTO> favorites = new ArrayList<>();
        String sql = "SELECT l.* FROM lectures l " +
                "JOIN user_favorites_lectures uf ON l.id = uf.lecture_id " +
                "WHERE uf.user_id = ?";

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = DBUtill.getConnection();
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, userId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                LectureDTO lectureDTO = new LectureDTO(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getInt("price"),
                        rs.getString("description"),
                        rs.getInt("favorite_count")
                );
                favorites.add(lectureDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtill.close(rs, stmt, con);
        }

        return favorites;
    }



    public boolean deleteFavoriteLecture(int lectureId, int userId) throws SQLException {
        String sql = "DELETE FROM user_favorites_lectures WHERE user_id = ? AND lecture_id = ?";
        Connection con = null;
        PreparedStatement stmt = null;

        System.out.println("!!!!!!");
        System.out.println(userId + "  " + lectureId);

        try {
            con = DBUtill.getConnection();
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.setInt(2, lectureId);

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0; // 하나 이상 삭제됐으면 true
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtill.close(null, stmt, con);
        }
    }

    public LectureDurationDTO getUserLectureDuration(int lectureId, int userId) throws SQLException {
        String sql = "SELECT ul.user_duration, ul.total_duration, l.description " +
                "FROM user_lectures ul " +
                "JOIN lectures l ON ul.lecture_id = l.id " +
                "WHERE ul.user_id = ? AND ul.lecture_id = ?";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = DBUtill.getConnection();
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.setInt(2, lectureId);

            rs = stmt.executeQuery();

            if (rs.next()) {
                int userDuration = rs.getInt("user_duration");
                int totalDuration = rs.getInt("total_duration");
                String description = rs.getString("description");

                return new LectureDurationDTO(userDuration, totalDuration, description);
            } else {
                return null;  // 데이터 없으면 null 반환
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtill.close(rs, stmt, con);
        }
    }


    public boolean updateUserLectureDuration(int lectureId,int userId, long durationSeconds) throws SQLException {
        String sql = "UPDATE user_lectures SET user_duration = user_duration + ? WHERE user_id = ? AND lecture_id = ?";
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = DBUtill.getConnection();
            stmt = con.prepareStatement(sql);

            stmt.setLong(1, durationSeconds);
            stmt.setInt(2, userId);
            stmt.setInt(3, lectureId);

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;  // 업데이트 성공 시 true 반환
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtill.close(null, stmt, con);
        }
    }

    public List<LectureDTO> getLecturesByUserId(int userId) {
        List<LectureDTO> lectures = new ArrayList<>();
        String sql = "SELECT * FROM lectures WHERE instructor_id = ?";

        try (Connection con = DBUtill.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                LectureDTO lecture = new LectureDTO();
                lecture.setId(rs.getInt("id"));
                lecture.setTitle(rs.getString("title"));
                lecture.setDescription(rs.getString("description"));
                lecture.setPrice(rs.getInt("price"));
                lectures.add(lecture);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lectures;
    }








}

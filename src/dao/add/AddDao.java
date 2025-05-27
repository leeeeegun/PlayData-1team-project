package dao.add;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import com.mysql.cj.x.protobuf.MysqlxCrud.Update;

import dto.add.AddDTO;
import util.DBUtill;

public class AddDao {
	
	String[] title;
	String[] content;
	Random random;
	
	public AddDao() {
		random = new Random();
	}
	public AddDTO getOneRandomAdd() {
		String randomTitle = title[random.nextInt(title.length)];
		String randomContent = content[random.nextInt(content.length)];
	
		AddDTO ad = new AddDTO(1, randomTitle, randomContent);
		ad.increaseCount();
		return ad;
	
	}
	public AddDTO select(int id) {
		String updateSql = "UPDATE ad SET send_count = send_count + 1 WHERE id = ?";
		String sql = "SELECT * FROM ad where id = ?";
		
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
        	con = DBUtill.getConnection();
        
            // 1) 송출 횟수 증가 쿼리 실행
            stmt = con.prepareStatement(updateSql);
            stmt.setInt(1, id);
            int updateCount = stmt.executeUpdate();
            stmt.close();
            
        	stmt = con.prepareStatement(sql);
        	stmt.setInt(1, id);
        	rs= stmt.executeQuery();

            if (updateCount == 0) {
                return null;
            }
            
        	if(rs.next()) {
        		AddDTO ado = new AddDTO(id, rs.getString("content"), rs.getString("name"));
        		ado.setCount(rs.getInt("send_count"));
        		return ado;
        	}
        	
        }catch (SQLException e) {
        	e.printStackTrace();
        }finally {
        	DBUtill.close(rs, stmt, con);
        }
        return null;
	}
}
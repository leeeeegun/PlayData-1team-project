package dao.add;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

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
		String sql = "SELECT * FROM ad where id = ?";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        ArrayList<AddDTO> addlist = new ArrayList<AddDTO>();
        
        try {
        	con = DBUtill.getConnection();
        	stmt = con.prepareStatement(sql);
        	stmt.setInt(1, id);
        	rs= stmt.executeQuery();
        	
        	if(rs.next()) {
        		AddDTO ado = new AddDTO(0, rs.getString("content"), rs.getString("name"));
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
	
	
	
	
	
	
	
	

//	// 광고 타이틀
//	title = new String[] {
//		// 자바 입문 
//		"  \"자바가 뭐예요...?\"\n" +
//		"     (•_•)\n" +
//		"    <)   )╯  📘\n" +
//		"     /   \\   ‘최현수 자바 입문’ 드가자!",
//		
//		// 자바 초급
//		"  \"이게 자바...? 현수가 박살!\"\n" +
//		"     ( ﾟдﾟ )\n" +
//		"     <) )╯🎤\n" +
//		"     /  >    println으로 초급 장악!",
//		
//		// 중급 광고
//		"  \"디버깅은 내 운명...\"\n" +
//		"     (╥﹏╥)\n" +
//		"     ⌨️  ‘null pointer’ 만나면 최현수해요..."
//	};
//	
//	// 광고 내용(설명)
//	content = new String[] {
//		"\n <<핫썸머 COOL한 최대 60% SALE>>",
//		"\n <<지금 구매하면 현수님 친필 싸인 증정!>>",
//		"\n <<오늘 하루만 역대급 특가 제공!!>>",
//		"\n <<지금 구매하면 1+1 강의 혜택>>",
//		"\n <<저희 현수님이 미쳤어요! 90% 할인>>"
//	};
//		
//}
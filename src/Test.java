import util.DBUtill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {

    public static void test(){
        String sql = "select * from customer";
        Connection con =null;
        PreparedStatement stmt =null;
        ResultSet rs = null;
        try {
            con = DBUtill.getConnection();
            stmt =  con.prepareStatement(sql);
            rs =  stmt.executeQuery();
            System.out.println("select실행결과=>"+rs);
            while(rs.next()) {//레코드가 있으면 true,없으면 false반환
                System.out.print(rs.getString(1)+"\t"); //조회된 테이블에서 1번 컬럼 읽기
                System.out.print(rs.getString("pass")+"\t");
                System.out.print(rs.getString("name")+"\t");
                System.out.print(rs.getString(4)+"\t");
                System.out.print(rs.getDate(5)+"\t");
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //작업이 완료된 후 점유하는 모든 자원이 메모리에서 해제
            DBUtill.close(rs, stmt, con);
        }
    }

}

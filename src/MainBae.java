import java.sql.SQLException;
import java.text.ParseException;

import Controller.payment.PaymentView;
import Controller.rating.RatingView;
import Controller.userinfo.UserInfoController;
import dao.login.LoginDao;
import dao.payment.PaymentDao;
import dto.user.UserDTO;

public class MainBae {

    public static void main(String[] args) throws SQLException, InterruptedException {

        //RatingView.insertRating();
        //RatingView.updateRating();
        //RatingView.deleteRating();
//    	PaymentView pay = new PaymentView();
//    	pay.paymentSystem();
    	UserDTO userDTO = new UserDTO(1, null, 0, null);
    	UserInfoController use1 = new UserInfoController();
    	use1.myPage(userDTO);
    	

    }
}

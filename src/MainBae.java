import java.text.ParseException;

import Controller.payment.PaymentView;
import Controller.rating.RatingView;
import Controller.userinfo.UserInfoController;
import dao.payment.PaymentDao;

public class MainBae {

    public static void main(String[] args){

        //RatingView.insertRating();
        //RatingView.updateRating();
        //RatingView.deleteRating();
//    	PaymentView pay = new PaymentView();
//    	pay.paymentSystem();
    	UserInfoController use1 = new UserInfoController();
    	String a = "user1";
    	String b = "a123";
    	
    	use1.myPage(a,b);
    	

    }
}

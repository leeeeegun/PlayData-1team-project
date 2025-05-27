import Controller.rating.RatingView;
import dao.payment.PaymentDao;

public class MainBae {

    public static void main(String[] args) {

        //RatingView.insertRating();
        //RatingView.updateRating();
        //RatingView.deleteRating();
    	PaymentDao pay = new PaymentDao();
    	System.out.println(pay.userMoney(1));
    	

    }
}

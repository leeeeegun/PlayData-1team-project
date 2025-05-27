package Controller.payment;

import java.util.Scanner;

import dao.payment.PaymentDao;

public class PaymentView {
	
	public void paymentSystem() {
		
		Scanner key = new Scanner(System.in);
		PaymentDao pay = new PaymentDao();
		
		System.out.println("=== 결제 화면 ===");
		System.out.println("선택한 강의:");
		System.out.println( title + " | 금액: "+ );
		
		System.out.println("총 결제 금액: ");
		
		System.out.println("결제하시겠습니까? 1.예 2.아니오");
		int result = key.nextInt();
		if(result==1) {
			System.out.println("결제가 완료되었습니다");
		} else {
			System.out.println("결제가 취소되었습니다.");
		}
	}
}

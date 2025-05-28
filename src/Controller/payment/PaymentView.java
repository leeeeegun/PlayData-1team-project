package Controller.payment;

import java.util.ArrayList;
import java.util.Scanner;
import dao.payment.PaymentDao;
import dto.payment.PaymentDTO;

public class PaymentView {
	public static void payInfo() {//결제 정보 화면에 보여주는거
		
	}
	
	public static void paymentSystem() {
		PaymentDao pay = new PaymentDao();
		Scanner sc = new Scanner(System.in);
		//강의 선택
		System.out.println("=== 결제 화면 ===");
		System.out.println("선택한 강의:");
		int id = sc.nextInt(); //강의선택
		//선택한 강의 목록
		String title = "";
		int price = 0;
		ArrayList<PaymentDTO> list = pay.getLecturesPrice(id);
		for(PaymentDTO lectur : list) {
			title = lectur.getTitle();
			price = lectur.getPrice();
		}
		System.out.println( "강의명: "+title+" | 금액: "+price +"원");
		System.out.println("====================================");
		System.out.println("유저 선택:");
		int userId = sc.nextInt();
		int userMoney = pay.userMoney(userId);
		System.out.println("현재 잔여 포인트: "+userMoney);//--> 유저를 정하는걸 어떻게 하지?
		System.out.println("총 결제 금액: "+price);
		
		System.out.println("결제하시겠습니까? 1.예 2.아니오");
		int result = sc.nextInt();
		if(result==1) {
			System.out.println("결제가 완료되었습니다.");
			System.out.println("====================================");
			System.out.println("현재 회원님의 포인트는 "+(userMoney-price));
		} else {
			System.out.println("결제가 취소되었습니다. 다시 결제 페이지로 돌아갑니다");
		}
	}
}

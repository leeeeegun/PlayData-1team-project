package Controller.payment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import Controller.landing.LandingView;
import dao.payment.PaymentDao;
import dto.payment.PaymentDTO;
import dto.user.UserDTO;

public class PaymentView {
	public static void payInfo() {//결제 정보 화면에 보여주는거
		
	}
	
	public static void paymentSystem(UserDTO userDTO) throws SQLException, InterruptedException {
		PaymentDao pay = new PaymentDao();
		Scanner sc = new Scanner(System.in);
		//강의 선택
		System.out.println("================ 결제 화면 =============");
		System.out.println();
		System.out.println("강의 선택 : ");
		int id = sc.nextInt(); //강의선택
		//선택한 강의 목록
		String title = "";
		int price = 0;
		ArrayList<PaymentDTO> list = pay.getLecturesPrice(id);
		for(PaymentDTO lectur : list) {
			title = lectur.getTitle();
			price = lectur.getPrice();
		}
		System.out.println();
		System.out.println( "강의명: "+title+" | 금액: "+price +"원");
		System.out.println();
		System.out.println("====================================");
		System.out.println();
		System.out.println();

		int userMoney = pay.userMoney(userDTO.getId());
		System.out.println("현재 잔여 포인트: "+userMoney);//--> 유저를 정하는걸 어떻게 하지?
		System.out.println("총 결제 금액: "+price);
//		System.out.println( title + " | 금액: "+ );
		
		System.out.println("총 결제 금액: ");

		System.out.println();
		System.out.println();
		System.out.println("결제하시겠습니까? 1.예 2.아니오");
		int result = sc.nextInt();

		System.out.println();
		System.out.println();

		System.out.print("결제 중입니다");

		for (int i = 0; i < 3; i++) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.print(".");
		}

		if(result !=1){
			System.out.println("==============================");
			System.out.println("결제에 실패 했습니다.");
			System.out.println("==============================");
		}

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}



		int currentPoint = pay.deductUserPointsAndGetBalance(userDTO.getId(), price);
		userDTO.setMoney(currentPoint);

		System.out.println();
		if(result==1) {
			System.out.println("결제가 완료되었습니다.");
			System.out.println("====================================");
			System.out.println("현재 회원님의 포인트는 "+(userDTO.getMoney()));
			System.out.println();
			System.out.println();
			System.out.println("       확인 [엔터(Enter)]    ");
			System.out.println();
			System.out.println("==============================");
			String input = sc.nextLine();

			if (input.isEmpty()) {
				LandingView.LandingLogin(userDTO);
			} else {
				System.out.println("잘못된 입력입니다. 프로그램을 종료합니다.");
			}
		} else {
			System.out.println("결제가 취소되었습니다. 다시 결제 페이지로 돌아갑니다");
		}
	}
}

package Controller.payment;

import Controller.landing.LandingView;
import dao.payment.PointDao;
import dto.user.UserDTO;

import java.sql.SQLException;
import java.util.Scanner;

public class PointController {

    public static void PointAdd(UserDTO userDTO) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("==============================");
        System.out.println("           포인트 충전            ");
        System.out.println();
        System.out.println();
        System.out.print(" 금액 :           ");
        int amount = sc.nextInt();
        System.out.println();
        System.out.print(" 전화번호 :           ");
        int tell = sc.nextInt();
        System.out.println();
        System.out.println();
        System.out.println("==============================");

        PointAddCheck(userDTO, amount);

    }

    public static void PointAddCheck(UserDTO userDTO, int account) throws SQLException {

        PointDao pointDao = new PointDao();

        Scanner sc = new Scanner(System.in);
        System.out.println("==============================");
        System.out.println("           포인트 충전            ");
        System.out.println();
        System.out.println();
        System.out.println("       포인트 충전 하시겠습니까      ");
        System.out.println();
        System.out.println();
        System.out.println("       1. 예        2. 아니오     ");
        int check = sc.nextInt();
        System.out.println("==============================");

        if(check == 1){
            System.out.print("결제 중입니다");

            int result = pointDao.PointAdd(userDTO, account);
            // 점을 하나씩 추가하며 출력
            for (int i = 0; i < 3; i++) {
                try {
                    Thread.sleep(500); // 0.5초 대기
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print("."); // 점 추가 출력
            }

            if(result !=1){
                System.out.println("==============================");
                System.out.println("결제에 실패 했습니다.");
                System.out.println("==============================");
            }

            // 줄 바꿈
            try {
                Thread.sleep(3000); // 3초 동안 멈춤 (밀리초 단위)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println();
            PointAddSuccess(userDTO);

        } else if (check == 2){
            LandingView.LandingLogin(userDTO);
        } else{
            System.out.println("==============================");
            System.out.println("잘못입력 하셨습니다");
            System.out.println("==============================");
            PointAddCheck(userDTO, account);
        }


    }

    public static void PointAddSuccess(UserDTO userDTO) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("==============================");
        System.out.println("           포인트 충전            ");
        System.out.println();
        System.out.println();
        System.out.println("       포인트가 충전 되었습니다.    ");
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
    }



}

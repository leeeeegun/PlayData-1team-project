package Controller.landing;

import Controller.login.LoginView;

import java.sql.SQLException;
import java.util.Scanner;

public class LandingView {

    public static void LandingNotLogin() throws SQLException {
        Scanner sc = new Scanner(System.in);

        System.out.println("======================================");
        System.out.println();
        System.out.println("                 플레인프런              ");
        System.out.println(" 1. 로그인         ");
        System.out.println(" 2. 강의          ");
        System.out.println();
        System.out.println();
        System.out.print(" 선택 :           ");
        int choice = sc.nextInt();
        System.out.println();
        System.out.println();
        System.out.println("======================================");

        if(choice == 1){
            LoginView.login();
        } else{

        }

    }

    public static void LandingLogin(String name,int point, String grade ){
        System.out.println("======================================");
        System.out.println();
        System.out.println("            플레인프런          ");
        System.out.println();
        System.out.println(" 포인트 : 30000   등급 : 열공중    최현수님");
        System.out.println();
        System.out.println(" 1. 로그인         ");
        System.out.println(" 2. 강의          ");
        System.out.println(" 2. 강의 올리기          ");
        System.out.println(" 2. 마이페이지          ");
        System.out.println(" 2. 포인트 충전          ");
        System.out.println();
        System.out.println(" 선택 :           ");
        System.out.println();
        System.out.println();
        System.out.println("======================================");

    }



}

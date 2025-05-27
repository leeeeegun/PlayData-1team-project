package Controller.login;

import dao.login.LoginDao;

import java.sql.SQLException;
import java.util.Scanner;

public class LoginView {

    public static void login() throws SQLException {

        LoginDao loginDao = new LoginDao();

        Scanner sc = new Scanner(System.in);
        System.out.println("=============================");
        System.out.println("             로그인            ");
        System.out.print("아이디  :  ");
        String id = sc.nextLine();
        System.out.print("비밀번호  :  ");
        String password = sc.nextLine();
        System.out.println("=============================");



        String result = loginDao.Login(id, password);
        System.out.println(result);
//        if(result == 1){
//            System.out.println(" 로그인 성공 ");
//        } else{
//            System.out.println(" 로그인 실패 ");
//        }


    }

}

package Controller.login;

import Controller.landing.LandingView;
import dao.login.LoginDao;
import dto.user.UserDTO;

import java.sql.SQLException;
import java.util.Scanner;

public class LoginView {

    public static void login() throws SQLException {
        Scanner sc = new Scanner(System.in);
        LoginDao loginDao = new LoginDao();

        try {
            sc = new Scanner(System.in);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("=============================");
        System.out.println("             로그인            ");
        System.out.print("아이디  :  ");
        String id = sc.nextLine();
        System.out.print("비밀번호  :  ");
        String password = sc.nextLine();
        System.out.println("=============================");



        try {
            UserDTO userDTO = loginDao.Login(id, password);
            LandingView.LandingLogin(userDTO);
        } catch (SQLException e) {
            System.out.println("데이터베이스 오류가 발생했습니다.");
            e.printStackTrace();
        } catch (NullPointerException e){
            System.out.println("아이디 또는 비밀번호가 틀렸습니다.");
            login();
        }
//        if(result == 1){
//            System.out.println(" 로그인 성공 ");
//        } else{
//            System.out.println(" 로그인 실패 ");
//        }


    }

}

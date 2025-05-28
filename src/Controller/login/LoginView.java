package Controller.login;

import Controller.add.AddController;
import Controller.landing.LandingView;
import dao.login.LoginDao;
import dto.login.JoinDTO;
import dto.user.UserDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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

        AddController.addEditor();
        System.out.println();
        System.out.println("=============================");
        System.out.println("             로그인            ");
        System.out.print("아이디  :  ");
        String id = sc.nextLine();
        System.out.print("비밀번호  :  ");
        String password = sc.nextLine();
        System.out.println("=============================");



        try {
            UserDTO userDTO = loginDao.Login(id, password);

            if(userDTO.getGrade().equals("관리자")){
                LandingView.LandingAdmin();
            } else{
                LandingView.LandingLogin(userDTO);

            }
        } catch (SQLException e) {
            System.out.println("데이터베이스 오류가 발생했습니다.");
            e.printStackTrace();
        } catch (NullPointerException e){
            System.out.println();
            System.out.println("아이디 또는 비밀번호가 틀렸습니다.");
            System.out.println();
            System.out.println();
            login();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//        if(result == 1){
//            System.out.println(" 로그인 성공 ");
//        } else{
//            System.out.println(" 로그인 실패 ");
//        }


    }

    public static void join() throws SQLException {
        Scanner sc = new Scanner(System.in);
        LoginDao loginDao = new LoginDao();

        System.out.println("=============================");
        System.out.println("           회원가입            ");
        System.out.print("이름 : ");
        String name = sc.nextLine();
        System.out.print("아이디 : ");
        String loginId = sc.nextLine();
        System.out.print("비밀번호 : ");
        String password = sc.nextLine();
        System.out.print("생년월일 (yyyy-MM-dd) : ");
        String birth = sc.nextLine();
        System.out.print("전화번호 : ");
        String phone = sc.nextLine();
        System.out.println("=============================");

        try {
            LocalDate birthDate = LocalDate.parse(birth);
            System.out.println("!!!!!!!!!!!!!!!!!");
            System.out.println(birthDate);
            System.out.println(birthDate.getClass().getName());
            System.out.println("!!!!!!!!!!!!!!!!!");
            JoinDTO joinDTO = new JoinDTO(name, loginId, password, birthDate, phone);
            int result = loginDao.joinUser(joinDTO);

            if (result == 1) {
                System.out.println("회원가입이 완료되었습니다.");
                LoginView.login();
            } else {
                System.out.println("회원가입에 실패했습니다.");
                join();
            }

        } catch (DateTimeParseException e) {
            System.out.println("생년월일 형식이 잘못되었습니다. yyyy-MM-dd 형식으로 입력해주세요.");
            join();
        }
    }


}

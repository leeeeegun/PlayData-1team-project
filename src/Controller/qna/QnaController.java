package Controller.qna;

import Controller.landing.LandingView;
import dao.qna.QnaDao;
import dto.qna.QnaStatisticsDTO;
import dto.qna.QnaSummaryDTO;
import dto.user.UserDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class QnaController {

    public static void QnaMain(UserDTO userDTO) throws SQLException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        QnaDao qnaDao = new QnaDao();
        QnaStatisticsDTO qnaStatisticsByUserId = qnaDao.getQnaStatisticsByUserId(userDTO.getId());
        int isAnsweredCount = 0;
        int isAnsweredNotCount = 0;
        int newAnswerCount = 0;


        System.out.println("====================================");
        System.out.println();
        System.out.println("               Q & A                ");
        System.out.println();
        System.out.println();
        if (qnaStatisticsByUserId.getTotal() == 0) {
            System.out.println("             비어 있음              ");
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
            System.out.println("  총 문의 개수 : " + qnaStatisticsByUserId.getTotal() + "개");
            System.out.println();
            System.out.println("  1. new 답변 완료된 문의 : " + qnaStatisticsByUserId.getNewAnswerCount() + "개");
            System.out.println();
            System.out.println("  2. 답변 완료된 문의 : " + qnaStatisticsByUserId.getAnsweredCount() + "개");
            System.out.println();
            System.out.println("  3. 미답변 문의 : " + qnaStatisticsByUserId.getUnansweredCount() + "개");
            System.out.println();
            System.out.println();
            System.out.println("        1. 문의 하기          2. 문의 보기 ");
            System.out.println();
            System.out.println("====================================");
            System.out.println();
            System.out.println();
            System.out.println(" 입력 : ");
            int input = sc.nextInt();
            System.out.println();
            if(input == 1){

            } else if (input == 2){
                System.out.println();
                System.out.println();
                System.out.println(" 시스템 번호 입력 : " );
                int num = sc.nextInt();
                System.out.println();
                System.out.println();
                if (num == 1){

                } else if (num == 2) {

                } else if (num == 3) {

                } else {
                    System.out.println("==================================");
                    System.out.println("           잘못된 입력입니다.         ");
                    System.out.println("==================================");
                    System.out.println();
                    System.out.println();
                    LandingView.LandingLogin(userDTO);
                }
            } else{
                System.out.println("==================================");
                System.out.println("           잘못된 입력입니다.         ");
                System.out.println("==================================");
                System.out.println();
                System.out.println();
                LandingView.LandingLogin(userDTO);
            }

        }



    }


    public static void QnaAdd(){
        getQnaSummariesByUserId
    }

    public static void QnaGetByCount(UserDTO userDTO) throws SQLException {
        QnaDao qnaDao = new QnaDao();
        List<QnaSummaryDTO> qnaSummariesByUserId = qnaDao.getQnaSummariesByUserId(userDTO.getId());

        qnaSummariesByUserId.stream().


    }


}

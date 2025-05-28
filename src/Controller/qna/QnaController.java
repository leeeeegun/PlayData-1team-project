package Controller.qna;

import Controller.landing.LandingView;
import dao.qna.QnaDao;
import dto.qna.QnaStatisticsDTO;
import dto.qna.QnaSummaryDTO;
import dto.user.UserDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QnaController {

    public static void QnaMain(UserDTO userDTO) throws SQLException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        QnaDao qnaDao = new QnaDao();
        QnaStatisticsDTO qnaStatisticsByUserId = qnaDao.getQnaStatisticsByUserId(userDTO.getId());



        System.out.println("====================================");
        System.out.println();
        System.out.println("               Q & A                ");
        System.out.println();
        System.out.println();
        System.out.println();
        if (qnaStatisticsByUserId.getTotal() == 0) {
            System.out.println("  [           비어 있음           ]   ");
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("     1. 문의 하기        2. 뒤로가기 ");
            System.out.println();
            System.out.println("====================================");
            System.out.println();
            System.out.println();
            System.out.print(" 입력 : ");
            int input = sc.nextInt();
            System.out.println();
            if(input == 1){
                QnaAdd(userDTO);
            } else if (input == 2){
                LandingView.LandingLogin(userDTO);
            } else {
                System.out.println("==================================");
                System.out.println("           잘못된 입력입니다.         ");
                System.out.println("==================================");
                System.out.println();
                System.out.println();
                LandingView.LandingLogin(userDTO);
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
            System.out.println("     1. 문의 하기      2. 문의 보기     3. 뒤로가기");
            System.out.println();
            System.out.println("====================================");
            System.out.println();
            System.out.println();
            System.out.print(" 입력 : ");
            int input = sc.nextInt();
            System.out.println();
            if(input == 1){
                QnaAdd(userDTO);
            } else if (input == 2){
                System.out.println();
                System.out.println();
                System.out.print(" 문의종류 번호 : " );
                int num = sc.nextInt();
                System.out.println();
                System.out.println();

                QnaGetByCount(userDTO, num);

            } else if (input == 3) {
                LandingView.LandingLogin(userDTO);

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


    public static void QnaAdd(UserDTO userDTO) throws SQLException, InterruptedException {
        QnaDao qnaDao = new QnaDao();
        Scanner sc = new Scanner(System.in);
        System.out.println("====================================");
        System.out.println();
        System.out.println("               Q & A                ");
        System.out.println();
        System.out.println();
        System.out.print("  제 목 : ");
        String title = sc.nextLine();
        System.out.println();
        System.out.print("  문 의  : ");
        String content = sc.nextLine();
        System.out.println();
        System.out.println();
        System.out.println("       확인 [엔터(Enter)]    ");
        System.out.println();
        System.out.println("==============================");
        String input = sc.nextLine();

        if (input.isEmpty()) {
            System.out.print("문의 등록 중입니다");

            for (int i = 0; i < 3; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print(".");
            }
            System.out.println();
            System.out.println();

            boolean reuslt = qnaDao.insertQna(title, content, userDTO.getId());

            if (reuslt) {
                System.out.println("==============================");
                System.out.println();
                System.out.println("         문의 등록 성공 !        ");
                System.out.println();
                System.out.println("==============================");
                System.out.println();
                System.out.println();

                QnaMain(userDTO);
            } else {
                System.out.println("==============================");
                System.out.println();
                System.out.println("        문의 등록 실패 ...        ");
                System.out.println();
                System.out.println("==============================");
                System.out.println();
                System.out.println();

                QnaMain(userDTO);
            }


        } else {
            System.out.println("잘못된 입력입니다. 프로그램을 종료합니다.");
        }



    }

    public static void QnaGetByCount(UserDTO userDTO, int num) throws SQLException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        QnaDao qnaDao = new QnaDao();
        List<QnaSummaryDTO> qnaSummariesByUserId = new ArrayList<>();

        if (num == 1) {
            qnaSummariesByUserId = qnaDao.getQnaSummariesByUserIdNew(userDTO.getId());
        } else if (num == 2) {
            qnaSummariesByUserId = qnaDao.getQnaSummariesByUserIdToAnswered(userDTO.getId());
        } else {
            qnaSummariesByUserId = qnaDao.getQnaSummariesByUserIdToNotAnswered(userDTO.getId());
        }


        System.out.println("====================================");
        System.out.println();
        System.out.println("               Q & A                ");
        System.out.println();
        System.out.println();
        if (qnaSummariesByUserId.isEmpty()) {
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
            for (QnaSummaryDTO qnaSummaryDTO : qnaSummariesByUserId) {
                System.out.println(qnaSummaryDTO.getId() + ". " +
                        qnaSummaryDTO.getTitle());
                System.out.println();
            }

            System.out.println();
            System.out.println();
            System.out.println("        1. 상세보기         2. 뒤로가기 ");
            System.out.println();
            System.out.println("====================================");
            System.out.println();
            System.out.println();
            System.out.print(" 입력 : ");
            int input = sc.nextInt();


            if(input == 1){
                System.out.print(" 번호 입력 : ");
                int number = sc.nextInt();
                System.out.println();
                QnaGet(userDTO, number, num);

            } else if (input == 2) {
                QnaMain(userDTO);
            } else{
                LandingView.LandingLogin(userDTO);
            }


        }
    }



    public static void QnaGet(UserDTO userDTO, int number, int num) throws SQLException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        QnaDao qnaDao = new QnaDao();
        QnaSummaryDTO qnaSummaryById = qnaDao.getQnaSummaryById(number);


        System.out.println("====================================");
        System.out.println();
        System.out.println("               Q & A                ");
        System.out.println();
        System.out.println();
        if (qnaSummaryById == null) {
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

            System.out.println("    제목 :  " + qnaSummaryById.getTitle() );
            System.out.println();
            System.out.println("    문의 :  " + qnaSummaryById.getTitle() );
            System.out.println();
            if (qnaSummaryById.getAnswer() != null) {
                System.out.println("   답변  : " + qnaSummaryById.getAnswer());
            }
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("       확인 [엔터(Enter)]    ");
            System.out.println();
            System.out.println("==============================");
            String input = sc.nextLine();

            if (input.isEmpty()) {
                QnaGetByCount(userDTO, num);
            } else {
                System.out.println("잘못된 입력입니다. 프로그램을 종료합니다.");
            }



        }
    }


}

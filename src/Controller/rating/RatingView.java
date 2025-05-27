package Controller.rating;

import java.util.ArrayList;
import java.util.Scanner;

import dao.rating.RatingDao;

public class RatingView {
	
	/*
	 * 평점 작성
     * 평점 수정
     * 평점 삭제
     * 강의 평점 보기(평점 계산)
	 */
	public static void insertRating() {
		Scanner sc = new Scanner(System.in);
		RatingDao rao = new RatingDao();
		
	    System.out.println("**********평점 추가하기**********");
	    System.out.println("id:");
	    int id = sc.nextInt();
	    System.out.println("평점:");
	    float rating = sc.nextFloat();
	    int result = rao.ratingInsert(id, rating);
	    
	    if(result>=1) {
	    	System.out.println("평점 등록에 성공하였습니다.");
	    } else {
	    	System.out.println("평점 등록에 실패하였습니다.");
		}
	    
	}
	
	public static void updateRating() {
		Scanner sc = new Scanner(System.in);
		RatingDao rao = new RatingDao();
		
	    System.out.println("**********평점 수정하기**********");
	    System.out.println("lecture_id:");
	    int lecture_id = sc.nextInt();
	    System.out.println("수정할 평점:");
	    float rating = sc.nextFloat();
	    int result = rao.ratingUpdate(lecture_id, rating);
	    
	    if(result>=1) {
	    	System.out.println("평점 수정에 성공하였습니다.");
	    } else {
	    	System.out.println("평점 수정에 실패하였습니다.");
		}
	    
	}
	public static void deleteRating() {
		Scanner sc = new Scanner(System.in);
		RatingDao rao = new RatingDao();
		
	    System.out.println("**********평점 삭제하기**********");
	    System.out.println("id:");
	    int id = sc.nextInt();
	    int result = rao.ratingDelet(id);
	    
	    if(result>=1) {
	    	System.out.println("평점 삭제에 성공하였습니다.");
	    } else {
	    	System.out.println("평점 삭제에 실패하였습니다.");
		}
	    
	}
	
	
}

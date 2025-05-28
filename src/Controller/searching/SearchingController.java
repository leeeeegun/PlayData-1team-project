package Controller.searching;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import Controller.landing.LandingView;
import dao.searching.SearchingDao;
import dto.searching.SearchingDTO;
import dto.user.UserDTO;

public class SearchingController {
	
	public static void searchingController(UserDTO userDTO) throws SQLException {
		Scanner sc = new Scanner(System.in);
		SearchingDao searchingDao = new SearchingDao();
		System.out.println("===============================");
		System.out.println();
		System.out.println("       강  의  검  색        ");
		System.out.println();
		System.out.print("제목을 입력하세요  :   ");
		String title = sc.next();
		System.out.println();
		ArrayList<SearchingDTO> searchList = searchingDao.searchingTitle(title);
		System.out.println();
		System.out.println("===============================");
		
		for(SearchingDTO search:searchList) {
			System.out.println("번호 :  "+search.getID());
			System.out.println("제목 :  "+search.getTitle());
			System.out.println("내용 :  "+search.getDescription());
			System.out.println("작성자 :  "+search.getInstructor_id());
			System.out.println("가격 :  "+search.getPrice());
			System.out.println("평점 :  "+search.getRating());
			System.out.println("카테고리 :  "+search.getCategory());
			System.out.println("즐겨찾기 :  "+search.getFavorite_count());
			System.out.println("생성날짜 :  "+search.getCreated_at());
			System.out.println("등록날짜 :  "+search.getUpdated_at());
		}

		System.out.println("a");


		String input = sc.nextLine();

		if (input.isEmpty()) {
			LandingView.LandingLogin(userDTO);
		} else {
			System.out.println("잘못된 입력입니다. 프로그램을 종료합니다.");
		}

		
		
		
  			
	}

	public static void searchingController() throws SQLException {
		Scanner sc = new Scanner(System.in);
		SearchingDao searchingDao = new SearchingDao();
		System.out.println("===============================");
		System.out.println();
		System.out.println("       강  의  검  색        ");
		System.out.println();
		System.out.print("제목을 입력하세요  :   ");
		String title = sc.next();
		System.out.println();
		ArrayList<SearchingDTO> searchList = searchingDao.searchingTitle(title);
		System.out.println();
		System.out.println("===============================");

		for(SearchingDTO search:searchList) {
			System.out.println("번호 :  "+search.getID());
			System.out.println("제목 :  "+search.getTitle());
			System.out.println("내용 :  "+search.getDescription());
			System.out.println("작성자 :  "+search.getInstructor_id());
			System.out.println("가격 :  "+search.getPrice());
			System.out.println("평점 :  "+search.getRating());
			System.out.println("카테고리 :  "+search.getCategory());
			System.out.println("즐겨찾기 :  "+search.getFavorite_count());
			System.out.println("생성날짜 :  "+search.getCreated_at());
			System.out.println("등록날짜 :  "+search.getUpdated_at());
		}

		System.out.println();
		System.out.println("   1. 구매하기         2. 뒤로 가기          ");

		int input = sc.nextInt();

		if (input == 1) {
			System.out.println("==========================");
			System.out.println("         번호 입력           ");
			System.out.println();
			System.out.println(" 번호 :  ");
			int num = sc.nextInt();


		} else if (input == 2){
			LandingView.LandingNotLogin();
		} else{
			System.out.println("잘못된 입력입니다.");
			LandingView.LandingNotLogin();
		}





	}

}

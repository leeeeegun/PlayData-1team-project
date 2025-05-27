package Controller.searching;

import java.util.ArrayList;
import java.util.Scanner;

import dao.searching.SearchingDao;
import dto.searching.SearchingDTO;

public class SearchingController {
	
	public static void searchingController() {
		Scanner sc = new Scanner(System.in);
		SearchingDao searchingDao = new SearchingDao();
		System.out.println("===============================");
		System.out.println("       강  의  검  색        ");
		System.out.print("제목을 입력하세요  :   ");
		String title = sc.next();
		ArrayList<SearchingDTO> searchList = searchingDao.searchingTitle(title);
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

		
		
		
  			
	}

}

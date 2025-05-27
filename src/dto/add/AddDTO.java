package dto.add;

public class AddDTO {
	
	int id;
	String title;
	String content;
	int count;
	
	public AddDTO(int id, String title, String content) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.count = 0;
	}
	
	// 광고 송출 횟수 카운트
	public void increaseCount() {
		this.count++;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	// 하루가 끝날 때 송출 횟수 초기화 
	public void resetDailyCount() {
		this.count = 0;
	}
}
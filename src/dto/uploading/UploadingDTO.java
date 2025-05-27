package dto.uploading;

import java.util.Date;

public class UploadingDTO {
	private String title;
	private String description;
	private int instructor_id;
	private int price;
	private String Category;
	
	public UploadingDTO(String title, String description, int instructor_id, int price, String category) {
		super();
		this.title = title;
		this.description = description;
		this.instructor_id = instructor_id;
		this.price = price;
		Category = category;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getInstructor_id() {
		return instructor_id;
	}
	public void setInstructor_id(int instructor_id) {
		this.instructor_id = instructor_id;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	
	
	
	
	
	

}

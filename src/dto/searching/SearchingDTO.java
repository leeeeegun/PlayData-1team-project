package dto.searching;

import java.util.Date;

public class SearchingDTO {
	int ID;
	String title;
	String description;
	int instructor_id;
	int price;
	float rating;
	String category;
	int favorite_count;
	Date created_at;
	Date updated_at;
	
	public SearchingDTO(){
		
	}
	public SearchingDTO(int iD, String title, String description, int instructor_id, int price, float rating,
			String category, int favorite_count, Date created_at, Date updated_at) {
		super();
		ID = iD;
		this.title = title;
		this.description = description;
		this.instructor_id = instructor_id;
		this.price = price;
		this.rating = rating;
		this.category = category;
		this.favorite_count = favorite_count;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	@Override
	public String toString() {
		return "SearchingDTO [ID=" + ID + ", title=" + title + ", description=" + description + ", instructor_id="
				+ instructor_id + ", price=" + price + ", rating=" + rating + ", category=" + category
				+ ", favorite_count=" + favorite_count + ", created_at=" + created_at + ", updated_at=" + updated_at
				+ "]";
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
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
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getFavorite_count() {
		return favorite_count;
	}
	public void setFavorite_count(int favorite_count) {
		this.favorite_count = favorite_count;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public Date getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	
}

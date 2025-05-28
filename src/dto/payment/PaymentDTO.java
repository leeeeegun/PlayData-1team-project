package dto.payment;


public class PaymentDTO {
	private int id;
	private String title;
	private String description;
	private String instructor_id;
	private int price;
	
	public PaymentDTO() {
	}
	
	public PaymentDTO(String title, int price) {
		super();
		this.title = title;
		this.price = price;
	}

	public PaymentDTO(int id, String title, String description, String instructor_id, int price) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.instructor_id = instructor_id;
		this.price = price;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getInstructor_id() {
		return instructor_id;
	}

	public void setInstructor_id(String instructor_id) {
		this.instructor_id = instructor_id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
}

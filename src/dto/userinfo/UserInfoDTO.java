package dto.userinfo;

import java.time.LocalDate;

public class UserInfoDTO {
	
	private int id;
    private String login_id;
    private String password;
    private String name;
    private LocalDate birth_date;
    private String phone;
    private int money;
    private String grade;
    private int total_watch_time;
    
	public UserInfoDTO() {
		
	}
	public UserInfoDTO(String login_id, String password) {
		this.login_id = login_id;
		this.password = password;
	}
	
	public UserInfoDTO(String password, String name, LocalDate birth_date, String phone) {
		super();
		this.password = password;
		this.name = name;
		this.birth_date = birth_date;
		this.phone = phone;
	}
	public UserInfoDTO(String login_id, String name, LocalDate birth_date, String phone, int money,
			String grade, int total_watch_time) {
		super();
		this.login_id = login_id;
		this.name = name;
		this.birth_date = birth_date;
		this.phone = phone;
		this.money = money;
		this.grade = grade;
		this.total_watch_time = total_watch_time;
	}

	@Override
	public String toString() {
		return "UserInfoDTO [login_id=" + login_id + ", password=" + password + ", name=" + name + ", birth_date="
				+ birth_date + ", phone=" + phone + ", money=" + money + ", grade=" + grade + ", total_watch_time="
				+ total_watch_time + "]";
	}

	public String getLogin_id() {
		return login_id;
	}

	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(LocalDate birth_date) {
		this.birth_date = birth_date;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public int getTotal_watch_time() {
		return total_watch_time;
	}

	public void setTotal_watch_time(int total_watch_time) {
		this.total_watch_time = total_watch_time;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
    
    

}

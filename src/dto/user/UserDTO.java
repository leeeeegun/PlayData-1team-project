package dto.user;

public class UserDTO {

    private boolean isLogin;


    private int id;
    private String name;
    private int money;
    private String grade;

    public UserDTO(int id, String name, int money, String grade) {
        this.id = id;
        this.name = name;
        this.money = money;
        this.grade = grade;
        isLogin = true;
    }


    public boolean isLogin() {
        return isLogin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public boolean getIsLogin() {
        return this.isLogin;
    }

    public void setLogin(boolean login) {
        this.isLogin = login;
    }
}

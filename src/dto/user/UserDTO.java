package dto.user;

public class UserDTO {

    private boolean isLogin;
    private String name;
    private String money;
    private String grade;

    public UserDTO(String name, String money, String grade) {
        this.name = name;
        this.money = money;
        this.grade = grade;
        isLogin = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
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

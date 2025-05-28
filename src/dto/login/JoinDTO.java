package dto.login;

import java.time.LocalDate;

public class JoinDTO {
    private String name;
    private String loginId;
    private String password;
    private LocalDate birthDate;
    private String phone;

    public JoinDTO() {}

    public JoinDTO(String name, String loginId, String password, LocalDate birthDate, String phone) {
        this.name = name;
        this.loginId = loginId;
        this.password = password;
        this.birthDate = birthDate;
        this.phone = phone;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}

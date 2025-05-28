package dto.lecture;

public class LectureDurationDTO {
    private int userDuration;
    private int totalDuration;
    private String description;  // 강의 설명

    public LectureDurationDTO(int userDuration, int totalDuration, String description) {
        this.userDuration = userDuration;
        this.totalDuration = totalDuration;
        this.description = description;
    }

    // getter, setter 필요하면 추가
    public int getUserDuration() {
        return userDuration;
    }

    public int getTotalDuration() {
        return totalDuration;
    }

    public String getDescription() {
        return description;
    }
}

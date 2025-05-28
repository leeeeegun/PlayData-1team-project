package dto.admin;

public class DashboardStats {
    private int userCount;
    private int lectureCount;
    private long totalWatchTime;
    private int todayLoginCount;
    private int unansweredQnaCount;
    private int totalSpent;

    // 생성자
    public DashboardStats(int userCount, int lectureCount, long totalWatchTime, int todayLoginCount, int unansweredQnaCount, int totalSpent) {
        this.userCount = userCount;
        this.lectureCount = lectureCount;
        this.totalWatchTime = totalWatchTime;
        this.todayLoginCount = todayLoginCount;
        this.unansweredQnaCount = unansweredQnaCount;
        this.totalSpent = totalSpent;
    }

    // Getter들
    public int getUserCount() { return userCount; }
    public int getLectureCount() { return lectureCount; }
    public long getTotalWatchTime() { return totalWatchTime; }
    public int getTodayLoginCount() { return todayLoginCount; }
    public int getUnansweredQnaCount() { return unansweredQnaCount; }
    public int getTotalSpent() { return totalSpent; }
}

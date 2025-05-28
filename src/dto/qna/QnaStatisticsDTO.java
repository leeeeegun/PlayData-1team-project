package dto.qna;

public class QnaStatisticsDTO {
    private int total;
    private int answeredCount;
    private int unansweredCount;
    private int newAnswerCount;

    public QnaStatisticsDTO() {
    }

    public QnaStatisticsDTO(int total, int answeredCount, int unansweredCount, int newAnswerCount) {
        this.total = total;
        this.answeredCount = answeredCount;
        this.unansweredCount = unansweredCount;
        this.newAnswerCount = newAnswerCount;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getAnsweredCount() {
        return answeredCount;
    }

    public void setAnsweredCount(int answeredCount) {
        this.answeredCount = answeredCount;
    }

    public int getUnansweredCount() {
        return unansweredCount;
    }

    public void setUnansweredCount(int unansweredCount) {
        this.unansweredCount = unansweredCount;
    }

    public int getNewAnswerCount() {
        return newAnswerCount;
    }

    public void setNewAnswerCount(int newAnswerCount) {
        this.newAnswerCount = newAnswerCount;
    }
}


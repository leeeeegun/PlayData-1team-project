package dto.qna;

public class QnaSummaryDTO {
    private String title;
    private boolean isAnswered;
    private boolean hasViewedAnswer;
    private String content;
    private String answer;

    public QnaSummaryDTO(String title, boolean isAnswered, boolean hasViewedAnswer) {
        this.title = title;
        this.isAnswered = isAnswered;
        this.hasViewedAnswer = hasViewedAnswer;
    }

    public QnaSummaryDTO(String title, boolean isAnswered, boolean hasViewedAnswer, String content, String answer) {
        this.title = title;
        this.isAnswered = isAnswered;
        this.hasViewedAnswer = hasViewedAnswer;
        this.content = content;
        this.answer = answer;
    }

    public QnaSummaryDTO(String title, boolean isAnswered, boolean hasViewedAnswer, String content) {
        this.title = title;
        this.isAnswered = isAnswered;
        this.hasViewedAnswer = hasViewedAnswer;
        this.content = content;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public boolean isAnswered() { return isAnswered; }
    public void setAnswered(boolean answered) { isAnswered = answered; }

    public boolean isHasViewedAnswer() { return hasViewedAnswer; }
    public void setHasViewedAnswer(boolean hasViewedAnswer) { this.hasViewedAnswer = hasViewedAnswer; }
}

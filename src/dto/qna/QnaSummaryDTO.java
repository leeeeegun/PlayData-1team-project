package dto.qna;

public class QnaSummaryDTO {

    private int id;
    private String title;
    private boolean isAnswered;
    private boolean hasViewedAnswer;
    private String content;
    private String answer;

    public QnaSummaryDTO(int id, String title, boolean isAnswered, boolean hasViewedAnswer) {
        this.id = id;
        this.title = title;
        this.isAnswered = isAnswered;
        this.hasViewedAnswer = hasViewedAnswer;
    }

    public QnaSummaryDTO(int id, String title, boolean isAnswered, boolean hasViewedAnswer, String content, String answer) {
        this.id = id;
        this.title = title;
        this.isAnswered = isAnswered;
        this.hasViewedAnswer = hasViewedAnswer;
        this.content = content;
        this.answer = answer;
    }

    public QnaSummaryDTO(int id, String title, boolean isAnswered, boolean hasViewedAnswer, String content) {
        this.id = id;
        this.title = title;
        this.isAnswered = isAnswered;
        this.hasViewedAnswer = hasViewedAnswer;
        this.content = content;
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

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }

    public boolean isHasViewedAnswer() {
        return hasViewedAnswer;
    }

    public void setHasViewedAnswer(boolean hasViewedAnswer) {
        this.hasViewedAnswer = hasViewedAnswer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}

package dto.lecture;

public class LectureDTO {

    private int id;
    private String title;

    private int price;

    private String description;

    private int favorite_count;

    public LectureDTO(int id, String title, int price, String description, int favorite_count) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.favorite_count = favorite_count;
    }

    public LectureDTO() {
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFavorite_count() {
        return favorite_count;
    }

    public void setFavorite_count(int favorite_count) {
        this.favorite_count = favorite_count;
    }
}

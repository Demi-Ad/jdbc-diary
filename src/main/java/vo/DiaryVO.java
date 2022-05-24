package vo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DiaryVO {
    private Integer id;
    private String title;
    private String content;

    private String createdAt;

    public DiaryVO(String title, String content) {
        this.title = title;
        this.content = content;
        this.createdAt = LocalDate.now().toString();
    }


    public DiaryVO(Integer id, String title, String content, String createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}

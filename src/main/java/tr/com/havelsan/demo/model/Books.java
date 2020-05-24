package tr.com.havelsan.demo.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("Books")
public class Books {
    private String id;
    private Integer isbn;
    private String name;
    private String authorName;

    public Books(Integer isbn, String name, String authorName) {
        this.isbn = isbn;
        this.name = name;
        this.authorName = authorName;
    }

    public Books() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getIsbn() {
        return isbn;
    }

    public void setIsbn(Integer isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}

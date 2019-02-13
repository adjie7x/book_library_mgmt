package com.mitrais.book_library_mgmt.service.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class BookDTO implements Serializable {

    private String id;

    @NotNull
    @Size(max = 25)
    private String isbn;

    @NotNull
    @Size(max = 50)
    private String title;

    @NotNull
    @Size(max = 30)
    private String author;

    @Size(max = 50)
    private String shelfId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getShelfId() {
        return shelfId;
    }

    public void setShelfId(String shelfId) {
        this.shelfId = shelfId;
    }
}

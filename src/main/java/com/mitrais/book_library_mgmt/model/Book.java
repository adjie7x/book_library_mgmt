package com.mitrais.book_library_mgmt.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "book")
public class Book implements Serializable {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "isbn", length = 25, nullable = false)
    private String isbn;

    @Column(name = "title", length = 50, nullable = false)
    private String title;

    @Column(name = "author", length = 30, nullable = false)
    private String author;

    @Column(name = "is_shelved", columnDefinition = "boolean NOT NULL DEFAULT false", insertable = false)
    private boolean isShelved;

    @Column(name = "is_deleted", columnDefinition = "boolean NOT NULL DEFAULT false", insertable = false)
    private boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "shelf_id", nullable = true)
    private Shelf shelf;

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

    public boolean isShelved() {
        return isShelved;
    }

    public void setShelved(boolean shelved) {
        isShelved = shelved;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}

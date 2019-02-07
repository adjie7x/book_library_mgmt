package com.mitrais.book_library_mgmt.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shelf")
public class Shelf implements Serializable {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "max_capacity")
    private long maxCapacity;

    @Column(name = "current_capacity")
    private long currentCapacity;


    @OneToMany(mappedBy = "shelf")
    private List<Book> books = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(long maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public long getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(long currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}

package ru.linar.sem.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.annotations.Cascade;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "book_id", unique = true, nullable = false)
    private Long id;

    @Column
    private String name;

    @Column(length = 500)
    private String description;

    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private List<BookChapter> chapters;

    @ManyToMany(mappedBy = "books")
    private List<User> authors;

    @Column
    private Integer chapterCounter;

    @Column
    private Integer lastBookNumber;

    @Column
    private Integer firstBookNumber;
}

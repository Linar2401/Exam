package ru.linar.sem.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "book_chapter",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "book_id"})})
public class BookChapter {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "book_chapter_id", unique = true, nullable = false)
    private Long id;

    @Column
    private String name;

    @Column
    private Byte serialNumber;

    @Column(length = 65535, columnDefinition = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name="book_id", nullable=false)
    private Book book;

    @OneToMany(mappedBy = "chapter", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Comment> comments;
}

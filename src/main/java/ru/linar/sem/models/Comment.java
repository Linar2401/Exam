package ru.linar.sem.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "comment_id", unique = true, nullable = false)
    private Long id;

    @Column(length = 65535, columnDefinition = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name="book_chapter_id", nullable=false)
    @JsonIgnore
    private BookChapter chapter;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @Column(nullable = false, columnDefinition = "timestamp default now()")
    private LocalDateTime createdAt;


}
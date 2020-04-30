package ru.linar.sem.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user",uniqueConstraints = {@UniqueConstraint(columnNames = {"nickname", "email"})})
public class User {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false, unique = true)
    @JsonIgnore
    private String email;

    @NotBlank
    @Column(nullable = false)
    @JsonIgnore
    private String country;

    @Column(nullable = false)
    @JsonIgnore
    private String passwordHash;

    @Column(nullable = false)
    private boolean gender;

    @Column(nullable = false, columnDefinition = "timestamp default now()")
    @JsonIgnore
    private LocalDateTime createdAt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_user",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "book_id") }
    )
    @JsonIgnore
    private List<Book> books;

    @Enumerated(value = EnumType.STRING)
    @JsonIgnore
    private Role role;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Comment> comments;
}

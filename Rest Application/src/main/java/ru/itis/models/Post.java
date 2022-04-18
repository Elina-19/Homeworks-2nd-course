package ru.itis.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Post {

    public enum State{
        DRAFT, PUBLISHED, DELETED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(length = 20)
    private String title;

    @Column(length = 1000)
    private String text;

    @Enumerated(value = EnumType.STRING)
    private State state;

    @OneToOne
    @JoinColumn(name = "photo_id", referencedColumnName = "id")
    private FileInfo photo;
}

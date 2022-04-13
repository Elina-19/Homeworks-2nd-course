package ru.itis.cms_homework.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "text", nullable = false)
    private String text;

    @Column(name = "is_for_admin")
    private Boolean isForAdmin;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account author;

    private String slug;
}

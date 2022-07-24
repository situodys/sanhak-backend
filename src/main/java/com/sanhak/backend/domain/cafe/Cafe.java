package com.sanhak.backend.domain.cafe;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "cafe")
public class Cafe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cafe_id")
    private Long id;
    @Column(name = "cafe_name")
    private String cafeName;
    @Column(name = "category_name")
    private String categoryName;
}

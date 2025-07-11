package com.ra.ss3s.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @Column(name = "category_id", length = 15)
    private String categoryId;

    @Column(name = "category_name", length = 100, nullable = false, unique = true)
    private String categoryName;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}

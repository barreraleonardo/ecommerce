package com.nocountry.ecommerce.domain.model;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SQLDelete(sql = "UPDATE product SET is_available = false WHERE product_id = ?")
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", updatable = true, unique = true, nullable = false)
    private String name;

    @Column(name = "price", updatable = true, nullable = false, precision = 3)
    private Double price;

    @Column(name = "stock", updatable = true, nullable = false)
    private Long stock;

    @Column(name = "is_available", updatable = true)
    private Boolean isAvailable = true;


    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "detail", nullable = false)
    private String detail;

    @Column(name = "image", nullable = false, updatable = true)
    private String image;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @ToString.Exclude
    private Category category;

    @OneToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "mark_id")
    @ToString.Exclude
    private Mark mark;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (!id.equals(product.id)) return false;
        if (!name.equals(product.name)) return false;
        if (!price.equals(product.price)) return false;
        if (!detail.equals(product.detail)) return false;
        if (!image.equals(product.image)) return false;
        if (category != null ? !category.equals(product.category) : product.category != null) return false;
        return mark != null ? mark.equals(product.mark) : product.mark == null;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

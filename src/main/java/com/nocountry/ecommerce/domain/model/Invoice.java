package com.nocountry.ecommerce.domain.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "invoice")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Invoice {


    @Id
    @Column(name = "invoice_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany
    @JoinTable(
            name = "invoice_summary",
            joinColumns =
            @JoinColumn(name = "invoice_id", referencedColumnName = "invoice_id"),
            inverseJoinColumns =
            @JoinColumn(name = "summary_id", referencedColumnName = "summary_id")
    )
    private List<PurchaseSummary> productList;

    @Column(name = "date", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime creationDate;

    @Column(name = "total_price", nullable = false, updatable = false)
    private Float totalPrice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Invoice invoice = (Invoice) o;

        if (!id.equals(invoice.id)) return false;
        if (!user.equals(invoice.user)) return false;
        return productList.equals(invoice.productList);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

package com.nocountry.ecommerce.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Table(name = "summary")
public class PurchaseSummary {

   @Id
   @GeneratedValue(strategy = IDENTITY)
   @Column(name = "summary_id")
   private Long id;

   @Column(name = "amount" ,nullable = false, updatable = false)
   private Integer amount;

   @OneToOne
   @JoinColumn(name = "product_id")
   private Product product;
}

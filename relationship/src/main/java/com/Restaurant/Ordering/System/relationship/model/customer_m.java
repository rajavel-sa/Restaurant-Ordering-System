package com.Restaurant.Ordering.System.relationship.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "a_customer_table")
public class customer_m {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int c_id;

    private String c_name;
}
package com.Restaurant.Ordering.System.relationship.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "a_payment_table")
public class payment_m {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "p_o_id", referencedColumnName = "o_id")
    private order_m order;

    private double p_total;
    private String p_paymentStatus; // Paid, Unpaid
    private LocalDateTime p_timestamp;
}

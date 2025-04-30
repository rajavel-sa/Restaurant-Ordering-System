package com.Restaurant.Ordering.System.relationship.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "a_order_table")
public class order_m {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int o_id;

    @ManyToOne
    @JoinColumn(name = "o_c_id", referencedColumnName = "c_id")
    private customer_m customer;

    private double o_total;
    private String o_status; // Pending, In Progress, Completed
    private String o_paymentStatus; // Paid, Unpaid
    private LocalDateTime o_timestamp;

}

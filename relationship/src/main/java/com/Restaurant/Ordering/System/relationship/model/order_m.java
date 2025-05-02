package com.Restaurant.Ordering.System.relationship.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.*;

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

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<order_items_m> orderItems;

    private int o_total;
    private String o_status;
    private String o_paymentStatus;
    private LocalDateTime o_timestamp;

}

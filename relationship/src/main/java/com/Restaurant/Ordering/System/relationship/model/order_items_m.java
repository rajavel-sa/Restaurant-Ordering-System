package com.Restaurant.Ordering.System.relationship.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "a_order_items_table")
public class order_items_m {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int item_id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private order_m order;

    @ManyToOne
    @JoinColumn(name = "menu_item_id", referencedColumnName = "f_id")
    private menu_m menuItem;

    private int quantity;
    private int item_total;
}
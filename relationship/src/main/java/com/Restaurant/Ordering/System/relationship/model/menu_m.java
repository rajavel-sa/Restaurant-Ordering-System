package com.Restaurant.Ordering.System.relationship.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "a_menu_table")
public class menu_m {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int f_id;

    private String f_name;
    private int f_price;
}

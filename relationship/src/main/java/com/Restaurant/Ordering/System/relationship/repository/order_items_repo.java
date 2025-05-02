package com.Restaurant.Ordering.System.relationship.repository;

import com.Restaurant.Ordering.System.relationship.model.order_items_m;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface order_items_repo extends JpaRepository<order_items_m, Integer> {}
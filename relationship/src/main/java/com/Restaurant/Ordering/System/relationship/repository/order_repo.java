package com.Restaurant.Ordering.System.relationship.repository;

import com.Restaurant.Ordering.System.relationship.model.order_m;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface order_repo extends JpaRepository<order_m, Integer> {
}
package com.Restaurant.Ordering.System.relationship.repository;

import com.Restaurant.Ordering.System.relationship.model.customer_m;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface customer_repo extends JpaRepository<customer_m, Integer> {}
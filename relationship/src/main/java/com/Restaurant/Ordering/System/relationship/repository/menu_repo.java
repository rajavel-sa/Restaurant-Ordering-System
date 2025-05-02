package com.Restaurant.Ordering.System.relationship.repository;

import com.Restaurant.Ordering.System.relationship.model.menu_m;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface menu_repo extends JpaRepository<menu_m, Integer> {}
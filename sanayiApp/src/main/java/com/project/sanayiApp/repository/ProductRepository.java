package com.project.sanayiApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.sanayiApp.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findByUserId(Long userId);

}

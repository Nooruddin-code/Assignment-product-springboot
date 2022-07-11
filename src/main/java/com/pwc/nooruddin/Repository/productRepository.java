package com.pwc.nooruddin.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pwc.nooruddin.Model.product;
@Repository
public interface productRepository extends JpaRepository<product, Integer> {
		Optional<product> findByProductName(String productName);
}

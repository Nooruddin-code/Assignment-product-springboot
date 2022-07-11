package com.pwc.nooruddin.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pwc.nooruddin.Model.catalog;
@Repository
public interface catalogRepository extends JpaRepository<catalog, Integer> {
		Optional<catalog> deleteByCatalogName(String name);
		Optional<catalog> findByCatalogName(String name);
}

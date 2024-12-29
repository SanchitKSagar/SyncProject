package com.repository;

import java.util.HashMap;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.entity.Products;

// Repository Layer directly connected to Database
@Repository
public interface ProductRepo extends JpaRepository<Products, Integer>{

	/**
	 * NOTE: Custom Query to get SSL details, 
	 * uncomment service method only when SSL details needed
	 * @return List<Object> SSL Details
	 */
	@Query(nativeQuery = true,
			value = "SHOW VARIABLES LIKE '%ssl%'")
	List<Object> getSSLDetails();
}

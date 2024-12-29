package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Products;
import com.service.ProductService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;





// Controller Layer
@RestController
@RequestMapping("api/products")
public class ProductController {

	@Autowired
	ProductService service;
	
	@GetMapping("/welcome")
	public String getMethodName() {
		return new String("Welcome to ProductAPI");
	}
	
	@GetMapping("/getall")
	public ResponseEntity<List<Products>> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Object> getProductByID(@PathVariable String id ) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(service.getProdById(Integer.valueOf(id)));
	}
	
	@PostMapping("/create")
	public ResponseEntity<Object> createProd(@RequestBody Products product) throws Exception {
		String response = service.addProduct(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateProduct(@PathVariable String id, @RequestBody Products product) throws Exception {
		product.setPid(Integer.parseInt(id));
		return ResponseEntity.status(HttpStatus.CREATED).body(service.updateProduct(product));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable String id) throws Exception{
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.deleteProduct(Integer.parseInt(id)));
	}
	
	@GetMapping("/parallel")
	public ResponseEntity<String> parallelOperations() {
		service.parallelProcessing();
		return ResponseEntity.status(HttpStatus.OK).body("Parallel Processing executed successfully");
	}
	
	// used to get SSL details
	@GetMapping("/ssldetails")
	public ResponseEntity<Object> getSLDetails() {
		return ResponseEntity.status(HttpStatus.OK).body(service.getSSLDetails());
	}
	
	
}

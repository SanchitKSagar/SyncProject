package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.entity.Products;
import com.exceptions.ExistingResourceFound;
import com.exceptions.ResourceNotFoundException;
import com.repository.ProductRepo;

// Service Layer uses Repository layer to perform Database actions
@Service
public class ProductService {

	// Runtime object creation
	@Autowired
	private ProductRepo repo;
	
	private final ExecutorService exeService = Executors.newFixedThreadPool(4); // new Worker thread 4
	
	// Get all product
	public List<Products> getAll(){
		return repo.findAll();
	}
	
	// Get product with id
	@Cacheable(key = "#id", value = "product")
	public Object getProdById(int id) throws Exception{
		Optional<Products> fetchedProd = repo.findById(id);
		if(fetchedProd.isPresent())
			return fetchedProd.get();
		else
			throw new ResourceNotFoundException(id);
	}
	
	// Add new product
	@CachePut(key = "#p.pid", value="product")
	public String addProduct(Products p) throws Exception{	
		Optional<Products> fetchedProd = repo.findById(p.getPid());
		if(fetchedProd.isPresent())
			throw new ExistingResourceFound(p.getPid());
		repo.save(p);
		return "Product Added successfully";
	}
	
	// Update Product
	@CachePut(key = "#p.pid", value="product")
	public String updateProduct(Products p) {
		Optional<Products> fetchedProd = repo.findById(p.getPid());
		if(!fetchedProd.isPresent())
			throw new ResourceNotFoundException(p.getPid());
		Products prod = fetchedProd.get();
		if(p.getPmanufacturer()==null || p.getPmanufacturer().isEmpty())
			p.setPmanufacturer(prod.getPmanufacturer());
		if(p.getPname()==null || p.getPname().isEmpty())
			p.setPname(prod.getPname());
		if(p.getPrice()==0.00)
			p.setPrice(prod.getPrice());
		repo.saveAndFlush(p);
		return "Product updated successfully";
	}
	
	//Delete Product
	@CacheEvict(key = "#pid", value = "product")
	public String deleteProduct(int pid) {
		Optional<Products> fetchedProd = repo.findById(pid);
		if(fetchedProd.isPresent()) {
			repo.deleteById(pid);
			return "Delete successful";
		}
		throw new ResourceNotFoundException(pid);
	}
	
	
	/**
	 * performing getAll and getbyID method parallely
	 */
	public void parallelProcessing() {
		// Executable Task 
		List<Callable<Void>> tasks = new ArrayList<>();
		
		//1st task
		tasks.add(() ->{
			getAll();
			System.out.println("GetAll Parallel");
			return null;
		});
		
		//2nd task
		tasks.add(() ->{
			getProdById(1);
			System.out.println("getBy ID 1 parallel");
			return null;
		});
		
		try {
			List<Future<Void>> result = exeService.invokeAll(tasks);
			for(Future<Void> res:result) {
				res.get();
			}
		}
		catch(Exception e ) {
			System.out.println(e.getMessage());
		}
		finally {
			exeService.shutdown();
		}
	}
	
	
	
	/**
	 * NOTE: Custom Query to get SSL details, 
	 * uncomment service method only when SSL details needed
	 * @return SSl Details
	 */
	
	public List<Object> getSSLDetails(){
		return new ArrayList<Object>();
//		return repo.getSSLDetails();
	}
	
	
	
	
}

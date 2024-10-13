package com.product.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.product.model.Product;
import com.product.repository.ProductRepo;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/product")

public class ProductController {

////injecting [name of class like to inject ] + [reference variable];
	@Autowired
	ProductRepo productRepo;

	// get all Products API
	@GetMapping("/prd")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> products = productRepo.findAll();
		return ResponseEntity.ok(products);
	}

//---------------------------------------------------------------- 17:50
	// Save Products API //25:00
	@PostMapping("/prd") // saving all methods in database
	public ResponseEntity<Product> saveProduct(@RequestBody Product product) {

		try {
			Product saveProduct = productRepo.save(product);
			return ResponseEntity.status(HttpStatus.CREATED).body(saveProduct);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	// get single Product by ID API
	@GetMapping("/prd/{id}")

	public ResponseEntity<Product> getemployeeById(@PathVariable Long id) {
		Optional<Product> optionalProduct = productRepo.findById(id);
		return optionalProduct.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

	}

	// update Product by ID API

	@PutMapping("/prd/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updateProduct) {
		Optional<Product> optionalProduct = productRepo.findById(id);
		if (optionalProduct.isPresent()) {
			Product product = optionalProduct.get();
			product.setProductname(updateProduct.getProductname());
			product.setProductdescription(updateProduct.getProductdescription());
			product.setPrice(updateProduct.getPrice());
			return ResponseEntity.ok(productRepo.save(product));

		} else {
			return ResponseEntity.notFound().build();
		}

	}

	// delete Product by ID
	@DeleteMapping("/prd/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
		Optional<Product> optionalProduct = productRepo.findById(id);
		if (optionalProduct.isPresent()) {
			productRepo.deleteById(id);
			return ResponseEntity.noContent().build();

		} else {
			return ResponseEntity.notFound().build();
		}

	}

}

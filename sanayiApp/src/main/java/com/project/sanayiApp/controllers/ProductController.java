package com.project.sanayiApp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.sanayiApp.business.abstracts.ProductService;
import com.project.sanayiApp.business.requests.ProductCreateRequest;
import com.project.sanayiApp.business.requests.ProductUpdateRequest;
import com.project.sanayiApp.business.responses.ProductResponse;
import com.project.sanayiApp.entities.Product;
import com.project.sanayiApp.repository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	ProductService productService;

	public ProductController(ProductService serviceService) {
		this.productService = serviceService;
	}
	
	@GetMapping
	public List<ProductResponse> getAllProducts(@RequestParam Optional<Long> userId){
		return productService.getAllProducts(userId);
	}
	
	@PostMapping
	public Product createProduct(@RequestBody ProductCreateRequest newProductRequest) {
		return productService.createProduct(newProductRequest);
	}
	
	@GetMapping("/{productId}")
	public ProductResponse getProductById(@PathVariable Long productId) {
		return productService.findProductById(productId);
	}
	
	@PutMapping("/{serviceId}")
	public Product updateProduct(@PathVariable Long productId, @RequestBody ProductUpdateRequest updateRequest) {

		return productService.updateProduct(productId, updateRequest);
	}
	
	@DeleteMapping
	public void deleteProduct(@PathVariable Long productId) {
		productService.deleteById(productId);
	}
	
}

package com.project.sanayiApp.business.abstracts;

import java.util.List;
import java.util.Optional;

import com.project.sanayiApp.business.requests.ProductCreateRequest;
import com.project.sanayiApp.business.requests.ProductUpdateRequest;
import com.project.sanayiApp.business.responses.ProductResponse;
import com.project.sanayiApp.entities.Product;

public interface ProductService {
	
	public List<ProductResponse> getAllProducts(Optional<Long> userId);
	public Product createProduct(ProductCreateRequest newProductRequest);
	public ProductResponse findProductById(Long productId);
	public Product updateProduct(Long productId, ProductUpdateRequest updateRequest); 
	public void deleteById(Long productId);
	
	
}

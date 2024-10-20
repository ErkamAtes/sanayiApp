package com.project.sanayiApp.business.concretes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.sanayiApp.business.abstracts.ProductService;
import com.project.sanayiApp.business.abstracts.UserService;
import com.project.sanayiApp.business.requests.ProductCreateRequest;
import com.project.sanayiApp.business.requests.ProductUpdateRequest;
import com.project.sanayiApp.business.responses.ProductResponse;
import com.project.sanayiApp.entities.Product;
import com.project.sanayiApp.entities.User;
import com.project.sanayiApp.repository.ProductRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductManager implements ProductService{
	
	private ProductRepository productRepository;
	private UserService userService;
	
	
	@Override
	public List<ProductResponse> getAllProducts(Optional<Long> userId) {
		List<Product> list;
		if(userId.isPresent()) {
			list = productRepository.findByUserId(userId.get());
		}
		list =  productRepository.findAll();
		return list.stream().map(p -> new ProductResponse(p)).collect(Collectors.toList());
	}

	@Override
	public Product createProduct(ProductCreateRequest newProductRequest) {
		// TODO Auto-generated method stub
		User user = userService.getUserById(newProductRequest.getId());
		if(user == null)
			return null;
		Product toSave = new Product();
		toSave.setId(newProductRequest.getId());
		toSave.setName(newProductRequest.getName());
		toSave.setProductPrice(newProductRequest.getServicePrice());
		toSave.setText(newProductRequest.getText());
		toSave.setUser(user);
		return productRepository.save(toSave);
	}

	@Override
	public ProductResponse findProductById(Long productId) {
		Product product = productRepository.findById(productId).orElse(null);
		ProductResponse productResponse = new ProductResponse(product);
		// TODO Auto-generated method stub
		return productResponse;
	}

	@Override
	public Product updateProduct(Long productId, ProductUpdateRequest updateRequest) {
		// TODO Auto-generated method stub
		Optional<Product> product = productRepository.findById(productId);
		if(product.isPresent()) {
			Product toUpdate = product.get();
			toUpdate.setName(updateRequest.getName());
			toUpdate.setProductPrice(updateRequest.getServicePrice());
			toUpdate.setText(updateRequest.getText());
			productRepository.save(toUpdate);
			return toUpdate;
		}
		return null;
	}

	@Override
	public void deleteById(Long serviceId) {
		// TODO Auto-generated method stub
		productRepository.deleteById(serviceId);
	}



}

package com.project.sanayiApp.business.responses;

import com.project.sanayiApp.entities.Product;

import lombok.Data;

@Data
public class ProductResponse {
	
	
	Long id;
	Long userId;
	String userName;
	String name;
	Double productPrice;
	String text;
	
	
	public ProductResponse(Product entity) {
		this.id = entity.getId();
		this.userId = entity.getUser().getId();
		this.userName = entity.getUser().getUserName();
		this.name = entity.getName();
		this.productPrice = entity.getProductPrice();
		this.text = entity.getText();
	}
}

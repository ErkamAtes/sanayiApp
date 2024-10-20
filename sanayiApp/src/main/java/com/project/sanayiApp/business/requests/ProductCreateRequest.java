package com.project.sanayiApp.business.requests;

import lombok.Data;

@Data
public class ProductCreateRequest {
	Long id;
	String name;
	Double servicePrice;
	String text;
}

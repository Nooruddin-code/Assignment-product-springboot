package com.pwc.nooruddin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.FileAlreadyExistsException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.pwc.nooruddin.CustomExceptions.productNotFoundException;
import com.pwc.nooruddin.Model.product;

@SpringBootTest
class ProductServiceAaignmentApplicationTests {


	
	@Test
	void CreateProductTest() throws FileAlreadyExistsException{
		assertEquals(new product(1,"iphone",30000,"mobile") , new product(1,"iphone",30000,"mobile"));
	}


	@Test
	void UpdateProduct() throws productNotFoundException {

	}
	@Test
	void DeleteProduct() {

	}

	@Test
	void getAllProducts() {

	}
}

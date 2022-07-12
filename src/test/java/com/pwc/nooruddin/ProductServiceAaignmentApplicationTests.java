package com.pwc.nooruddin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.FileAlreadyExistsException;
import java.util.List;

import javax.naming.directory.NoSuchAttributeException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pwc.nooruddin.CustomExceptions.productNotFoundException;
import com.pwc.nooruddin.DAO.productENDModel;
import com.pwc.nooruddin.Model.product;
import com.pwc.nooruddin.Repository.productRepository;
import com.pwc.nooruddin.services.productServices;

@SpringBootTest
class ProductServiceAaignmentApplicationTests {
	
	@Autowired
	private productServices productServices;
	
	@Autowired
	private productRepository productRepository;
	
	@Test
	void CreateProductTest() {
		product createProduct=null;
		try {
			createProduct = this.productServices.createProduct(new productENDModel("iphone", 0, "mobile"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(new product(1,"iphone",0,"mobile") , createProduct);
	}


	@Test
	void UpdateProduct() throws FileAlreadyExistsException, Exception {
		product updateProduct=null;
		try {
			updateProduct = this.productServices.UpdateProduct(new productENDModel("iphone", 0, "laptop"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(new productNotFoundException(null).getMessage(), updateProduct);
	}
	
	@Test
	void DeleteProduct() throws NoSuchAttributeException {
		List<product> allProducts = this.productServices.getAllProducts();
		assertEquals(this.productRepository.findAll(), allProducts);
	}
}

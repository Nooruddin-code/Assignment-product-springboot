package com.pwc.nooruddin.Controllers;

import java.util.List;

import javax.naming.directory.NoSuchAttributeException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pwc.nooruddin.CustomExceptions.productNotFoundException;
import com.pwc.nooruddin.DAO.productENDModel;
import com.pwc.nooruddin.Model.product;
import com.pwc.nooruddin.services.productServices;

@RestController
@RequestMapping("/noor/pwc")
public class productController {

	@Autowired
	private productServices services;

	@PostMapping("/EnroleNewProduct")
	public ResponseEntity<?> AddNewProduct(@RequestBody productENDModel product) throws Exception{
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.services.createProduct(product));
	}

	@GetMapping("/ListOfProducts")
	public ResponseEntity<List<product>> ListOfProducts() throws NoSuchAttributeException{
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.services.getAllProducts());
	}

	@PutMapping("/ModifyProduct")
	public ResponseEntity<?> UpdateProduct(@RequestBody productENDModel product) throws productNotFoundException{
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.services.UpdateProduct(product));
	}

	@DeleteMapping("/DeleteProduct/{productName}/{catalogName}")
	public ResponseEntity<?> DeleteProduct(@PathVariable String productName) throws NoSuchAttributeException{
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.services.DeleteProduct(productName));
	}
}

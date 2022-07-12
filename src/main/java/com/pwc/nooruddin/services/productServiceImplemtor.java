package com.pwc.nooruddin.services;

import java.nio.file.FileAlreadyExistsException;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

import javax.naming.directory.NoSuchAttributeException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;

import com.pwc.nooruddin.CustomExceptions.productNotFoundException;
import com.pwc.nooruddin.DAO.productENDModel;
import com.pwc.nooruddin.Model.catalog;
import com.pwc.nooruddin.Model.product;
import com.pwc.nooruddin.Repository.catalogRepository;
import com.pwc.nooruddin.Repository.productRepository;
@Service
public class productServiceImplemtor implements productServices {

	@Autowired
	private productRepository productRepository;

	@Autowired
	private catalogRepository catalogRepository;

	@Override
	public List<product> getAllProducts() throws NoSuchAttributeException {
		List<product> results = this.productRepository.findAll();
		if(results.size() <=0 ) {
			throw new NoSuchAttributeException("No products Listed");
		}
		return results;
	}

	@Override
	public product UpdateProduct(productENDModel product) throws productNotFoundException,IncorrectResultSizeDataAccessException{
		Optional<product> productName = this.productRepository.findByProductName(product.getProductName());
		Optional<catalog> findByCatalogName = this.catalogRepository.findByCatalogName(product.getCatalogName());
		if(productName.isPresent() && findByCatalogName.isPresent()) {
			productName.get().setProductName(product.getProductName());
			productName.get().setProductPrice(product.getProductPrice());
			productName.get().setCategoryName(product.getCatalogName());
			findByCatalogName.get().setCatalogName(product.getCatalogName());
			return this.productRepository.save(productName.get());
			}
		throw new InputMismatchException("product doesn't exists in DB please create the product first");
	}

	@Override
	public String DeleteProduct(String productName) throws NoSuchAttributeException{
		Optional<product> deleteByProductName = this.productRepository.findByProductName(productName);
		if(deleteByProductName.isPresent()) {
			this.productRepository.deleteById(deleteByProductName.get().getId());
			return deleteByProductName.get().getProductName()+" Deleted";
		}
		throw new NoSuchAttributeException("product Not Listed/Registered");
	}

	@Override
	public product createProduct(productENDModel product) throws FileAlreadyExistsException {
		Optional<product> productName = this.productRepository.findByProductName(product.getProductName());
		Optional<catalog> findByCatalogName = this.catalogRepository.findByCatalogName(product.getCatalogName());
		if(!productName.isPresent() && !findByCatalogName.isPresent()) {
			this.catalogRepository.save(new catalog(0, product.getCatalogName(), new HashSet<>(List.of(new product(0, product.getProductName(), product.getProductPrice(), product.getCatalogName())))));
			return this.productRepository.findByProductName(product.getProductName()).get();
		}
		throw new FileAlreadyExistsException("product is Already Exist");
	}

}

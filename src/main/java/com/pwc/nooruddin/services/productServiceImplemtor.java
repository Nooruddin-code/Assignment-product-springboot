package com.pwc.nooruddin.services;

import java.nio.file.FileAlreadyExistsException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.naming.directory.NoSuchAttributeException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;

import com.pwc.nooruddin.CustomExceptions.productNotFoundException;
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
	public product createProduct(product product) throws FileAlreadyExistsException,IncorrectResultSizeDataAccessException {
		Optional<product> productName = this.productRepository.findByProductName(product.getProductName());
		if(!productName.isPresent()) {
			this.catalogRepository.save(new catalog(0,product.getCategoryName(),new HashSet<>(List.of( product))));
			return product;
		}
		throw new FileAlreadyExistsException("product is Already Exist");
	}

	@Override
	public catalog UpdateProduct(product product) throws productNotFoundException,IncorrectResultSizeDataAccessException{
		Optional<catalog> findByCatalogName = this.catalogRepository.findByCatalogName(product.getCategoryName());
		Optional<com.pwc.nooruddin.Model.product> findByProductName = this.productRepository.findByProductName(product.getProductName());
		if(findByProductName.isPresent()) {
			findByCatalogName.get().setCatalogName(product.getCategoryName());
			findByCatalogName.get().setProduct(new HashSet<>(List.of(product)));
			findByProductName.get().setCategoryName(product.getCategoryName());
			findByProductName.get().setProductName(product.getProductName());
			findByProductName.get().setProductPrice(product.getProductPrice());			
			return this.catalogRepository.save(findByCatalogName.get());
		}
		 throw new productNotFoundException("product not found to update please provide proper product name");
	}

	@Override
	public String DeleteProduct(String productName) throws NoSuchAttributeException{
		Optional<product> deleteByProductName = this.productRepository.findByProductName(productName);
		if(deleteByProductName.isPresent()) {
			this.productRepository.deleteById(deleteByProductName.get().getId());
			this.catalogRepository.deleteByCatalogName(deleteByProductName.get().getCategoryName());
			return productName+" Deleted";
		}
		throw new NoSuchAttributeException("product Not Listed/Registered");
	}

}

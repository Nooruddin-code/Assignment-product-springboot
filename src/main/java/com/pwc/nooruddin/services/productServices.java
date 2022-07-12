package com.pwc.nooruddin.services;

import java.nio.file.FileAlreadyExistsException;
import java.util.List;

import javax.naming.directory.NoSuchAttributeException;

import com.pwc.nooruddin.CustomExceptions.productNotFoundException;
import com.pwc.nooruddin.DAO.productENDModel;
import com.pwc.nooruddin.Model.product;

public interface productServices {
	List<product> getAllProducts()throws NoSuchAttributeException;
	product createProduct(productENDModel product)throws FileAlreadyExistsException, Exception;
	product UpdateProduct(productENDModel product) throws productNotFoundException;
	String DeleteProduct(String productName) throws NoSuchAttributeException;
}

package edu.miu.cs545.waa_project.service;

import edu.miu.cs545.waa_project.domain.Product;
import edu.miu.cs545.waa_project.domain.Seller;

import java.util.List;


public interface ProductService {
	
	List<Product> getAll();

	void save(Product product);

	Product find(Long id);

	void delete(Product product);

	List<Product> getProductsBySeller(Seller seller);
	
}

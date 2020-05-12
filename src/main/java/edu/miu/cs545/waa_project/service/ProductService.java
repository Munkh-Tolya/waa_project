package edu.miu.cs545.waa_project.service;

import edu.miu.cs545.waa_project.domain.Product;
import edu.miu.cs545.waa_project.domain.Seller;

import java.util.List;


public interface ProductService {
	
	public List<Product> getAll();

	public void save(Product product);

	public Product find(Long id);

	List<Product> getProductsBySeller(Seller seller);
	
}

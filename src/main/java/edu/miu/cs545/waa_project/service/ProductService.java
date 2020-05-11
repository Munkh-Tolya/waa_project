package edu.miu.cs545.waa_project.service;

import edu.miu.cs545.waa_project.domain.Product;
import java.util.List;


public interface ProductService {
	
	public List<Product> getAll();

	public Product save(Product product);

	public Product find(Long id);
	
}

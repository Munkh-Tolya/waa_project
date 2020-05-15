package edu.miu.cs545.waa_project.service;

import edu.miu.cs545.waa_project.domain.Category;
import edu.miu.cs545.waa_project.domain.Product;
import edu.miu.cs545.waa_project.domain.Seller;
import edu.miu.cs545.waa_project.exception.ProductNotFoundException;
import edu.miu.cs545.waa_project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;
	
	@Override
	public List<Product> getAll() {
		return (List<Product>) productRepository.findAll();
	}

	@Override
	public List<Product> getByCategory(Integer categoryId) {
		return productRepository.findProductsByCategory(categoryId);
	}

	@Override
	public void save(Product product) {
		productRepository.save(product);
	}

	@Override
	public Product find(Long id) {
		return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id.toString()));
	}

	@Override
	public void delete(Product product) {
		productRepository.delete(product);
	}

	@Override
	public List<Product> getProductsBySeller(Seller seller) {
		return productRepository.findProductsBySeller(seller);
	}

}

package edu.miu.cs545.waa_project.service;

import edu.miu.cs545.waa_project.domain.Buyer;
import edu.miu.cs545.waa_project.domain.Product;
import edu.miu.cs545.waa_project.domain.ProductReview;
import edu.miu.cs545.waa_project.repository.ProductRepository;
import edu.miu.cs545.waa_project.repository.ProductReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductReviewServiceImpl implements ProductReviewService {
    @Autowired
    ProductReviewRepository productReviewRepository;
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;

    @Override
    public List<ProductReview> getAll() {
        return (List<ProductReview>) productReviewRepository.findAll();
    }

    @Override
    public ProductReview save(ProductReview productReview) {
        return productReviewRepository.save(productReview);
    }

    @Override
    public ProductReview find(Long id) {
        return productReviewRepository.findById(id).get();
    }

    @Override
    public ProductReview saveReviewToProduct(ProductReview productReview, Long productId){
        Product product = productService.find(productId);
        if(product != null){
            Buyer buyer = userService.getAuthenticatedBuyer();
            productReview.setEnabled(false);
            productReview.setCreateDate(new Date());
            productReview.setBuyer(buyer);
            product.addReview(productReview);
            productService.save(product);
            return productReview;
        }else{
            throw new NullPointerException("Product is not found.");
        }
    }
}

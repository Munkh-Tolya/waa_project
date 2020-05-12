package edu.miu.cs545.waa_project.service;

import edu.miu.cs545.waa_project.domain.ProductReview;

import java.util.List;

public interface ProductReviewService {
    public List<ProductReview> getAll();
    public ProductReview save(ProductReview product);
    public ProductReview find(Long id);
}

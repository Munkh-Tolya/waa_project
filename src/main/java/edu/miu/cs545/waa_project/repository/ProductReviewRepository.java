package edu.miu.cs545.waa_project.repository;

import edu.miu.cs545.waa_project.domain.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {

}

package edu.miu.cs545.waa_project.service;


import edu.miu.cs545.waa_project.domain.Item;
import edu.miu.cs545.waa_project.domain.Product;

public interface ItemService {

    // Find item by product
    Item findTopByProduct(Product product);

    void deleteItemById(Long productId);
}

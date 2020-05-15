package edu.miu.cs545.waa_project.service;

import edu.miu.cs545.waa_project.domain.Item;
import edu.miu.cs545.waa_project.domain.Product;
import edu.miu.cs545.waa_project.repository.ItemRepository;
import edu.miu.cs545.waa_project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService{
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Item findTopByProduct(Product product) {
        return itemRepository.findTopByProduct(product);
    }

    @Override
    public void deleteItemById(Long itemId) {
        Item item = itemRepository.findById(itemId).get();
        Product product = item.getProduct();
        product.setQuantity(product.getQuantity() + item.getQuantity());
        productRepository.save(product);
        itemRepository.deleteById(itemId);
    }
}

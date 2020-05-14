package edu.miu.cs545.waa_project.service;

import edu.miu.cs545.waa_project.domain.Item;
import edu.miu.cs545.waa_project.domain.Product;
import edu.miu.cs545.waa_project.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService{
    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Item findTopByProduct(Product product) {
        return itemRepository.findTopByProduct(product);
    }

    @Override
    public void deleteItemById(Long productId) {
        itemRepository.deleteById(productId);
    }
}

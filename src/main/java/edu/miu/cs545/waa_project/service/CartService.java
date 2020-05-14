package edu.miu.cs545.waa_project.service;

import edu.miu.cs545.waa_project.domain.Item;
import edu.miu.cs545.waa_project.domain.dto.ResponseDTO;
import org.springframework.ui.Model;

import java.util.List;

public interface CartService {
    ResponseDTO addItem(Long productId, int quantity);
    Model getCartItems(Model model);
}

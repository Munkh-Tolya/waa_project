package edu.miu.cs545.waa_project.service;

import edu.miu.cs545.waa_project.domain.Buyer;
import edu.miu.cs545.waa_project.domain.Item;
import edu.miu.cs545.waa_project.domain.Product;
import edu.miu.cs545.waa_project.domain.Seller;
import edu.miu.cs545.waa_project.domain.dto.CartSellerDTO;
import edu.miu.cs545.waa_project.domain.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService{
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    @Autowired
    ItemService itemService;

    @Override
    public ResponseDTO addItem(Long productId, int quantity) {
        ResponseDTO response = new ResponseDTO();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Buyer buyer = (Buyer)userService.findByEmail("buyer@miu.edu");
        Product product = productService.find(productId);
        if(product == null || product.getQuantity() < quantity){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Sorry, Product is out of stock");
        }else{
            List<Item> items = buyer.getCardItems();
            Item item = items.stream().filter(p -> p.getProduct().getId() == productId).findAny().orElse(null);
            if(item == null) {
                System.out.println("added new item");
                buyer.addCardItem(new Item(product,quantity));
            }else {
                System.out.println("by this way");
                item.setQuantity(item.getQuantity() + quantity);
                item.setPrice(item.getPrice() + quantity * product.getPrice());
            }
            product.setQuantity(product.getQuantity() - quantity);

            response.setMessage("Product " + product.getName() + "has been added to cart.");
            response.setCardSize(buyer.getCardItems().size());

            productService.save(product);
            userService.save(buyer);
            return response;
        }
    }

    @Override
    public Model getCartItems(Model model) {
        Buyer buyer = userService.getAuthenticatedBuyer();
        List<Item> items = buyer.getCardItems();
        List<CartSellerDTO> cartDTOS = new ArrayList<>();
        for(Item i:items){
            Seller seller = i.getProduct().getSeller();
            if(seller != null){
                CartSellerDTO cartSellerDTO = cartDTOS.stream()
                        .filter(dto -> dto.getSellerId() == seller.getId())
                        .findAny()
                        .orElse(null);
                if(cartSellerDTO != null){
                    cartSellerDTO.addItem(i);
                }else{
                    cartSellerDTO = new CartSellerDTO(seller.getFirstName(), seller.getId());
                    cartSellerDTO.addItem(i);
                    cartDTOS.add(cartSellerDTO);
                }
            }
        }
        model.addAttribute("cartDTOS",cartDTOS);
        model.addAttribute("grandTotal",0);
        return model;
    }
}

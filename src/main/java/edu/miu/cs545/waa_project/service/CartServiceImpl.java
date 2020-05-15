package edu.miu.cs545.waa_project.service;

import edu.miu.cs545.waa_project.domain.Buyer;
import edu.miu.cs545.waa_project.domain.Item;
import edu.miu.cs545.waa_project.domain.Product;
import edu.miu.cs545.waa_project.domain.Seller;
import edu.miu.cs545.waa_project.domain.dto.CartSellerDTO;
import edu.miu.cs545.waa_project.domain.dto.ResponseDTO;
import edu.miu.cs545.waa_project.repository.ItemRepository;
import edu.miu.cs545.waa_project.repository.ProductRepository;
import edu.miu.cs545.waa_project.repository.UserRepository;
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
    @Autowired
    ItemRepository itemRepository;

    @Override
    public ResponseDTO addItem(Long productId, int quantity) {
        ResponseDTO response = new ResponseDTO();
        Buyer buyer = userService.getAuthenticatedBuyer();
        Product product = productService.find(productId);
        if(product == null || product.getQuantity() < quantity){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Sorry, Product is out of stock");
        }else{
            List<Item> items = buyer.getCardItems();
            Item item = items.stream().filter(p -> p.getProduct().getId() == productId).findAny().orElse(null);
            if(item == null) {
                buyer.addCardItem(new Item(product,quantity));
            }else {
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
        if(buyer.getCoupon() > 0){
            model.addAttribute("hasCoupon", true);
        }
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
    @Override
    public Model getCheckOutSummary(Model model, Long sellerId, String coupon) {
        List<Item> items = this.getCartItemsBySeller(sellerId);
        double subTotal = 0.0;
        double grandTotal = 0.0;
        for(Item i:items){
            subTotal += i.getPrice();
        }
        grandTotal = subTotal;
        if(coupon != null){
            if(userService.buyerHasCoupon()){
                double discount = subTotal * 0.05;
                grandTotal = subTotal - discount;
                model.addAttribute("discount",discount);
            }
        }
        model.addAttribute("subTotal",subTotal);
        model.addAttribute("grandTotal",grandTotal);
        model.addAttribute("items",items);
        model.addAttribute("sellerId",sellerId);
        return model;
    }

    @Override
    public List<Item> getCartItemsBySeller(Long sellerId) {
        Buyer buyer = userService.getAuthenticatedBuyer();
        return itemRepository.getItemsBySellerId(buyer.getId(),sellerId);
    }
}

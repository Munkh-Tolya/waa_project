package edu.miu.cs545.waa_project.Interceptor;

import edu.miu.cs545.waa_project.domain.Buyer;
import edu.miu.cs545.waa_project.domain.Seller;
import edu.miu.cs545.waa_project.domain.User;
import edu.miu.cs545.waa_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Component
public class Interceptor implements HandlerInterceptor {

   @Autowired
   private UserService userService;

   @Override
   public void postHandle(
      HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
      Principal principal = request.getUserPrincipal();
      if(request.isUserInRole("ROLE_BUYER") && principal != null && modelAndView != null){
         User user = userService.findByEmail(principal.getName());
         if(user != null){
            Buyer buyer = (Buyer)user;
            modelAndView.getModelMap().addAttribute("shoppingCart",buyer.getCardItems());
         }
      }
   }

}
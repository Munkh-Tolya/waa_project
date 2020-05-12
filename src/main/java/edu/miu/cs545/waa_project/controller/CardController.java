package edu.miu.cs545.waa_project.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/card")
public class CardController {

//    @PostMapping(value = "/", produces = "application/json")
//    return new ResponseEntity<String>("Thank you for submitting feedback", HttpStatus.OK);
    @PostMapping(value = "/",consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public @ResponseBody String saveCardItem(@RequestParam Map<String,String> params) {
        return params.get("productId") + params.get("quantity") + "gg";
    }
}

package edu.miu.cs545.waa_project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Product has been ordered by customer.")
public class ProductAlreadyOrderedForDeletion extends RuntimeException{

    public String getFullMessage(){
        return " Product has been ordered by customer.";
    }

}

package edu.miu.cs545.waa_project.service;

import edu.miu.cs545.waa_project.domain.dto.ResponseDTO;

public interface CartService {
    ResponseDTO addItem(Long productId, int quantity );
}

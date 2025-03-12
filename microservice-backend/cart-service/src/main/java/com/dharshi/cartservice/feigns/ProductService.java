package com.dharshi.cartservice.feigns;

import com.dharshi.cartservice.dtos.ApiResponseDto;
import com.dharshi.cartservice.dtos.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "api-gateway", url = "http://api-gateway:8080", contextId = "productServiceFeignClient1")
public interface ProductService {

    @GetMapping("/product-service/product/get/byId")
    ResponseEntity<ApiResponseDto<ProductDto>> getProductById(@RequestParam String id);

}

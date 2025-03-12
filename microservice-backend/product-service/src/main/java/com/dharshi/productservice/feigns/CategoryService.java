package com.dharshi.productservice.feigns;

import com.dharshi.productservice.dtos.ApiResponseDto;
import com.dharshi.productservice.dtos.CategoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "api-gateway", url = "http://api-gateway:8080", contextId = "authServiceFeignClient9")
public interface CategoryService {

    @GetMapping("/category-service/category/get/byId")
    ResponseEntity<ApiResponseDto<CategoryDto>> getCategoryById(@RequestParam String id);

}

package com.dharshi.authservice.feigns;


import com.dharshi.authservice.dtos.ApiResponseDto;
import com.dharshi.authservice.dtos.MailRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "api-gateway", url = "http://api-gateway:8080", contextId = "authServiceFeignClient13")
public interface NotificationService {

    @PostMapping("/notification-service/notification/send")
    ResponseEntity<ApiResponseDto<?>> sendEmail(@RequestBody MailRequestDto requestDto);
}

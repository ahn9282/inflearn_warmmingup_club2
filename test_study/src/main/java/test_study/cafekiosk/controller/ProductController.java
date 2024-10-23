package test_study.cafekiosk.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import test_study.cafekiosk.api.ApiResponse;
import test_study.cafekiosk.dto.ProductResponse;
import test_study.cafekiosk.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;


    @PostMapping("/new")
    public ApiResponse<ProductResponse> createProduct(@Valid @RequestBody ProductCreateRequest request){
       return  ApiResponse.of(HttpStatus.OK, productService.createProduct(request));
    }

    @GetMapping("/selling")
    public ApiResponse<List<ProductResponse>> getSellingProducts(){
        return  ApiResponse.ok(productService.getSellingProducts());
    }
}

package com.devd.spring.bookstorecatalogservice.controller;

import com.devd.spring.bookstorecatalogservice.dto.CreateProductRequest;
import com.devd.spring.bookstorecatalogservice.dto.UpdateProductCategoryRequest;
import com.devd.spring.bookstorecatalogservice.dto.UpdateProductRequest;
import com.devd.spring.bookstorecatalogservice.model.Product;
import com.devd.spring.bookstorecatalogservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

/**
 * @author: Devaraj Reddy,
 * Date : 2019-06-06
 */
@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/product")
    public ResponseEntity<?> createProduct(@RequestBody @Valid CreateProductRequest createProductRequest){

        String product = productService.createProduct(createProductRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{productId}")
                .buildAndExpand(product).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable("productId") String productId) {

        Product product = productService.getProduct(productId);

        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<?> deleteProductCategory(@PathVariable("productId") String productId) {

        productService.deleteProduct(productId);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/product")
    public ResponseEntity<?> updateProductCategory(@RequestBody @Valid UpdateProductRequest updateProductRequest) {

        productService.updateProduct(updateProductRequest);

        return ResponseEntity.noContent().build();
    }

}

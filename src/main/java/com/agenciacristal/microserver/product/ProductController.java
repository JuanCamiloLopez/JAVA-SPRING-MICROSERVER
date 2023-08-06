package com.agenciacristal.microserver.product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
@RestController
@RequestMapping(path = "api/v1/products")
public class ProductController {
    private final ProductServer productServer;

    public ProductController(ProductServer productServer) {
        this.productServer = productServer;
    }

    @GetMapping
    public List<Product> getProducts(){
        return productServer.getProducts();
    }

    @PostMapping
    public ResponseEntity<Object> registrarProduct(@RequestBody Product product){
        return productServer.newProduct(product);

    }

    @PutMapping
    public ResponseEntity<Object> actualizarProduct(@RequestBody Product product){
        return productServer.updateProduct(product);

    }

    @DeleteMapping(path = "{ProductId}")
    public ResponseEntity<Object> eliminarProducto(@PathVariable("ProductId") Long id){
        return productServer.deleteProduct(id);
    }

}

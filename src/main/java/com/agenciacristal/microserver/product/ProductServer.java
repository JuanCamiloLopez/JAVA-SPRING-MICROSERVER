package com.agenciacristal.microserver.product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@Service
public class ProductServer {

    private final ProductRepository productRepository;

    public ProductServer(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    public ResponseEntity<Object> newProduct(Product product) {

        Optional<Product> res = productRepository.findProductByName(product.getName());
        HashMap<String, Object> datos = new HashMap<>();

        if (res.isPresent()){
            datos.put("Error", true);
            datos.put("message","Ya existe un producto con ese nombre");
            return new ResponseEntity<>(datos, HttpStatus.CONFLICT);
        }
        productRepository.save(product);
        datos.put("data", product);
        datos.put("message","Se guardo con exito");
        return new ResponseEntity<>(datos, HttpStatus.CREATED);
    }

    public ResponseEntity<Object> updateProduct(Product product){
        Optional<Product> res = productRepository.findById(product.getId());
        HashMap<String, Object> datos = new HashMap<>();
        if(res.isPresent()){
            productRepository.save(product);
            datos.put("data", product);
            datos.put("message","Se ACTUALIZO con exito");
            return new ResponseEntity<>(datos, HttpStatus.CREATED);
        }
        datos.put("Error", true);
        datos.put("message","NO EXISTE UN PRODUCTO CON TAL ID, DEBES CREARLO");
        return new ResponseEntity<>(datos, HttpStatus.CONFLICT);
    }

    public ResponseEntity<Object> deleteProduct(Long id){
        boolean exsiste = productRepository.existsById(id);
        HashMap<String, Object> datos = new HashMap<>();
        if (!exsiste){
            datos.put("message","No se encontro un producto con tal id");
            return new ResponseEntity<>(datos, HttpStatus.CONFLICT);
        }
        productRepository.deleteById(id);
        datos.put("mesaje de eliminacion", "se elimino correctamente el producto");
        return new ResponseEntity<>(datos, HttpStatus.OK);

    }
}

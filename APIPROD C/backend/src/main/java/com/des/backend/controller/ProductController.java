package com.des.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.des.backend.exception.ResourceNotFoundException;
import com.des.backend.model.Product;
import com.des.backend.repository.ProductRepository;

import jakarta.validation.Valid;

/**
 * @RestController combina @Controller + @ResponseBody: Transforma
 *                 automaticamente o retorno dos metodos em json
 */
@RestController
/**
 * @RequestMapping define o prefixo da rota de todos os endpoints
 *                 deste controler
 */
@RequestMapping("/products")
public class ProductController {

    // Pelo MVC simples o controller acessa direto o repository
    private final ProductRepository productRepository;

    // Injeção de dependencia via construtor
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // @GetMapping mapeia requisições GET para /products
    @GetMapping
    public List<Product> getAll() {
        // findAll() ja devolve a lista pronta sem precisar de conversão
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return findProductOrThrow(id);
    }

    @PostMapping
    public ResponseEntity<Product> create(@Valid @RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productRepository.save(product));
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id,
            @Valid @RequestBody Product product) {
                findProductOrThrow(id);
                product.setId(id);
                return productRepository.save(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        Product product = findProductOrThrow(id);
        productRepository.delete(product);
        return ResponseEntity.noContent().build();
    }

    private Product findProductOrThrow(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

}

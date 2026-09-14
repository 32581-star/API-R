package com.des.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.des.backend.model.Product;

//Estender o JpaRepository ja fornece os metodos crud prontos:
//save() - findById() - findAll() - deleteById() - existsById(), etc
//O primeiro tipo é o generico e a entidade(Product), o segundo é
//o tipo do id
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}

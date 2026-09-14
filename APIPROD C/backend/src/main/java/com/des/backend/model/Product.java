package com.des.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Product")
public class Product {

    @Id // Marca como chave primaria da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Gera valores no padrao de identidade (1,2,3,...)
    // evitando duplicidade
    private Long id;
    // Chave primaria tipo Long para suportar valores null

    // Configura a tabela no banco como nao nula e
    // com limite de 100 caracteres
    @Column(nullable = false, length = 100)
    // Impede que o campo venha vazio ou so com espaços " "
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    // Limite de 255 caracteres
    @Column(nullable = false, length = 255)
    // Impede que o campo venha vazio ou so com espaços " "
    @NotBlank(message = "Description is required")
    @Size(max = 255, message = "Description must not exceed 255 characters")
    private String description;

    @Column(nullable = false)
    @Positive(message = "Value must be greater than zero")
    private double value;   

    @PositiveOrZero(message = "Quantity must be zero or greater")
    @Column(nullable = false)
    private int quantity;

}

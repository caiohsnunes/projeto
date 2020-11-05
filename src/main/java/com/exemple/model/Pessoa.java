package com.exemple.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Pessoa {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   
   @Column(name="nome", nullable = false)
   private String nome;
   @Column(name="sobrenome", nullable = false)
   private String sobrenome;
   @Column(name="email", nullable = false)
   private String email;
   @Column(name="celular", nullable = false)
   private String celular;
}
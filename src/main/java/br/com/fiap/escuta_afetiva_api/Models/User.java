package br.com.fiap.escuta_afetiva_api.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
 
     @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
 
     @Email(message = "email inválido")
     @NotBlank(message = "campo obrigatório")
     @Column(unique = true)
     private String email;
 
     @NotBlank(message = "campo obrigartório")
     @Size(min = 5)
     private String password;
     
     @NotNull(message = "campo obrigatório")
     @Enumerated(EnumType.STRING)
     private UserRole role; 
     
 }
    
    


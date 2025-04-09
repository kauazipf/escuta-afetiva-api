package br.com.fiap.escuta_afetiva_api.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "campo obrigatório")
    private String name;

    @NotBlank(message = "campo obrigatório")
    private String plano;

    @NotBlank(message = "campo obrigatório")
    private String email;

    @NotBlank(message = "campo obrigatório")
    private String telefone;

}

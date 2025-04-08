package br.com.fiap.escuta_afetiva_api.Config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.escuta_afetiva_api.Models.Paciente;
import br.com.fiap.escuta_afetiva_api.Repository.PacienteRepository;
import jakarta.annotation.PostConstruct;

@Component
public class DatabaseSeeder {

    @Autowired
    private PacienteRepository PacienteRepository;

    @PostConstruct
    public void PacienteSeed() {
        PacienteRepository.saveAll(
                List.of(
                        Paciente.builder().name("Paciente 1").plano("Mensal").email("paciente1@gmail.com").telefone("4002-8922").build(),
                        Paciente.builder().name("Paciente 2").plano("Quinzenal").email("paciente2@gmail.com").telefone("4003-8933").build(),
                        Paciente.builder().name("Paciente 3").plano("Semanal").email("paciente3@gmail.com").telefone("4004-8944").build()));
    }

}

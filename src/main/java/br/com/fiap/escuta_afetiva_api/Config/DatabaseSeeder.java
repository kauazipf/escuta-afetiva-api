package br.com.fiap.escuta_afetiva_api.Config;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.escuta_afetiva_api.Models.Consulta;
import br.com.fiap.escuta_afetiva_api.Models.Paciente;
import br.com.fiap.escuta_afetiva_api.Repository.ConsultaRepository;
import br.com.fiap.escuta_afetiva_api.Repository.PacienteRepository;
import jakarta.annotation.PostConstruct;

@Component
public class DatabaseSeeder {

    @Autowired
    private PacienteRepository PacienteRepository;

    @Autowired
    private ConsultaRepository ConsultaRepository;

    @PostConstruct
    public void init() {
         var pacientes = List.of(
            Paciente.builder().name("Paciente 1").plano("Mensal").email("paciente1@gmail.com").telefone("4002-8922").build(),
            Paciente.builder().name("Paciente 2").plano("Quinzenal").email("paciente2@gmail.com").telefone("4003-8933").build(),
            Paciente.builder().name("Paciente 3").plano("Semanal").email("paciente3@gmail.com").telefone("4004-8944").build());

 
         PacienteRepository.saveAll(pacientes);
 
         var consultas = new ArrayList<Consulta>();
         for (int i = 0; i < 50; i++) {
             consultas.add(Consulta.builder()
                .consultationDate(Date.valueOf(LocalDate.now().plusDays(new Random().nextInt(30)))) // deve ser presente ou futuro
                .registrationDate(Date.valueOf(LocalDate.now().minusDays(new Random().nextInt(30)))) // deve ser presente ou passado
                .paciente(pacientes.get(new Random().nextInt(pacientes.size())))
                .build());
         }
         ConsultaRepository.saveAll(consultas);

     }

}

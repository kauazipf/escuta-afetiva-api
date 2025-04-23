package br.com.fiap.escuta_afetiva_api.Controllers;

import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.fiap.escuta_afetiva_api.Models.Consulta;
import br.com.fiap.escuta_afetiva_api.Models.Paciente;
import br.com.fiap.escuta_afetiva_api.Repository.ConsultaRepository;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("Consultas")
@Slf4j
public class ConsultaController {
    record ConsultaFilter(Date consultationDate, Date registrationDate, Paciente paciente){}

    @Autowired
    private ConsultaRepository repository;

    @GetMapping
    public List<Consulta> index(ConsultaFilter filter){
        log.info("Buscando consultas com data de registro {} e data de consulta{}", filter.consultationDate(), filter.registrationDate());

        var probe = Consulta.builder()
                        .consultationDate(filter.consultationDate())
                        .registrationDate(filter.registrationDate())
                        .paciente(filter.paciente())
                        .build();

        var matcher = ExampleMatcher
                            .matchingAll()
                            .withIgnoreCase()
                            .withStringMatcher(StringMatcher.CONTAINING);
        
        var example = Example.of(probe, matcher);
        return repository.findAll(example);
    }
    
 }
    


package br.com.fiap.escuta_afetiva_api.Controllers;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.escuta_afetiva_api.Models.Consulta;
import br.com.fiap.escuta_afetiva_api.Models.Paciente;
import br.com.fiap.escuta_afetiva_api.Repository.ConsultaRepository;
import br.com.fiap.escuta_afetiva_api.Specification.ConsultaSpecification;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("Consultas")
@Slf4j
public class ConsultaController {
    public record ConsultaFilter(Date consultationDate, Date registrationDate, Paciente paciente){}

    @Autowired
    private ConsultaRepository repository;

    @GetMapping
    public Page<Consulta> index(ConsultaFilter filter,
            @PageableDefault(size = 10, sort = "date", direction = Direction.DESC) Pageable pageable){
        var specification = ConsultaSpecification.withFilters(filter);
        return repository.findAll(specification, pageable);
    }
    
 }
    


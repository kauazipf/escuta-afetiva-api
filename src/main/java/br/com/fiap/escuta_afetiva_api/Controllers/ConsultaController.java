package br.com.fiap.escuta_afetiva_api.Controllers;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.escuta_afetiva_api.Models.Consulta;
import br.com.fiap.escuta_afetiva_api.Models.Paciente;
import br.com.fiap.escuta_afetiva_api.Repository.ConsultaRepository;
import br.com.fiap.escuta_afetiva_api.Specification.ConsultaSpecification;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("consultas")
@Slf4j
public class ConsultaController {
    public record ConsultaFilter(LocalDate consultationDate, LocalDate registrationDate, Paciente paciente){}

    @Autowired
    private ConsultaRepository repository;

    @GetMapping
    public Page<Consulta> index(ConsultaFilter filter,
            @PageableDefault(size = 10, sort = "consultationDate", direction = Direction.DESC) Pageable pageable){
        var specification = ConsultaSpecification.withFilters(filter);
        return repository.findAll(specification, pageable);
    }

    @PostMapping
    @CacheEvict(value = "consultas", allEntries = true)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(responses = {
            @ApiResponse(responseCode = "400", description = "Falha na validação")
    })
    public @Valid Consulta create(@RequestBody @Valid Consulta consulta) {
        log.info("Cadastrando consulta " + consulta.getConsultationDate() + consulta.getRegistrationDate() 
                + consulta.getPaciente());
    
        // Evita que o JPA tente atualizar em vez de criar
        consulta.setId(null);
    
        return repository.save(consulta);
    }
    
 }
    


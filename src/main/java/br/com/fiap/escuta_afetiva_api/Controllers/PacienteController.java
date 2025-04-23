package br.com.fiap.escuta_afetiva_api.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.escuta_afetiva_api.Models.Paciente;
import br.com.fiap.escuta_afetiva_api.Repository.PacienteRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/pacientes")
@Slf4j
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @GetMapping
    @Cacheable("pacientes")
    @Operation(description = "Listar todas os pacientes", tags = "pacientes", summary = "Lista de pacientes")
    public List<Paciente> index() {
        log.info("Buscando todos pacientes");
        return repository.findAll();
    }

    @PostMapping
    @CacheEvict(value = "pacientes", allEntries = true)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(responses = {
            @ApiResponse(responseCode = "400", description = "Falha na validação")
    })
    public Paciente create(@RequestBody @Valid Paciente paciente) {
        log.info("Cadastrando paciente " + paciente.getName() + paciente.getPlano() 
                + paciente.getEmail() + paciente.getTelefone());
    
        // Evita que o JPA tente atualizar em vez de criar
        paciente.setId(null);
    
        return repository.save(paciente);
    }

    @GetMapping("{id}")
    public Paciente get(@PathVariable Long id) {
        log.info("Buscando paciente " + id);
        return getPaciente(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        log.info("Apagando paciente " + id);
        repository.delete(getPaciente(id));
    }

    @PutMapping("{id}")
    public Paciente update(@PathVariable Long id, @RequestBody @Valid Paciente Paciente) {
        log.info("Atualizando paciente " + id + " " + Paciente);

        getPaciente(id);
        Paciente.setId(id);
        return repository.save(Paciente);
    }

    private Paciente getPaciente(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Paciente não encontrado"));
    }

}

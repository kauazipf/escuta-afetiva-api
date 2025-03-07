package br.com.fiap.escuta_afetiva_api.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.escuta_afetiva_api.Models.Paciente;

@RestController
public class PacienteController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private List<Paciente> repository = new ArrayList<>();

    // GETALL
    @GetMapping(path = "/pacientes")
    public List<Paciente> index() {
        log.info("Buscando todas categorias");
        return repository;
    }

    // POST
    @PostMapping(path = "/pacientes")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Paciente> create(@RequestBody Paciente paciente) {
        log.info("Cadastrando paciente: " + paciente.getNome() + paciente.getPlano() 
        + paciente.getTelefone() + paciente.getEmail());
        repository.add(paciente);
        return ResponseEntity.status(201).body(paciente);
    }

    // GET
    @GetMapping(path = "/pacientes/{id}")
    public ResponseEntity<Paciente> get(@PathVariable Long id) {
        log.info("Buscando categoria... \n" + id);
        var paciente = getPaciente(id);
        return ResponseEntity.ok(paciente);
    }

    // apagar categoria @delete
    @DeleteMapping("/pacientes/{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        log.info("Apagando categoria " + id);
        var paciente = getPaciente(id);
        repository.remove(paciente);
        return ResponseEntity.noContent().build();

    }

    // editar categoria @put
    @PutMapping("/pacientes/{id}")
    public ResponseEntity<Paciente> update(@PathVariable Long id, @RequestBody Paciente paciente) {
        log.info("Atualizando categoria " + id + " " + paciente);
        var pacienteToUpdate = getPaciente(id);
        repository.remove(pacienteToUpdate);
        paciente.setId(id);
        repository.add(paciente);
        return ResponseEntity.ok(paciente);
    }

    private Paciente getPaciente(Long id) {
        return repository.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado."));
    }

}

package br.com.fiap.escuta_afetiva_api.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.escuta_afetiva_api.Models.Paciente;

@RestController
public class PacienteController {

    private List<Paciente> repository = new ArrayList<>();

    @GetMapping("/pacientes")
    public List<Paciente> index(){
        return repository;
    }

    @PostMapping("/pacientes")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Paciente create(@RequestBody Paciente paciente){
        System.out.println("Cadastrando um novo paciente " + paciente.getNome() + paciente.getEmail() + paciente.getPlano() + paciente.getTelefone());
        repository.add(paciente);
        return paciente;
    }

    @GetMapping(path = "/pacientes{id}")
    public ResponseEntity<Paciente> get(@PathVariable Long id) {
        System.out.println("Buscando paciente " + id);
        var paciente = repository.stream().filter(c -> c.getId().equals(id)).findFirst();

        if (paciente.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(paciente.get());
    }
    
}

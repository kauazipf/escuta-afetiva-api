package br.com.fiap.escuta_afetiva_api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.escuta_afetiva_api.Models.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

}

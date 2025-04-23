package br.com.fiap.escuta_afetiva_api.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.fiap.escuta_afetiva_api.Models.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    // List<Agenda> findByConsultationDate(Date date);

}

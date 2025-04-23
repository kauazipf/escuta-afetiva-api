package br.com.fiap.escuta_afetiva_api.Specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import br.com.fiap.escuta_afetiva_api.Controllers.ConsultaController.ConsultaFilter;
import br.com.fiap.escuta_afetiva_api.Models.Consulta;

import jakarta.persistence.criteria.Predicate;

public class ConsultaSpecification {

    public static Specification<Consulta> withFilters(ConsultaFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Filtra por consultationDate, se fornecido (com exatidão, não LIKE porque é Date)
            if (filter.consultationDate() != null) {
                predicates.add(cb.equal(root.get("consultationDate"), filter.consultationDate()));
            }

            // Filtra por registrationDate, se fornecido
            if (filter.registrationDate() != null) {
                predicates.add(cb.equal(root.get("registrationDate"), filter.registrationDate()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

}

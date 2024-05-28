package br.com.ada.estacionamento.vagas;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;


@Repository
public interface VagaRepository extends CrudRepository<Vaga, Integer> {
    
    Collection<Vaga> findByOcupada(boolean b);
}

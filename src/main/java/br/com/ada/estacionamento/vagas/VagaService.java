package br.com.ada.estacionamento.vagas;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VagaService {

    
    public VagaRepository repository;
    
    public VagaService(VagaRepository repository) {
        this.repository = repository;
    }
    
    @PostConstruct
    public void criarVagas(){
        for (int i = 0; i < 10; i++){
            repository.save(new Vaga());
        }
    }



}
